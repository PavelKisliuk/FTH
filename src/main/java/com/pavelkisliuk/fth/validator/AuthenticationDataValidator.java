/*  By Pavel Kisliuk, 11.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.validator;

import com.pavelkisliuk.fth.model.FthAuthenticationData;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * The {@code AuthenticationDataValidator} class is {@code FthValidator} realization for
 * validation {@code FthAuthenticationData}. Validator check e-mail and password of trainer,
 * if this trainer exist in system.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class AuthenticationDataValidator implements FthValidator<FthAuthenticationData> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Message for e-mail field empty.
	 */
	private static final String EMAIL_EMPTY = "Field \"email\" empty.\n";

	/**
	 * Message for password field empty.
	 */
	private static final String PASSWORD_EMPTY = "Field \"password\" empty.\n";

	/**
	 * Messages about incorrect data.
	 */
	private ArrayList<String> messageGroup = new ArrayList<>();

	/**
	 * Inspect {@code FthAuthenticationData} instance for correct data.
	 * <p>
	 *
	 * @param authenticationData is data for validation.
	 * @return {@code true} if {@param authenticationData} valid, else return {@code false}.
	 */
	@Override
	public boolean isCorrect(FthAuthenticationData authenticationData) {
		LOGGER.log(Level.DEBUG,
				"Start AuthenticationDataValidator -> isCorrect(FthAuthenticationData).");
		if (authenticationData == null) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in AuthenticationDataValidator -> isCorrect(FthAuthenticationData)!!!");
			return false;
		}
		return isBlank(authenticationData);
	}

	private boolean isBlank(FthAuthenticationData authenticationData) {
		boolean flag = true;
		if (authenticationData.getEmail() == null || authenticationData.getEmail().isBlank()) {
			LOGGER.log(Level.WARN,
					"E-mail empty!");
			flag = false;
			messageGroup.add(EMAIL_EMPTY);
		}
		if (authenticationData.getPassword() == null || authenticationData.getPassword().isBlank()) {
			LOGGER.log(Level.WARN,
					"Password empty!");
			flag = false;
			messageGroup.add(PASSWORD_EMPTY);
		}
		return flag;
	}

	@Override
	public String toString() {
		return !messageGroup.isEmpty() ? messageGroup.toString().replace("[", "- ").
				replace(']', ' ').
				replace(',', '-').
				strip() : "";
	}
}