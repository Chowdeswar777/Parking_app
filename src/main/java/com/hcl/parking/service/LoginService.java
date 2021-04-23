package com.hcl.parking.service;

import org.springframework.stereotype.Service;

import com.hcl.parking.dto.LoginDto;
import com.hcl.parking.dto.LoginResponseDto;

@Service
public interface LoginService {
	
	public LoginResponseDto loginUser(LoginDto loginDto);
}
