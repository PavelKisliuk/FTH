/*  By Pavel Kisliuk, 07.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.validator;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.model.FthInt;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.AuthenticateClientSelectSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * The {@code ClientExistValidator} class is {@code FthValidator} realization for
 * verifying client in system.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ClientExistValidator implements FthValidator<FthAuthenticationData> {
	private static final Logger LOGGER = LogManager.getLogger();

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
	public boolean isCorrect(FthAuthenticationData authenticationData) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start ClientExistValidator -> isCorrect(FthAuthenticationData).");
		if (authenticationData == null) {
			LOGGER.log(Level.ERROR,
					"null parameter in ClientExistValidator -> isCorrect(FthAuthenticationData)!!!");
			return false;
		}

		return isExist(authenticationData);
	}

	/**
	 * Inspect client for existing in database.
	 * <p>
	 *
	 * @param authenticationData is data for validation.
	 * @return {@code true} if client exist in database, otherwise {@code false}.
	 */
	private boolean isExist(FthAuthenticationData authenticationData) throws FthServiceException {
		boolean flag = true;
		try {
			FthInt fthInt = (FthInt) FthRepository.INSTANCE.query(
					new AuthenticateClientSelectSpecifier(authenticationData)).get(0);
			if (fthInt.get() != 1) {
				LOGGER.log(Level.WARN,
						"Client not exist in system!");
				messageGroup.add(INCORRECT);
				flag = false;
			}
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in ClientExistValidator -> isExist(FthAuthenticationData).", e);
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