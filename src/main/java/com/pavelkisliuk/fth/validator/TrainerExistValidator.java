/*  By Pavel Kisliuk, 07.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.validator;

import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.model.FthInt;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.AuthenticateTrainerSelectSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * The {@code TrainerExistValidator} class is {@code FthValidator} realization for
 * verifying trainer in system.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class TrainerExistValidator implements FthValidator<FthAuthenticationData> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Message for incorrect password or e-mail..
	 */
	private static final String INCORRECT = "Incorrect e-mail or password.";

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
	public boolean isCorrect(FthAuthenticationData authenticationData) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start TrainerExistValidator -> isCorrect(FthAuthenticationData).");
		if (authenticationData == null) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in TrainerExistValidator -> isCorrect(FthAuthenticationData)!!!");
			return false;
		}
		return isExist(authenticationData);
	}

	/**
	 * Inspect trainer for existing in database.
	 * <p>
	 *
	 * @param authenticationData is data for validation.
	 * @return {@code true} if trainer exist in database, otherwise {@code false}.
	 */
	private boolean isExist(FthAuthenticationData authenticationData) throws FthControllerException {
		boolean flag = true;
		try {
			FthInt fthInt = (FthInt) FthRepository.INSTANCE.query(
					new AuthenticateTrainerSelectSpecifier(authenticationData)).get(0);
			if (fthInt.get() != 1) {
				LOGGER.log(Level.WARN,
						"Trainer not exist in system!");
				messageGroup.add(INCORRECT);
				flag = false;
			}
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in TrainerExistValidator -> isExist(FthAuthenticationData).", e);
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