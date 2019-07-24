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
 * The {@code PersonalSurnameValidator} class is {@code FthValidator} realization for
 * validation personal surname. Surname can consist only from letters.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class PersonalSurnameValidator implements FthValidator {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final int SURNAME_LENGTH = 30;

	private static final String SURNAME_EMPTY = "Field \"surname\" empty.\n";
	private static final String LONG_SURNAME = "Surname has to be less then " + SURNAME_LENGTH + " symbols!\n";
	private static final String INCORRECT_SURNAME = "Surname has to consist of letters!\n";

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
				"Start PersonalSurnameValidator -> isCorrect(FthData).");
		if (data == null ||
				data.getClass() != FthString.class) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in PersonalSurnameValidator -> isCorrect(FthData)!!!");
			return false;
		}

		messageGroup = new ArrayList<>();
		FthString surname = (FthString) data;
		return isBlank(surname) && isCorrect(surname);
	}

	private boolean isCorrect(FthString surname) {
		boolean flag = true;
		if (surname.get().length() > SURNAME_LENGTH) {
			LOGGER.log(Level.WARN, "Long surname!");
			flag = false;
			messageGroup.add(LONG_SURNAME);
		}
		if (!surname.get().matches("\\p{Alpha}+")) {
			LOGGER.log(Level.WARN, "Incorrect surname!");
			flag = false;
			messageGroup.add(INCORRECT_SURNAME);
		}
		return flag;
	}

	private boolean isBlank(FthString surname) {
		boolean flag = true;
		if (surname.get().isBlank()) {
			LOGGER.log(Level.WARN, "Surname is blank!");
			flag = false;
			messageGroup.add(SURNAME_EMPTY);
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