package com.hcl.parking.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.parking.dto.UserRegistrationRequestDto;
import com.hcl.parking.dto.UserRegistrationResponseDto;
import com.hcl.parking.entity.Registration;
import com.hcl.parking.exception.CommonException;
import com.hcl.parking.repository.RegistrationRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceImplTest {
	
	@InjectMocks
	UserRegistrationServiceImpl userRegistrationServiceImpl;
	
	@Mock
	RegistrationRepository registrationRepository;
	
	UserRegistrationResponseDto userRegistrationResponseDto;
	UserRegistrationRequestDto userRegistrationRequestDto;
	Registration registration;
//	PasswordUtil passwordUtil;
	
//	public PasswordUtil getPasswordUtil()
//	{
//		PasswordUtil passwordUtil = new PasswordUtil();
//		passwordUtil.encodePassword(password)
//		
//	}
	
	public UserRegistrationResponseDto getUserRegistrationResponseDto()
	{
		UserRegistrationResponseDto userRegistrationResponseDto = new UserRegistrationResponseDto();
		userRegistrationResponseDto.setMessage("User registered successfuly");
		return userRegistrationResponseDto;
	}
	
	public UserRegistrationRequestDto getUserRegistrationRequestDto()
	{
		UserRegistrationRequestDto userRegistrationRequestDto = new UserRegistrationRequestDto();
		userRegistrationRequestDto.setHclExperience(6F);
		userRegistrationRequestDto.setMobileNumber("8877669955");
		userRegistrationRequestDto.setOverAllExperience(16F);
		userRegistrationRequestDto.setPassword("123454");
		userRegistrationRequestDto.setUserName("singh");
		return userRegistrationRequestDto;
	}
	
	public Registration getRegistration()
	{
		Registration registration = new Registration();
		registration.setHclExperience(7F);
		registration.setMobileNumber("9988776655");
		registration.setOverAllExperience(17F);
		registration.setPassword("123");
		registration.setRegistrationId(2);
		registration.setRoleId(1);
		registration.setUserName("gurpreet");
		return registration;
	}
	
	@Before
	public void setup()
	{
		 registration= getRegistration();
		 userRegistrationResponseDto = getUserRegistrationResponseDto();
		 userRegistrationRequestDto = getUserRegistrationRequestDto();
	}
	
	@Test
	public void testRegisterUser()
	{
		Mockito.when(registrationRepository.save(Mockito.any())).thenReturn(registration);
		UserRegistrationResponseDto response = userRegistrationServiceImpl.registerUser(userRegistrationRequestDto);
		assertEquals("User registered successfuly", response.getMessage());
	}
	
	
	@Test(expected = CommonException.class)
	public void testRegisterUser_1()
	{
		Mockito.when(registrationRepository.findByMobileNumber(Mockito.anyString())).thenReturn(Optional.of(registration));
		userRegistrationServiceImpl.registerUser(userRegistrationRequestDto);
	}

}
