package com.hcl.parking.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.parking.dto.BookRequestDto;
import com.hcl.parking.dto.BookResponseDto;
import com.hcl.parking.entity.AvailableSlot;
import com.hcl.parking.entity.RequestSlot;
import com.hcl.parking.exception.CommonException;
import com.hcl.parking.repository.AvailableSlotRepository;
import com.hcl.parking.repository.RegistrationRepository;
import com.hcl.parking.repository.RequestSlotRepository;
import com.hcl.parking.util.ParkingConstants;

/**
 * 
 * @author HAriPriya G
 *
 */

@Service
public class BookServiceImpl implements BookService {
	private static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	RequestSlotRepository requestSlotRepository;

	@Autowired
	AvailableSlotRepository availableSlotRepository;

	Random random = new Random();

	/**
	 * 
	 * This method is intended to book the slots based on the current date
	 * 
	 * @param regId is the input request which includes
	 * @return it returns BookResponseDto object with message and bookingId
	 */

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	@Override
	public BookResponseDto bookSlot(BookRequestDto bookRequestDto) {
		logger.info("book slot service impl");

		Optional<AvailableSlot> availableSlot = availableSlotRepository.findById(bookRequestDto.getAvailableSlotId());
		if (!availableSlot.isPresent())
			throw new CommonException("No slot present");

		// Fetching all availableSlots from AvailableSlot table
		List<AvailableSlot> availableSlots = availableSlotRepository.findByAvailableDate(LocalDate.now());
		if (availableSlots.isEmpty())
			throw new CommonException(ParkingConstants.NO_AVAILABLE_SLOTS);
		try {
			availableSlotRepository.lockSlot(availableSlots.get(0).getAvailableSlotId());
		} catch (org.hibernate.exception.LockAcquisitionException e) {
			throw new CommonException(" Slot Already booked");

		}
//		try {
//			Thread.sleep(30000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		AvailableSlot availableSlotDb = availableSlots.get(0);
		if (availableSlotDb.getStatus().equalsIgnoreCase("Booked"))
			throw new CommonException("Already booked");

		availableSlotDb.setBookedEmpId(bookRequestDto.getRegId());
		availableSlotDb.setStatus("booked");
		try {
			availableSlotRepository.save(availableSlotDb);
		} catch (Exception e) {
			throw new CommonException(" Slot Already booked");
		}

		return new BookResponseDto("slot booked", "Your slot id is:" + availableSlotDb.getSlotId());
	}

	public LocalDate getLocalDate(String data) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ParkingConstants.DATE_FORMAT);
		return LocalDate.parse(data, dateTimeFormatter);

	}

	
	@Scheduled(fixedRate = 1*60*1000)
	@Override
	public BookResponseDto doRaffle() {
		 logger.info("Current time is :: " + Calendar.getInstance().getTime());

		List<RequestSlot> requestSlotList = requestSlotRepository.findByRequestDate(LocalDate.now().plusDays(1));
		List<AvailableSlot> availableSlotsList = availableSlotRepository
				.findByAvailableDate(LocalDate.now().plusDays(1));
		for (int i = 0; i < availableSlotsList.size(); i++) {
			for (int j = 0; j < requestSlotList.size(); j++) {
				int randomIndex = random.nextInt(requestSlotList.size() - 1);

				int registrationId = requestSlotList.get(randomIndex).getRegistrationId();

				availableSlotsList.get(randomIndex).setBookedEmpId(registrationId);
				availableSlotsList.get(randomIndex).setStatus("booked");

				availableSlotRepository.save(availableSlotsList.get(randomIndex));

			}
		}

		return new BookResponseDto("Success", null);
	}

}
