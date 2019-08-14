/*  By Pavel Kisliuk, 13.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.singup;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.exception.FthMailException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthRegistrationData;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.servlet.PageType;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;
import com.pavelkisliuk.fth.specifier.insert.RegistrationDataInsertSpecifier;
import com.pavelkisliuk.fth.validator.RegistrationDataValidator;
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
 * @see FthRepository
 * @see MailSender
 * @see com.pavelkisliuk.fth.model.FthRegistrationData
 * @see com.pavelkisliuk.fth.specifier.insert.RegistrationDataInsertSpecifier
 * @see RegistrationDataValidator
 * @since 12.0
 */
public class SingUpService implements FthService<FthRegistrationData> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Length of key to send new user to e-mail for confirm registration.
	 */
	private static final int KEY_LENGTH = 30;
	private static final String SUCCESS_SEND_EMAIL_MESSAGE = "Success! Go to your e-mail for verification.\n" +
			"You will forward to main page.";
	private static final String KEY_MESSAGE = "message";

	private static final String URL_CLIENT = "http://fth.ngrok.io/start?command=CONFIRM&bool=false&str=";
	private static final String URL_TRAINER = "http://fth.ngrok.io/start?command=CONFIRM&bool=true&str=";
	private static final String ADMIN_MAIL = "pavel-2008.94@mail.ru";

	private FthBoolean isTrainer;

	public SingUpService(FthBoolean isTrainer) {
		this.isTrainer = isTrainer;
	}

	/**
	 * Registry new user in the database.
	 * <p>
	 *
	 * @param registrationData is new user data for registration.
	 * @return message for user about progress of registration as {@code String}.
	 * @throws FthServiceException if exceptions occurred.
	 */
	@Override
	public String serve(FthRegistrationData registrationData) throws FthServiceException {
		LOGGER.log(Level.DEBUG, "Start FthSingUpService -> signUp().");
		Map<String, String> responseJson = new HashMap<>();
		RegistrationDataValidator validator = new RegistrationDataValidator();
		LOGGER.log(Level.INFO, "Start validation.");
		if (!validator.isCorrect(registrationData)) {
			LOGGER.log(Level.WARN, "Incorrect data.");
			responseJson.put(KEY_ERROR_MESSAGE, validator.toString());
		} else {
			LOGGER.log(Level.INFO, "Data correct.");
			String key = createKey();
			registrationData.setKey(key);
			LOGGER.log(Level.INFO, "Try to send e-mail.");
			MailSender mailSender = new MailSender();
			try {
				if (!isTrainer.get()) {
					String confirmMessage = URL_CLIENT + key;
					mailSender.send(confirmMessage, registrationData.getEmail());
				} else {
					String confirmMessage = URL_TRAINER + key;
					mailSender.send(confirmMessage, ADMIN_MAIL);
				}
				FthInsertSpecifier insertSpecifier = new RegistrationDataInsertSpecifier(registrationData);
				FthRepository.INSTANCE.add(insertSpecifier);
				responseJson.put(KEY_MESSAGE, SUCCESS_SEND_EMAIL_MESSAGE);
				responseJson.put(KEY_REDIRECT, PageType.INDEX.get());
			} catch (FthMailException | FthRepositoryException e) {
				throw new FthServiceException("Exception in FthSingUpService -> signUp().", e);
			}
		}
		LOGGER.log(Level.DEBUG,
				"Finish FthSingUpService -> signUp().");
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