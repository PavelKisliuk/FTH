package com.pavelkisliuk.fth.exception;

public class FthRepositoryException extends Exception {
	public FthRepositoryException(String message) {
		super(message);
	}

	public FthRepositoryException(String message, Throwable cause) {
		super(message, cause);
	}
}