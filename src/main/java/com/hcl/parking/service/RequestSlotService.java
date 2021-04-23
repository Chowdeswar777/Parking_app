package com.hcl.parking.service;

import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.dto.RequestSlotResponseDto;

public interface RequestSlotService {

	RequestSlotResponseDto requestSlot(RequestSlotDto requestSlotDto);

}
