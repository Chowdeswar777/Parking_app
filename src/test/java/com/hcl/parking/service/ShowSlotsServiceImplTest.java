package com.hcl.parking.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.parking.dto.SlotsResponseDto;
import com.hcl.parking.entity.AvailableSlot;
import com.hcl.parking.exception.CommonException;
import com.hcl.parking.repository.AvailableSlotRepository;

@RunWith(MockitoJUnitRunner.class)
public class ShowSlotsServiceImplTest {

	@InjectMocks
	ShowSlotsServiceImpl showSlotsServiceImpl;
	
	@Mock
	AvailableSlotRepository availableSlotRepository;
	
	SlotsResponseDto slotsResponseDto;
	AvailableSlot availableSlot;
	List<SlotsResponseDto> responseList;
	List<AvailableSlot> slotList;
	LocalDate availableDate = LocalDate.now();
	
	public SlotsResponseDto getSlotsResponseDto()
	{
		SlotsResponseDto slotsResponseDto = new SlotsResponseDto();
		slotsResponseDto.setAvailableDate(availableDate);
		slotsResponseDto.setAvailableSlotId(1);
		slotsResponseDto.setBookedEmpId(1);
		slotsResponseDto.setSlotId(12);
		slotsResponseDto.setStatus("Available");
		slotsResponseDto.setVipId(1);
		return slotsResponseDto;
	}
	
	public AvailableSlot getAvailableSlot()
	{
		AvailableSlot availableSlot = new AvailableSlot();
		availableSlot.setAvailableDate(LocalDate.now());
		availableSlot.setAvailableSlotId(1);
		availableSlot.setBookedEmpId(2);
		availableSlot.setSlotId(12);
		availableSlot.setStatus("Available");
		availableSlot.setVipId(2);
		return availableSlot;
	}
	
	@Before
	public void setup()
	{
		slotsResponseDto = getSlotsResponseDto();
		availableSlot = getAvailableSlot();
		responseList = new ArrayList<>();
		responseList.add(slotsResponseDto);
		slotList = new ArrayList<>();
		slotList.add(availableSlot);
	}
	
	@Test
	public void getAllAvailableSlots()
	{
		Mockito.when(availableSlotRepository.findAll()).thenReturn(slotList);
		List<SlotsResponseDto> response = showSlotsServiceImpl.getAllAvailableSlots();
		assertEquals(responseList.get(0).getSlotId(), response.get(0).getSlotId());
	}
	
	@Test(expected = CommonException.class)
	public void getAllAvailableSlots_1()
	{
		showSlotsServiceImpl.getAllAvailableSlots();
	}
	
}
