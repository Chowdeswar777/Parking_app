package com.hcl.parking.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author DeepikaSivarajan
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer registrationId;
	private LocalDate fromDate;
	private LocalDate toDate;
}
