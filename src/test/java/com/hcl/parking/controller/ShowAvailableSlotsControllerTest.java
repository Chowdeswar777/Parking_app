package com.hcl.parking.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.parking.service.ShowSlotsService;
/**
 * 
 * @author Venkat
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowAvailableSlotsControllerTest {
private static final Logger logger = LoggerFactory.getLogger(ShowAvailableSlotsControllerTest.class);
	@Mock ShowSlotsService showSlotsService;
	@InjectMocks ShowAvailableSlotsController showAvailableSlotsController;
	MockMvc mockMvc;
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(showAvailableSlotsController).build();
	}
	
	/**
	 *this method id intended to written to test the getAvailableSlots
	 */
	@Test
	public void getAvailableSlotsTest() throws Exception{
		logger.info("inside the getAvailableSlotsTest method..");
		mockMvc.perform(MockMvcRequestBuilders.get("/api/showAvailableSlots").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());	
	
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
