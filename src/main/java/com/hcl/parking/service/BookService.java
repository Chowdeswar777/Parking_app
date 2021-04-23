package com.hcl.parking.service;

import com.hcl.parking.dto.BookRequestDto;
import com.hcl.parking.dto.BookResponseDto;

public interface BookService {
	
	
	BookResponseDto bookSlot(BookRequestDto bookRequestDto);
	BookResponseDto doRaffle();

}
