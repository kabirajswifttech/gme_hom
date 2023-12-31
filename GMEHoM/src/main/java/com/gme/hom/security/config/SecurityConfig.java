package com.gme.hom.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.gme.hom.auth.components.JwtAuthFilter;
import com.gme.hom.usersecurity.services.UserSecurityDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Autowired
	private UserSecurityDetailsService jpaUserDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector)
			throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

		return http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						auth -> auth.requestMatchers(mvcMatcherBuilder.pattern("/internal/**")).permitAll() // to be
																											// denyAll
																											// for
																											// production
								.requestMatchers(mvcMatcherBuilder.pattern("/common/**")).permitAll()
								.requestMatchers(mvcMatcherBuilder.pattern("/test/**")).permitAll()
								.requestMatchers(mvcMatcherBuilder.pattern("/signup/**")).permitAll()
								.requestMatchers(mvcMatcherBuilder.pattern("/auth/**")).permitAll()
								// .requestMatchers(mvcMatcherBuilder.pattern("/swagger-ui/*")).permitAll()
								// .requestMatchers(mvcMatcherBuilder.pattern("/swagger-ui")).permitAll()
								// .requestMatchers(mvcMatcherBuilder.pattern("/swagger-ui/**")).permitAll()
								// .requestMatchers(mvcMatcherBuilder.pattern("/v3/api-docs/*")).permitAll()
								// .requestMatchers(mvcMatcherBuilder.pattern("/documents/**")).permitAll() //
								// to be authenticated for production
								.requestMatchers(mvcMatcherBuilder.pattern("/api/v1/**")).authenticated().anyRequest()
								.authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.userDetailsService(jpaUserDetailsService).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}