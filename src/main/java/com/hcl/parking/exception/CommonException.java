package com.hcl.parking.exception;

import java.io.Serializable;

public class CommonException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public CommonException(String message) {
		super(message);

	}

}
