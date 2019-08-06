/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.validator.pure;

import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.validator.FthValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * The {@code PersonalNameValidator} class is {@code FthValidator} realization for
 * validation personal name. Name can consist only from letters.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class PersonalNameValidator implements FthValidator<FthString> {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final int NAME_LENGTH = 20;

	private static final String NAME_EMPTY = "Field \"name\" empty.\n";
	private static final String LONG_NAME = "Name has to be less then " + NAME_LENGTH + " symbols!\n";
	private static final String INCORRECT_NAME = "Name has to consist of letters!\n";

	/**
	 * Messages about incorrect data.
	 */
	private ArrayList<String> messageGroup;

	/**
	 * Inspect {@code FthString} instance for correct data.
	 * <p>
	 *
	 * @param name is data for validation.
	 * @return {@code true} if {@param name} valid, else return {@code false}.
	 */
	@Override
	public boolean isCorrect(FthString name) {
		LOGGER.log(Level.DEBUG,
				"Start PersonalNameValidator -> isCorrect(FthString).");
		if (name == null) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in PersonalNameValidator -> isCorrect(FthString)!!!");
			return false;
		}

		messageGroup = new ArrayList<>();
		return isBlank(name) && isCorrectSyntax(name);
	}

	private boolean isCorrectSyntax(FthString name) {
		boolean flag = true;
		if (name.get().length() > NAME_LENGTH) {
			LOGGER.log(Level.WARN,
					"Long name!");
			flag = false;
			messageGroup.add(LONG_NAME);
		}
		if (!name.get().matches("\\p{IsAlphabetic}+")) {
			LOGGER.log(Level.WARN,
					"Incorrect name!");
			flag = false;
			messageGroup.add(INCORRECT_NAME);
		}
		return flag;
	}

	private boolean isBlank(FthString name) {
		boolean flag = true;
		if (name.get().isBlank()) {
			LOGGER.log(Level.WARN,
					"Name is blank!");
			flag = false;
			messageGroup.add(NAME_EMPTY);
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