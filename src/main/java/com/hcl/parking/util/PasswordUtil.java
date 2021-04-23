package com.hcl.parking.util;

import java.util.Base64;

import org.springframework.stereotype.Component;
@Component
public class PasswordUtil {
	public String encodePassword(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}
}
 