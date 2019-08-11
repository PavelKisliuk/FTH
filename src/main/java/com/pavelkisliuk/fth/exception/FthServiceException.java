package com.pavelkisliuk.fth.exception;

public class FthServiceException extends Exception{
	public FthServiceException(String message) {
		super(message);
	}

	public FthServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}