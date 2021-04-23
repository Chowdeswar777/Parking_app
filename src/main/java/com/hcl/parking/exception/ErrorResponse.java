package com.hcl.parking.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Integer statusCode;
	private String message;

}
