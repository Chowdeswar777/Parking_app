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

import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.dto.RequestSlotResponseDto;
import com.hcl.parking.service.RequestSlotService;

/**
 * @author DeepikaSivarajan
 *
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class RequestSlotController {
	private static final Logger logger = LoggerFactory.getLogger(RequestSlotController.class);
	@Autowired
	RequestSlotService requestSlotService;

	/**
	 * This method is intended to request slot
	 * 
	 * @param RequestSlotDto is the input parameter which includes
	 *                       registrationId,date
	 * @return RequestSlotResponseDto returns message
	 */

	@PostMapping("/requestSlot")
	public ResponseEntity<RequestSlotResponseDto> requestSlot(@RequestBody RequestSlotDto requestSlotDto) {
		logger.info("Request slots {} : ", requestSlotDto.getRegistrationId());
		RequestSlotResponseDto response = requestSlotService.requestSlot(requestSlotDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
