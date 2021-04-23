package com.hcl.parking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.dto.RequestSlotResponseDto;
import com.hcl.parking.entity.RequestSlot;
import com.hcl.parking.exception.CommonException;
import com.hcl.parking.repository.RequestSlotRepository;
import com.hcl.parking.util.ParkingConstants;

/**
 * @author DeepikaSivarajan
 *
 */

@Service
public class RequestSlotServiceImpl implements RequestSlotService {
	private static final Logger logger = LoggerFactory.getLogger(RequestSlotServiceImpl.class);
	@Autowired
	RequestSlotRepository requestSlotRepository;

	/**
	 * This method is intended to request slot
	 * 
	 * @param RequestSlotDto is the input parameter which includes
	 *                       registrationId,date
	 * @exception USER_NOT_FOUND if user not found
	 * @return RequestSlotResponseDto returns message
	 */

	@Override
	public RequestSlotResponseDto requestSlot(RequestSlotDto requestSlotDto) {
		logger.info("request slot service {} : ", requestSlotDto.getRegistrationId());
		RequestSlot requestSlot = new RequestSlot();
		RequestSlotResponseDto response = new RequestSlotResponseDto();
		if (requestSlotDto.getRegistrationId() == null)
			throw new CommonException(ParkingConstants.USER_NOT_FOUND);
		else {
			requestSlot.setRegistrationId(requestSlotDto.getRegistrationId());
			requestSlot.setRequestDate(requestSlotDto.getRequestDate());
			requestSlotRepository.save(requestSlot);
			response.setMessage("Request sent successfully");
		}
		return response;

	}

}
