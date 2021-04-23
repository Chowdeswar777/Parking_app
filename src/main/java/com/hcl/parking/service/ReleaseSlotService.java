package com.hcl.parking.service;

import com.hcl.parking.dto.ReleaseRequestDto;
import com.hcl.parking.dto.ReleaseResponseDto;

public interface ReleaseSlotService {

	ReleaseResponseDto releaseSlots(ReleaseRequestDto releaseRequestDto);

}
