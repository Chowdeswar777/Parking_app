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

import com.hcl.parking.dto.ReleaseRequestDto;
import com.hcl.parking.dto.ReleaseResponseDto;
import com.hcl.parking.service.ReleaseSlotService;

/**
 * @author DeepikaSivarajan
 *
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class ReleaseSlotsController {
	private static final Logger logger = LoggerFactory.getLogger(ReleaseSlotsController.class);
	@Autowired
	ReleaseSlotService releaseSlotService;

	/**
	 * This method is intended to releasing parking slots of VIP members
	 * 
	 * @param ReleaseRequestDto is the input object which includes
	 *                          registrationId,fromDate,toDate
	 * @return ReleaseResponseDto which returns message
	 */
	@PostMapping("/release")
	public ResponseEntity<ReleaseResponseDto> releaseSlots(@RequestBody ReleaseRequestDto releaseRequestDto) {
		logger.info("Release slots {} : ", releaseRequestDto.getRegistrationId());
		ReleaseResponseDto response = releaseSlotService.releaseSlots(releaseRequestDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
