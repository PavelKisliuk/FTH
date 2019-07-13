package com.pavelkisliuk.fth.exception;

public class FthControllerException extends Exception{
	public FthControllerException(String message) {
		super(message);
	}

	public FthControllerException(String message, Throwable cause) {
		super(message, cause);
	}
}