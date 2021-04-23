package com.hcl.parking.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parking.dto.UserRegistrationRequestDto;
import com.hcl.parking.dto.UserRegistrationResponseDto;
import com.hcl.parking.service.UserRegistrationServiceImpl;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class UserRegistrationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	UserRegistrationServiceImpl userRegistrationServiceImpl;

	/**
	 * This method is intended to register user and provide a slot for user
	 * 
	 * @param UserRegistrationRequestDto is the input object which includes
	 *                          password,mobileNumber,overAllExperience
	 *                          userName, hclExperience;
	 * @exception USER_ALREADY_REGISTERED,INVALID_MOBILENUMBER,INVALID_NAME
	 * @return UserRegistrationResponseDto which returns message
	 */
	@PostMapping("/register")
	public ResponseEntity<UserRegistrationResponseDto> registerUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
		LOGGER.info("in register user controller");
		UserRegistrationResponseDto response = userRegistrationServiceImpl.registerUser(userRegistrationRequestDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
