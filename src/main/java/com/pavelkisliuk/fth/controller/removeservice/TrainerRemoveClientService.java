/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.controller.removeservice;

import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.update.TrainerRemoveClientSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code TrainerRemoveClientService} is {@code FthService} realization to unchain
 * conjunction between trainer and client.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class TrainerRemoveClientService implements FthService {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Constrain database unchain conjunction between trainer and client.
	 * <p>
	 *
	 * @param data is id of client in database to find his conjunction with trainer.
	 * @return empty {@code String}.
	 * @throws FthControllerException if {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthData data) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start TrainerRemoveClientService -> serve(FthData).");
		if (data == null ||
				data.getClass() != FthLong.class) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in TrainerRemoveClientService -> serve(FthData)!!!");
			return "";
		}

		FthLong clientId = (FthLong) data;
		try {
			LOGGER.log(Level.INFO,
					"Start unchaining.");
			//Unchain.
			FthRepository.INSTANCE.replace(new TrainerRemoveClientSpecifier(clientId));
			LOGGER.log(Level.INFO,
					"Unchained.");
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in TrainerRemoveClientService -> serve(FthData).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish TrainerRemoveClientService -> serve(FthData).");
		return "";
	}
}