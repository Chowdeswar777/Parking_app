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

import com.hcl.parking.dto.LoginDto;
import com.hcl.parking.dto.LoginResponseDto;
import com.hcl.parking.service.LoginService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class LoginController {
	@Autowired 
	LoginService loginService;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){
		logger.info("inside the login method..");
		return new ResponseEntity<>(loginService.loginUser(loginDto), HttpStatus.OK);
	}
}
