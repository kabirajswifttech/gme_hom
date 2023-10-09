package com.gme.hom.auth.service;

import com.gme.hom.auth.models.PasswordResponse;

public interface PasswordService {

	PasswordResponse generateResetPasswordLink(Long id, String actionUserName,String RequestedIp);

	Long getUserIdFromToken(String token);

	PasswordResponse changePasswordByLink(Long userIdFromToken, String newPassword, String token, String requestedIp);

}
