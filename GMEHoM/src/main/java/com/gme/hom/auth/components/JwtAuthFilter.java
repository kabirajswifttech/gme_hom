package com.gme.hom.auth.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gme.hom.usersecurity.services.UserSecurityDetailsService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private UserSecurityDetailsService jpaUserDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String username;

		String jwtToken = null;

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {

				if (cookie.getName().equals("jwt")) {

					jwtToken = cookie.getValue();

					// System.out.println("Cookie of current user: " + cookie.getValue());
				}
			}
		}

		if (jwtToken == null) {

			try {

				filterChain.doFilter(request, response);

			} catch (java.io.IOException | ServletException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			return;
		}

//	        jwtToken = authHeader.substring(7);
		username = jwtUtils.extractUsername(jwtToken);

		// System.out.println("User: " + username + " requesting service at: " + request.getRequestURL());

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);

			if (jwtUtils.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		try {

			filterChain.doFilter(request, response);

		} catch (java.io.IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
