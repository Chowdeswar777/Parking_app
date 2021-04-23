package com.hcl.parking.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parking.dto.UserRegistrationRequestDto;
import com.hcl.parking.dto.UserRegistrationResponseDto;
import com.hcl.parking.entity.Registration;
import com.hcl.parking.entity.Slot;
import com.hcl.parking.exception.CommonException;
import com.hcl.parking.repository.RegistrationRepository;
import com.hcl.parking.repository.SlotRepository;
import com.hcl.parking.util.ParkingConstants;
import com.hcl.parking.util.PasswordUtil;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
	private static Logger logger = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	SlotRepository slotRepository;

	/**
	 * This method is intended to register user and provide a slot for user
	 * 
	 * @param UserRegistrationRequestDto is the input object which includes
	 *                                   password,mobileNumber,overAllExperience
	 *                                   userName, hclExperience;
	 * @exception USER_ALREADY_REGISTERED,INVALID_MOBILENUMBER,INVALID_NAME
	 * @return UserRegistrationResponseDto which returns message
	 */
	@Override
	public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequestDto) {
		logger.info("in register user service");
		UserRegistrationResponseDto response = new UserRegistrationResponseDto();
		Registration register = new Registration();
		PasswordUtil passwordUtil = new PasswordUtil();
		Optional<Registration> registration = registrationRepository
				.findByMobileNumber(userRegistrationRequestDto.getMobileNumber());
		if (registration.isPresent())
			throw new CommonException(ParkingConstants.USER_ALREADY_REGISTERED);
		if (!validMobileNumber(userRegistrationRequestDto.getMobileNumber())) {
			throw new CommonException(ParkingConstants.INVALID_MOBILENUMBER);
		}

		if (!validateCustomerName(userRegistrationRequestDto.getUserName())) {
			throw new CommonException(ParkingConstants.INVALID_NAME);
		}

		if (userRegistrationRequestDto.getOverAllExperience() >= 15
				&& userRegistrationRequestDto.getHclExperience() >= 5) {
			register.setMobileNumber(userRegistrationRequestDto.getMobileNumber());
			register.setHclExperience(userRegistrationRequestDto.getHclExperience());
			register.setOverAllExperience(userRegistrationRequestDto.getOverAllExperience());
			register.setPassword(passwordUtil.encodePassword(userRegistrationRequestDto.getPassword()));
			register.setRoleId(2);
			register.setUserName(userRegistrationRequestDto.getUserName());

		} else {
			register.setMobileNumber(userRegistrationRequestDto.getMobileNumber());
			register.setHclExperience(userRegistrationRequestDto.getHclExperience());
			register.setOverAllExperience(userRegistrationRequestDto.getOverAllExperience());
			register.setPassword(passwordUtil.encodePassword(userRegistrationRequestDto.getPassword()));
			register.setRoleId(3);
			register.setUserName(userRegistrationRequestDto.getUserName());
		}

		Registration reg = registrationRepository.save(register);

		if (userRegistrationRequestDto.getOverAllExperience() >= 15
				&& userRegistrationRequestDto.getHclExperience() >= 5) {
			Slot slot = new Slot();
			slot.setSlotName("A" + reg.getRegistrationId());
			slot.setRegistrationId(reg.getRegistrationId());
			slotRepository.save(slot);
		}

		response.setMessage("User registered successfuly");
		return response;
	}

	private boolean validMobileNumber(String number) {
		Pattern p = Pattern.compile("^[0-9]{10}$");
		Matcher m = p.matcher(number);
		return (m.find() && m.group().equals(number));
	}

	private boolean validateCustomerName(String customerName) {
		String name = ("^[a-zA-Z]*$");
		return customerName.matches(name);
	}
}
