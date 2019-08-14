/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.signin;

import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.servlet.PageType;
import com.pavelkisliuk.fth.validator.AuthenticationDataValidator;
import com.pavelkisliuk.fth.validator.ClientExistValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code ClientSignInService} class is {@code FthService} realization for
 * authentication client in system.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ClientSignInService implements FthService<FthAuthenticationData> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Flag for designation if trainer exist in system.
	 */
	private boolean clientExist;

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
	@Override
	public String serve(FthAuthenticationData authenticationData) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start ClientSignInService -> serve(FthAuthenticationData).");
		if (authenticationData == null) {
			throw new FthServiceException(
					"null parameter in ClientSignInService -> serve(FthAuthenticationData).");
		}

		Map<String, String> responseJson = new HashMap<>();
		AuthenticationDataValidator validator = new AuthenticationDataValidator();
		ClientExistValidator clientExistValidator = new ClientExistValidator();
		if (validator.isCorrect(authenticationData) &&
				clientExistValidator.isCorrect(authenticationData)) {
			responseJson.put(KEY_REDIRECT, PageType.CLIENT_PAGE.get());
			clientExist = true;
			LOGGER.log(Level.INFO,
					"Client exist. Put page for redirection in responseJson.");
		} else {
			responseJson.put(KEY_ERROR_MESSAGE, validator.toString() + clientExistValidator.toString());
			LOGGER.log(Level.INFO,
					"Client doesn't exist. Put warn information in responseJson.");
		}
		LOGGER.log(Level.DEBUG,
				"Finish ClientSignInService -> serve(FthAuthenticationData).");
		return GSON.toJson(responseJson);
	}

	/**
	 * Return {@code clientExist}.
	 * <p>
	 *
	 * @return {@code clientExist}.
	 */
	public boolean isClientExist() {
		return clientExist;
	}
}