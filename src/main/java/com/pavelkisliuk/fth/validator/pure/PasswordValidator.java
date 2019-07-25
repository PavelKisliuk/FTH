/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.validator.pure;

import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.validator.FthValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * The {@code PasswordValidator} class is {@code FthValidator} realization for
 * validation password. Password has to consist of letters and digits and 6-30 symbols.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class PasswordValidator implements FthValidator {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final int PASSWORD_LENGTH = 30;
	private static final int PASSWORD_MIN_LENGTH = 6;

	private static final String PASSWORD_EMPTY = "Field \"password\" empty.\n";
	private static final String LONG_PASSWORD = "Password has to be less then " + PASSWORD_LENGTH + " symbols!\n";
	private static final String SHORT_PASSWORD = "Password has to be more then " + PASSWORD_MIN_LENGTH + " symbols!\n";
	private static final String INCORRECT_PASSWORD = "Password has to consist of letters(A-Z a-z) " +
			"and digits(0-9)!\n";

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
	public boolean isCorrect(FthData data) {
		LOGGER.log(Level.DEBUG,
				"Start PasswordValidator -> isCorrect(FthData).");
		if (data == null ||
				data.getClass() != FthString.class) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in PasswordValidator -> isCorrect(FthData)!!!");
			return false;
		}

		messageGroup = new ArrayList<>();
		FthString password = (FthString) data;
		return isBlank(password) && isCorrect(password);
	}

	private boolean isCorrect(FthString password) {
		boolean flag = true;
		if (password.get().length() > PASSWORD_LENGTH) {
			LOGGER.log(Level.WARN, "Long password!");
			flag = false;
			messageGroup.add(LONG_PASSWORD);
		}
		if (password.get().length() < PASSWORD_MIN_LENGTH) {
			LOGGER.log(Level.WARN, "Short password!");
			flag = false;
			messageGroup.add(SHORT_PASSWORD);
		}
		if (!password.get().matches("[a-zA-Z0-9]+")) {
			LOGGER.log(Level.WARN, "Incorrect password!");
			flag = false;
			messageGroup.add(INCORRECT_PASSWORD);
		}
		return flag;
	}

	private boolean isBlank(FthString password) {
		boolean flag = true;
		if (password.get().isBlank()) {
			LOGGER.log(Level.WARN, "Password is blank!");
			flag = false;
			messageGroup.add(PASSWORD_EMPTY);
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