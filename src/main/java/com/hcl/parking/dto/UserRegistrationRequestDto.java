package com.hcl.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequestDto {

	private String userName;
	private String password;
	private String mobileNumber;
	private Float overAllExperience;
	private Float hclExperience;
}
