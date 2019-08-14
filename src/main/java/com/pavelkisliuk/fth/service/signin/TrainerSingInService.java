/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.signin;

import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.servlet.PageType;
import com.pavelkisliuk.fth.validator.AuthenticationDataValidator;
import com.pavelkisliuk.fth.validator.TrainerExistValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code TrainerSingInService} class is {@code FthService} realization for
 * authentication trainer in system.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class TrainerSingInService implements FthService<FthAuthenticationData> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Flag for designation if trainer exist in system.
	 */
	private boolean trainerExist;

	/**
	 * Retrieve e-mail and password in {@code FthAuthenticationData}, verify it and return redirection url
	 * as JSON string if {@code FthAuthenticationData} correct, otherwise return message with incorrect data.
	 * <p>
	 *
	 * @param authenticationData is e-mail and password for processing.
	 * @return redirection url as JSON string if {@code FthAuthenticationData} correct,
	 * otherwise return message with incorrect data.
	 * @throws FthServiceException if {@param authenticationData} null; {@code FthRepositoryException} occurred.
	 */
	public String serve(FthAuthenticationData authenticationData) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start TrainerSingInService -> serve(FthAuthenticationData).");
		if (authenticationData == null) {
			throw new FthServiceException(
					"null parameter in TrainerSingInService -> serve(FthAuthenticationData).");
		}

		Map<String, String> responseJson = new HashMap<>();
		AuthenticationDataValidator validator = new AuthenticationDataValidator();
		TrainerExistValidator trainerExistValidator = new TrainerExistValidator();
		if (validator.isCorrect(authenticationData) &&
				trainerExistValidator.isCorrect(authenticationData)) {
			responseJson.put(KEY_REDIRECT, PageType.TRAINER_PAGE.get());
			trainerExist = true;
			LOGGER.log(Level.INFO,
					"Trainer exist. Put page for redirection in responseJson.");
		} else {
			responseJson.put(KEY_ERROR_MESSAGE, validator.toString() + trainerExistValidator.toString());
			LOGGER.log(Level.INFO,
					"Trainer doesn't exist. Put warn information in responseJson.");
		}
		LOGGER.log(Level.DEBUG,
				"Finish TrainerSingInService -> serve(FthAuthenticationData).");
		return GSON.toJson(responseJson);
	}

	/**
	 * Return {@code trainerExist}.
	 * <p>
	 *
	 * @return {@code trainerExist}.
	 */
	public boolean isTrainerExist() {
		return trainerExist;
	}
}