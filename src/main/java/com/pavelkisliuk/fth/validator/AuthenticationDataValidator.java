/*  By Pavel Kisliuk, 11.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.validator;

import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthInt;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.AuthenticateSelectSpecifier;
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
public class AuthenticationDataValidator implements FthValidator {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String EMAIL_EMPTY = "Field \"email\" empty.\n";
	private static final String PASSWORD_EMPTY = "Field \"password\" empty.\n";

	private static final String INCORRECT = "Incorrect e-mail or password.";
	/**
	 * Messages about incorrect data.
	 */
	private ArrayList<String> messageGroup;

	/**
	 * Inspect {@code FthAuthenticationData} instance for correct data.
	 * <p>
	 *
	 * @param data is data for validation.
	 * @return {@code true} if {@param data} valid, else return {@code false}.
	 */
	@Override
	public boolean isCorrect(FthData data) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start AuthenticationDataValidator -> isCorrect(FthData).");
		if (data == null ||
				data.getClass() != FthAuthenticationData.class) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in AuthenticationDataValidator -> isCorrect(FthData)!!!");
			return false;
		}

		messageGroup = new ArrayList<>();
		FthAuthenticationData authenticationData = (FthAuthenticationData) data;
		return isBlank(authenticationData) && isExist(authenticationData);
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

	private boolean isExist(FthAuthenticationData authenticationData) throws FthControllerException {
		boolean flag = true;
		try {
			FthInt fthInt = (FthInt) FthRepository.INSTANCE.query(
					new AuthenticateSelectSpecifier(authenticationData)).get(0);
			if(fthInt.get() != 1) {
				LOGGER.log(Level.WARN,
						"Trainer not exist in system!");
				messageGroup.add(INCORRECT);
				flag = false;
			}
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in AuthenticationDataValidator -> isExist(FthAuthenticationData).", e);
		}
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