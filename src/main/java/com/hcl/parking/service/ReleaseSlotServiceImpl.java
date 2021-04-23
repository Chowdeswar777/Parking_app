package com.hcl.parking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parking.dto.ReleaseRequestDto;
import com.hcl.parking.dto.ReleaseResponseDto;
import com.hcl.parking.entity.AvailableSlot;
import com.hcl.parking.entity.Registration;
import com.hcl.parking.entity.Slot;
import com.hcl.parking.exception.CommonException;
import com.hcl.parking.repository.AvailableSlotRepository;
import com.hcl.parking.repository.RegistrationRepository;
import com.hcl.parking.repository.SlotRepository;
import com.hcl.parking.util.ParkingConstants;

/**
 * 
 * @author DeepikaSivarajan
 *
 */
@Service
public class ReleaseSlotServiceImpl implements ReleaseSlotService {
	private static final Logger logger = LoggerFactory.getLogger(ReleaseSlotServiceImpl.class);
	@Autowired
	RegistrationRepository registrationRepository;
	@Autowired
	SlotRepository slotRepository;
	@Autowired
	AvailableSlotRepository availableSlotRepository;

	/**
	 * This method is intended to releasing parking slots of VIP members
	 * 
	 * @param ReleaseRequestDto is the input object which includes
	 *                          registrationId,fromDate,toDate
	 * @exception USER_NOT_FOUND,SLOTS_NOT_FOUND,ERROR_FROM_DATE,ERROR_TO_DATE
	 * @return ReleaseResponseDto which returns message
	 */

	@Override
	public ReleaseResponseDto releaseSlots(ReleaseRequestDto releaseRequestDto) {
		logger.info("Releasing slots in service {} : ", releaseRequestDto.getRegistrationId());
		Optional<Registration> registration = registrationRepository.findById(releaseRequestDto.getRegistrationId());
		if (!registration.isPresent())
			throw new CommonException(ParkingConstants.USER_NOT_FOUND + releaseRequestDto.getRegistrationId());
		Optional<Slot> slot = slotRepository.findByRegistrationId(releaseRequestDto.getRegistrationId());
		if (!slot.isPresent())
			throw new CommonException(ParkingConstants.SLOTS_NOT_FOUND + releaseRequestDto.getRegistrationId());
		LocalDate fromDate = releaseRequestDto.getFromDate();
		LocalDate toDate = releaseRequestDto.getToDate();
		if (fromDate.compareTo(LocalDate.now()) != 0 && fromDate.compareTo(LocalDate.now()) < 0)
			throw new CommonException(ParkingConstants.ERROR_FROM_DATE);
		if (fromDate.compareTo(toDate) != 0 && fromDate.compareTo(toDate) > 0)
			throw new CommonException(ParkingConstants.ERROR_TO_DATE);
		List<LocalDate> dates = new ArrayList<>();
		LocalDate currentDate = fromDate;
		ReleaseResponseDto releaseResponseDto = new ReleaseResponseDto();
		while (currentDate.isBefore(toDate) || currentDate.equals(toDate)) {
			dates.add(currentDate);
			AvailableSlot availableSlot = new AvailableSlot();
			availableSlot.setSlotId(slot.get().getSlotId());
			availableSlot.setStatus("Available");
			availableSlot.setVipId(releaseRequestDto.getRegistrationId());
			availableSlot.setAvailableDate(currentDate);
			AvailableSlot response = availableSlotRepository.save(availableSlot);
			logger.info("Available slot id {} : ", response.getAvailableSlotId());
			currentDate = currentDate.plusDays(1);
			releaseResponseDto.setMessage("Slot released Successfully");
		}
		return releaseResponseDto;
	}

}
