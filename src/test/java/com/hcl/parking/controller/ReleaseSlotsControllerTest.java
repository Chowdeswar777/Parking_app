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
import com.hcl.parking.dto.ReleaseRequestDto;
import com.hcl.parking.service.ReleaseSlotService;

@RunWith(MockitoJUnitRunner.class)
public class ReleaseSlotsControllerTest {
	@Mock
	ReleaseSlotService releaseSlotService;
	@InjectMocks
	ReleaseSlotsController releaseSlotsController;
	@Autowired
	MockMvc mockMvc;
	ReleaseRequestDto releaseRequestDto;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(releaseSlotsController).build();
		releaseRequestDto = new ReleaseRequestDto();
	}

	@Test
	public void testReleaseSlots() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/release").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(releaseRequestDto)))
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
