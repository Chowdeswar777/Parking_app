package com.hcl.parking.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer regId;
	private String message;
	private String roleName;
}
