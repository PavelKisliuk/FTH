/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.remove;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.update.TrainerRemoveClientUpdateSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code TrainerRemoveClientService} class is {@code FthService} realization to unchain
 * conjunction between trainer and client.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class TrainerRemoveClientService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Constrain database unchain conjunction between trainer and client.
	 * <p>
	 *
	 * @param clientId is id of client in database to find his conjunction with trainer.
	 * @return empty {@code String}.
	 * @throws FthServiceException if {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong clientId) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start TrainerRemoveClientService -> serve(FthData).");
		if (clientId == null) {
			throw new FthServiceException(
					"null parameter in TrainerRemoveClientService -> serve(FthLong).");
		}
		try {
			LOGGER.log(Level.INFO,
					"Start unchaining.");
			//Unchain.
			FthRepository.INSTANCE.replace(new TrainerRemoveClientUpdateSpecifier(clientId));
			LOGGER.log(Level.INFO,
					"Unchained.");
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in TrainerRemoveClientService -> serve(FthData).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish TrainerRemoveClientService -> serve(FthData).");
		return FthServletCommand.EMPTY_JSON;
	}
}