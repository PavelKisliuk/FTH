/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.updating;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;
import com.pavelkisliuk.fth.specifier.update.ClientTrainerUpdateSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code ChangeTrainerService} is {@code FthService} realization for
 * changing client trainer.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ChangeTrainerService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * ID of client.
	 */
	private FthLong clientId;

	/**
	 * Construct for {@code clientId} initialisation.
	 * <p>
	 *
	 * @param clientId for {@code clientId} initialisation.
	 */
	public ChangeTrainerService(FthLong clientId) {
		this.clientId = clientId;
	}

	@Override
	public String serve(FthLong trainerId) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start ChangeTrainerService -> serve(FthLong).");
		if (trainerId == null) {
			throw new FthServiceException(
					"null parameter in ChangeTrainerService -> serve(FthLong).");
		}

		FthUpdateSpecifier updateSpecifier = new ClientTrainerUpdateSpecifier(clientId, trainerId);
		try {
			FthRepository.INSTANCE.replace(updateSpecifier);
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in ChangeTrainerService -> serve(FthData).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish ChangeTrainerService -> serve(FthLong).");
		return FthServletCommand.EMPTY_JSON;
	}
}