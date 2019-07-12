package com.pavelkisliuk.fth.exception;

public class FthServletException extends Exception {
	public FthServletException(String message) {
		super(message);
	}

	public FthServletException(String message, Throwable cause) {
		super(message, cause);
	}

	public FthServletException(Throwable cause) {
		super(cause);
	}

	protected FthServletException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}