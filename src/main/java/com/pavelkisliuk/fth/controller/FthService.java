/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */
package com.pavelkisliuk.fth.controller;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthData;

/**
 * The {@code FthService} interface describe behaviour of service classes for processing data from client
 * and prepare corresponding answer.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public interface FthService<T extends FthData> {
	/**
	 * Key in JSON String.
	 */
	String KEY_MESSAGE = "message";

	/**
	 * Key in JSON String.
	 */
	String KEY_REDIRECT = "redirect";

	/**
	 * Instance for JSON string creation.
	 */
	Gson GSON = new Gson();

	/**
	 * Retrieve special instance of {@code FthData}, process it and return corresponding answer
	 * as JSON string.
	 * <p>
	 *
	 * @param data special instance of {@code FthData} for processing.
	 * @return JSON string.
	 * @throws FthControllerException if special situations occurred.
	 */
	String serve(T data) throws FthControllerException;
}