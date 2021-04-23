//package com.hcl.parking.service;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.hcl.parking.dto.RequestSlotDto;
//import com.hcl.parking.dto.RequestSlotResponseDto;
//import com.hcl.parking.entity.RequestSlot;
//import com.hcl.parking.repository.RequestSlotRepository;
//
///**
// * @author DeepikaSivarajan
// *
// */
//@RunWith(MockitoJUnitRunner.class)
//public class RequestSlotServiceImplTest {
//	@Mock
//	RequestSlotRepository requestSlotRepository;
//	@InjectMocks
//	RequestSlotServiceImpl requestSlotServiceImpl;
//	RequestSlotDto requestSlotDto;
//	RequestSlotResponseDto requestSlotResponseDto;
//	RequestSlot requestSlot;
//
//	@Before
//	public void setUp() {
//		requestSlotDto = new RequestSlotDto();
//		requestSlotResponseDto = new RequestSlotResponseDto();
//		requestSlot = new RequestSlot();
//	}
//
//	@Test
//	public void testRequestSlot() {
//		Mockito.when(requestSlotRepository.save(Mockito.any())).thenReturn();
//		RequestSlotResponseDto response = requestSlotServiceImpl.requestSlot();
//		Assert.assertEquals("Request sent successfully", response.getMessage());
//	}
//
//}
