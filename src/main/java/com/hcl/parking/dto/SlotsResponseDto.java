package com.hcl.parking.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotsResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer availableSlotId;
	private LocalDate availableDate;
	private Integer slotId;
	private String status;
	private Integer vipId;
	private Integer bookedEmpId;
}
