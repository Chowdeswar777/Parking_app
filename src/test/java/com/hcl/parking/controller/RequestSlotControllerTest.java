package com.hcl.parking.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.service.RequestSlotService;

@RunWith(MockitoJUnitRunner.class)
public class RequestSlotControllerTest {
	@Mock
	RequestSlotService requestSlotService;
	@InjectMocks
	RequestSlotController requestSlotController;
	@Autowired
	MockMvc mockMvc;
	RequestSlotDto requestSlotDto;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(requestSlotController).build();
		requestSlotDto = new RequestSlotDto();
	}

	@Test
	public void testRequestSlot() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/requestSlot").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(requestSlotDto)))
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
