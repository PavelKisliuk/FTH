/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.validator.pure;

import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.AllAuthenticationEmailSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.AllRegistrationEmailSelectSpecifier;
import com.pavelkisliuk.fth.validator.FthValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EmailValidator} class is {@code FthValidator} realization for
 * validation e-mail.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class EmailValidator implements FthValidator {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final int EMAIL_LENGTH = 250;
	private static final int EMAIL_MIN_LENGTH = 6;

	private static final String EMAIL_EMPTY = "Field \"e-mail\" empty.\n";
	private static final String LONG_EMAIL = "E-mail has to be less then " + EMAIL_LENGTH + " symbols!\n";
	private static final String SHORT_EMAIL = "E-mail has to be more then " + EMAIL_MIN_LENGTH + " symbols!\n";
	private static final String EMAIL_EXIST = "User with such e-mail already exist.";
	/**
	 * Messages about incorrect data.
	 */
	private ArrayList<String> messageGroup;

	/**
	 * Inspect {@code FthString} instance for correct data.
	 * <p>
	 *
	 * @param data is data for validation.
	 * @return {@code true} if {@param data} valid, else return {@code false}.
	 */
	@Override
	public boolean isCorrect(FthData data) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start EmailValidator -> isCorrect(FthData).");
		if (data == null ||
				data.getClass() != FthString.class) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in EmailValidator -> isCorrect(FthData)!!!");
			return false;
		}

		messageGroup = new ArrayList<>();
		FthString email = (FthString) data;
		return isBlank(email) && isCorrect(email) && isEmailUnique(email);
	}

	private boolean isCorrect(FthString email) {
		boolean flag = true;
		if (email.get().length() > EMAIL_LENGTH) {
			LOGGER.log(Level.WARN, "Long password!");
			flag = false;
			messageGroup.add(LONG_EMAIL);
		}
		if (email.get().length() < EMAIL_MIN_LENGTH) {
			LOGGER.log(Level.WARN, "Short password!");
			flag = false;
			messageGroup.add(SHORT_EMAIL);
		}
		return flag;
	}

	private boolean isBlank(FthString email) {
		boolean flag = true;
		if (email.get().isBlank()) {
			LOGGER.log(Level.WARN, "Email is blank!");
			flag = false;
			messageGroup.add(EMAIL_EMPTY);
		}
		return flag;
	}

	/**
	 * Check is e-mail in {@param email} don't exist in database.
	 * <p>
	 *
	 * @param email is data for validation.
	 * @return {@code true} if {@param email} e-mail don't exist in database,
	 * else return {@code false}.
	 */
	private boolean isEmailUnique(FthString email) throws FthControllerException {
		LOGGER.log(Level.DEBUG, "Start EmailValidator -> isEmailUnique(FthString).");
		boolean flag = true;
		try {
			List<FthData> registrationEmailGroup =
					FthRepository.INSTANCE.query(new AllRegistrationEmailSelectSpecifier());
			List<FthData> authenticationEmailGroup =
					FthRepository.INSTANCE.query(new AllAuthenticationEmailSelectSpecifier());
			if (registrationEmailGroup.contains(email) ||
					authenticationEmailGroup.contains(email)) {
				LOGGER.log(Level.WARN, "E-mail already exist!");
				messageGroup.add(EMAIL_EXIST);
				flag = false;
			}
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in EmailValidator -> isEmailUnique(FthString).", e);
		}
		LOGGER.log(Level.DEBUG, "Finish EmailValidator -> isEmailUnique(FthString).");
		return flag;
	}

	@Override
	public String toString() {
		return messageGroup.toString().replace("[", "- ").
				replace(']', ' ').
				replace(',', '-').
				strip();
	}
}