/*  By Pavel Kisliuk, 11.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.validator;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthRegistrationData;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.AllClientEmailSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.AllRegistrationEmailSelectSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * The class {@code RegistrationDataValidator} is realization of interface {@code FthValidator} for
 * validation {@code FthRegistrationData}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see com.pavelkisliuk.fth.model.FthRegistrationData
 * @see FthValidator
 * @since 12.0
 */
public class RegistrationDataValidator implements FthValidator {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String NAME_EMPTY = "Field \"name\" empty.\n";
	private static final String SURNAME_EMPTY = "Field \"surname\" empty.\n";
	private static final String EMAIL_EMPTY = "Field \"email\" empty.\n";
	private static final String PASSWORD_EMPTY = "Field \"password\" empty.\n";
	private static final String CONFIRM_PASSWORD_EMPTY = "Field \"name\" empty.\n";

	private static final String DIFFERENT_PASSWORDS = "Different passwords!";
	private static final String LONG_NAME = "Name has to be less then 30 symbols!\n";
	private static final String INCORRECT_NAME = "Name has to consist of letters!\n";
	private static final String LONG_SURNAME = "Surname has to be less then 40 symbols!\n";
	private static final String INCORRECT_SURNAME = "Surname has to consist of letters!\n";
	private static final String LONG_PASSWORD = "Password has to be less then 30 symbols!\n";
	private static final String INCORRECT_PASSWORD = "Password has to consist of letters(big or small) " +
			"and digits!\n";

	/**
	 * {@code ArrayList} for messages about incorrect data.
	 */
	private ArrayList<String> messageGroup;

	/**
	 * Inspect {@code FthRegistrationData} instance for correct data.
	 * <p>
	 *
	 * @param data is data for validation.
	 * @return {@code true} if {@param data} valid, else return {@code false}.
	 */
	@Override
	public boolean isCorrect(FthData data) {
		messageGroup = new ArrayList<>();
		FthRegistrationData registrationData = (FthRegistrationData) data;
		return isDataNotEmpty(registrationData) &&
				isDataCorrect(registrationData) &&
				isEmailUnique(registrationData);
	}

	/**
	 * Check is data empty.
	 * <p>
	 *
	 * @param registrationData is data for validation.
	 * @return {@code true} if {@param registrationData} empty, else return {@code false}.
	 */
	private boolean isDataNotEmpty(FthRegistrationData registrationData) {
		LOGGER.log(Level.DEBUG, "Start RegistrationDataValidator -> isDataNotEmpty().");
		boolean flag = true;
		if (registrationData.getName() == null || registrationData.getName().isBlank()) {
			LOGGER.log(Level.WARN, "Empty data in registrationData.getName().");
			flag = false;
			messageGroup.add(NAME_EMPTY);
		}
		if (registrationData.getSurname() == null || registrationData.getSurname().isBlank()) {
			LOGGER.log(Level.WARN, "Empty data in registrationData.getSurname().");
			flag = false;
			messageGroup.add(SURNAME_EMPTY);
		}
		if (registrationData.getEmail() == null || registrationData.getEmail().isBlank()) {
			LOGGER.log(Level.WARN, "Empty data in registrationData.getEmail().");
			flag = false;
			messageGroup.add(EMAIL_EMPTY);
		}
		if (registrationData.getPassword() == null || registrationData.getPassword().isBlank()) {
			LOGGER.log(Level.WARN, "Empty data in registrationData.getPassword().");
			flag = false;
			messageGroup.add(PASSWORD_EMPTY);
		}
		if (registrationData.getConfirmPassword() == null || registrationData.getConfirmPassword().isBlank()) {
			LOGGER.log(Level.WARN, "Empty data in registrationData.getConfirmPassword().");
			flag = false;
			messageGroup.add(CONFIRM_PASSWORD_EMPTY);
		}
		LOGGER.log(Level.DEBUG, "Finish RegistrationDataValidator -> isDataNotEmpty().");
		return flag;
	}

	/**
	 * Check is data correct(fulfil specify requirements).
	 * <p>
	 *
	 * @param registrationData is data for validation.
	 * @return {@code true} if {@param registrationData} correct, else return {@code false}.
	 */
	private boolean isDataCorrect(FthRegistrationData registrationData) {
		LOGGER.log(Level.DEBUG, "Start RegistrationDataValidator -> isDataCorrect().");
		boolean flag = true;
		if (registrationData.getName().length() > 30) {
			LOGGER.log(Level.WARN, "Long name data in registrationData.getName().");
			flag = false;
			messageGroup.add(LONG_NAME);
		}
		if (!registrationData.getName().matches("[a-zA-Z]+")) {
			LOGGER.log(Level.WARN, "Incorrect name data in registrationData.getName().");
			flag = false;
			messageGroup.add(INCORRECT_NAME);
		}
		if (registrationData.getSurname().length() > 40) {
			LOGGER.log(Level.WARN, "Long surname data in registrationData.getSurname().");
			flag = false;
			messageGroup.add(LONG_SURNAME);
		}
		if (!registrationData.getSurname().matches("[a-zA-Z]+")) {
			LOGGER.log(Level.WARN, "Incorrect surname data in registrationData.getSurname().");
			flag = false;
			messageGroup.add(INCORRECT_SURNAME);
		}
		if (registrationData.getPassword().length() > 30) {
			LOGGER.log(Level.WARN, "Long password data in registrationData.getPassword().");
			flag = false;
			messageGroup.add(LONG_PASSWORD);
		}
		if (!registrationData.getPassword().matches("[a-zA-Z0-9]+")) {
			LOGGER.log(Level.WARN, "Incorrect password data in registrationData.getPassword().");
			flag = false;
			messageGroup.add(INCORRECT_PASSWORD);
		}
		if (!registrationData.getPassword().equals(registrationData.getConfirmPassword())) {
			LOGGER.log(Level.WARN, "Different data in registrationData.getPassword() " +
					"and registrationData.getConfirmPassword()");
			flag = false;
			messageGroup.add(DIFFERENT_PASSWORDS);
		}
		LOGGER.log(Level.DEBUG, "Finish RegistrationDataValidator -> isDataCorrect().");
		return flag;
	}

	/**
	 * Check is e-mail in {@param registrationData} don't exist in database.
	 * <p>
	 *
	 * @param registrationData is data for validation.
	 * @return {@code true} if {@param registrationData} e-mail don't exist in database,
	 * else return {@code false}.
	 */
	private boolean isEmailUnique(FthRegistrationData registrationData) {
		LOGGER.log(Level.DEBUG, "Start RegistrationDataValidator -> isEmailUnique().");
		boolean flag = true;
		try {
			if (FthRepository.INSTANCE.query(new AllRegistrationEmailSelectSpecifier()).
					contains(new FthString(registrationData.getEmail())) ||
					FthRepository.INSTANCE.query(new AllClientEmailSelectSpecifier()).
							contains(new FthString(registrationData.getEmail()))) {
				LOGGER.log(Level.WARN, "Existed email data in registrationData.getEmail().");
				messageGroup.add("This E-mail already exist!");
				flag = false;
			}
		} catch (FthRepositoryException e) {
			messageGroup.add("Something wrong! Try again later.");
			flag = false;
		}
		LOGGER.log(Level.DEBUG, "Finish RegistrationDataValidator -> isEmailUnique().");
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