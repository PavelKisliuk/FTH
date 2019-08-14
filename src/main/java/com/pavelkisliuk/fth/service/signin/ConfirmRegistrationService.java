/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.signin;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code ClientSignInService} class is {@code FthService} realization for
 * confirm registration.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ConfirmRegistrationService implements FthService<FthString> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * User for confirmed.
	 */
	private FthBoolean isTrainer;

	/**
	 * Constructor for {@code isTrainer} initialization.
	 * <p>
	 *
	 * @param isTrainer for {@code isTrainer} initialization.
	 */
	public ConfirmRegistrationService(FthBoolean isTrainer) {
		this.isTrainer = isTrainer;
	}

	/**
	 * confir user registration and set user data in database in necessary condition.
	 * <p>
	 *
	 * @param registrationKey is registration key for authenticate redistricted user in database.
	 * @return empty JSON string.
	 * @throws FthServiceException if {@param registrationKey} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthString registrationKey) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start ConfirmRegistrationService -> serve(FthLong).");
		if (registrationKey == null) {
			throw new FthServiceException(
					"null parameter in ConfirmRegistrationService -> serve(FthLong).");
		}

		String userTable = isTrainer.get() ? "TrainerData" : "ClientPersonalData";
		FthString fthUserTable = new FthString(userTable);
		try {
			FthRepository.INSTANCE.newUserInsert(fthUserTable, registrationKey);
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in ConfirmRegistrationService -> serve(FthLong).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish ConfirmRegistrationService -> serve(FthAuthenticationData).");
		return FthServletCommand.SUCCESS;
	}
}