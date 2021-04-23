package com.hcl.parking.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private String slotId;

}
