package com.hcl.parking.service;

import org.springframework.stereotype.Service;

import com.hcl.parking.dto.UserRegistrationRequestDto;
import com.hcl.parking.dto.UserRegistrationResponseDto;

@Service
public interface UserRegistrationService {

	public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
}
