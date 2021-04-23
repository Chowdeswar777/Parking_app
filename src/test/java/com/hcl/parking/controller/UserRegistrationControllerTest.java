package com.hcl.parking.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.parking.dto.UserRegistrationRequestDto;
import com.hcl.parking.dto.UserRegistrationResponseDto;
import com.hcl.parking.service.UserRegistrationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class UserRegistrationControllerTest {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserRegistrationControllerTest.class);
	
	@InjectMocks
	UserRegistrationController userRegistrationController;
	
	@Mock
	UserRegistrationServiceImpl userRegistrationServiceImpl;
	
	MockMvc mockMvc;

	UserRegistrationRequestDto userRegistrationRequestDto;
	UserRegistrationResponseDto userRegistrationResponseDto;
	
	public UserRegistrationRequestDto getUserRegistrationRequestDto()
	{
		userRegistrationRequestDto = new UserRegistrationRequestDto();
		userRegistrationRequestDto.setHclExperience(13F);
		userRegistrationRequestDto.setMobileNumber("1234567891");
		userRegistrationRequestDto.setOverAllExperience(20F);
		userRegistrationRequestDto.setPassword("1223");
		userRegistrationRequestDto.setUserName("Gurpreet");
		return userRegistrationRequestDto;
	}
	public UserRegistrationResponseDto getUserRegistrationResponseDto()
	{
		userRegistrationResponseDto = new UserRegistrationResponseDto();
		userRegistrationResponseDto.setMessage("User registered successfuly");
		return userRegistrationResponseDto;
	}

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build();
		userRegistrationRequestDto = getUserRegistrationRequestDto();
		userRegistrationResponseDto = getUserRegistrationResponseDto();
	}

	@Test
	public void registerUserTest() throws Exception {

		logger.info("in  register user method");
		mockMvc.perform(MockMvcRequestBuilders.post("/api/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(userRegistrationRequestDto)))
				.andExpect(status().isCreated());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
}
