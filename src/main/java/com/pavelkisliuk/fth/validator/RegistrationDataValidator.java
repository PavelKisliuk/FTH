/*  By Pavel Kisliuk, 11.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.validator;

import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthRegistrationData;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.validator.pure.EmailValidator;
import com.pavelkisliuk.fth.validator.pure.PasswordValidator;
import com.pavelkisliuk.fth.validator.pure.PersonalNameValidator;
import com.pavelkisliuk.fth.validator.pure.PersonalSurnameValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * The {@code RegistrationDataValidator} class is {@code FthValidator} realization for
 * validation {@code FthRegistrationData}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class RegistrationDataValidator implements FthValidator<FthRegistrationData> {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String DIFFERENT_PASSWORDS = "Different passwords!";

	/**
	 * Messages about incorrect data.
	 */
	private ArrayList<String> messageGroup;

	/**
	 * Inspect {@code FthRegistrationData} instance for correct data.
	 * <p>
	 *
	 * @param registrationData is data for validation.
	 * @return {@code true} if {@param registrationData} valid, else return {@code false}.
	 */
	@Override
	public boolean isCorrect(FthRegistrationData registrationData) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start RegistrationDataValidator -> isCorrect(FthRegistrationData).");
		if (registrationData == null) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in RegistrationDataValidator -> isCorrect(FthRegistrationData)!!!");
			return false;
		}

		messageGroup = new ArrayList<>();
		return isCorrectSyntax(registrationData);
	}

	private boolean isCorrectSyntax(FthRegistrationData registrationData) throws FthServiceException {
		boolean flag = true;
		FthValidator<FthString> validator = new PersonalNameValidator();
		if (!validator.isCorrect(new FthString(registrationData.getName()))) {
			LOGGER.log(Level.WARN,
					"Invalid name!");
			flag = false;
			messageGroup.add(validator.toString());
		}
		validator = new PersonalSurnameValidator();
		if (!validator.isCorrect(new FthString(registrationData.getSurname()))) {
			LOGGER.log(Level.WARN,
					"Invalid surname!");
			flag = false;
			messageGroup.add(validator.toString());
		}
		validator = new PasswordValidator();
		if (!validator.isCorrect(new FthString(registrationData.getPassword()))) {
			LOGGER.log(Level.WARN,
					"Invalid password!");
			flag = false;
			messageGroup.add(validator.toString());
		}
		validator = new EmailValidator();
		if (!validator.isCorrect(new FthString(registrationData.getEmail()))) {
			LOGGER.log(Level.WARN,
					"Invalid e-mail!");
			flag = false;
			messageGroup.add(validator.toString());
		}
		if (!registrationData.getPassword().equals(registrationData.getConfirmPassword())) {
			LOGGER.log(Level.WARN,
					"Different passwords!");
			flag = false;
			messageGroup.add(DIFFERENT_PASSWORDS);
		}
		return flag;
	}

	@Override
	public String toString() {
		return messageGroup.toString().replace("[", "- ").
				replace(']', ' ').
				replace(',', '-').
				replace("- -", "\n-").
				replace(".-", ".\n-").
				strip();
	}
}