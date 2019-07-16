/*  By Pavel Kisliuk, 13.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.controller.singupservice;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthMailException;
import com.pavelkisliuk.fth.model.FthRegistrationData;
import com.pavelkisliuk.fth.validator.FthRegistrationDataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The {@code FthSingUpService} class registry new users in database.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see com.pavelkisliuk.fth.repository.FthRepositorySingleton
 * @see MailSender
 * @see com.pavelkisliuk.fth.model.FthRegistrationData
 * @see com.pavelkisliuk.fth.specifier.insert.RegistrationDataInsertSpecifier
 * @see com.pavelkisliuk.fth.validator.FthRegistrationDataValidator
 * @since 12.0
 */
public class FthSingUpService {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Length of key to send new user to e-mail for confirm registration.
	 */
	private static final int KEY_LENGTH = 30;
	private static final String SUCCESS_SEND_EMAIL_MESSAGE = "Success! Go to your e-mail for verification.\n" +
			"You will forward to main page.";
	private static final String REDIRECT_PATH = "https://www.google.ru";
	private static final String KEY_MESSAGE = "message";
	private static final String KEY_REDIRECT = "redirect";

	/**
	 * Registry new user in the database.
	 * <p>
	 *
	 * @param registrationData is new user data for registration.
	 * @return message for user about progress of registration as {@code String}.
	 * @throws FthControllerException if exceptions occurred.
	 */
	public String signUp(FthRegistrationData registrationData) throws FthControllerException {
		LOGGER.log(Level.DEBUG, "Start FthSingUpService -> signUp().");
		Map<String, String> responseJson = new HashMap<>();
		FthRegistrationDataValidator validator = new FthRegistrationDataValidator();
		LOGGER.log(Level.INFO, "Start validation.");
		if (!validator.isCorrect(registrationData)) {
			LOGGER.log(Level.WARN, "Incorrect data.");
			responseJson.put(KEY_MESSAGE, validator.toString());
		} else {
			LOGGER.log(Level.INFO, "Data correct.");
			String key = createKey();
			registrationData.setKey(key);
			String confirmMessage = "http://localhost:8080/FTH/start?command=confirm&key=" + key;

			LOGGER.log(Level.INFO, "Try to send e-mail.");
			MailSender mailSender = new MailSender();
			try {
				mailSender.send(confirmMessage, registrationData.getEmail());
				//FthRepositorySingleton.INSTANCE.add(new RegistrationDataInsertSpecifier(registrationData));
				responseJson.put(KEY_MESSAGE, SUCCESS_SEND_EMAIL_MESSAGE);
				responseJson.put(KEY_REDIRECT, REDIRECT_PATH);
			} catch (FthMailException /*| FthRepositoryException*/ e) {
				throw new FthControllerException("Exception in FthSingUpService -> signUp().", e);
			}
		}
		LOGGER.log(Level.DEBUG, "Finish FthSingUpService -> signUp().");
		return new Gson().toJson(responseJson);
	}

	/**
	 * Create unique key for confirm registration.
	 * <p>
	 *
	 * @return unique key.
	 */
	private String createKey() {
		LOGGER.log(Level.DEBUG, "Start FthSingUpService -> createKey().");
		final String keyPattern = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder key = new StringBuilder();
		Random random = new SecureRandom();
		for (int i = 0; i < KEY_LENGTH; i++) {
			key.append(keyPattern.charAt(random.nextInt(keyPattern.length())));
		}
		LOGGER.log(Level.INFO, "Created key: -> " + key);
		LOGGER.log(Level.DEBUG, "Finish FthSingUpService -> createKey().");
		return key.toString();
	}
}