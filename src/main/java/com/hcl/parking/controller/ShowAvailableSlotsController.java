package com.hcl.parking.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parking.dto.SlotsResponseDto;
import com.hcl.parking.service.ShowSlotsService;
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class ShowAvailableSlotsController {
@Autowired ShowSlotsService showSlotsService;

/**
 * This method is intended to show all available slots
 * 
 * @return List<SlotsResponseDto> which returns list of avaliable slots
 */
@GetMapping("/showAvailableSlots")
public ResponseEntity<List<SlotsResponseDto>> getAvailableSlots(){
	return new ResponseEntity<>(showSlotsService.getAllAvailableSlots(), HttpStatus.OK);
}
}
