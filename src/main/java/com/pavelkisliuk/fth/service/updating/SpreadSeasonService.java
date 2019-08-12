/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.updating;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthClientPublicData;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;
import com.pavelkisliuk.fth.specifier.update.SpreadSeasonPassUpdateSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code SpreadSeasonService} class is {@code FthService} realization for
 * prolongation of client season pass.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class SpreadSeasonService implements FthService<FthClientPublicData> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Spread client's season.
	 * <p>
	 *
	 * @param clientPublicData is data for spreading.
	 * @return EMPTY_JSON.
	 * @throws FthServiceException if {@param clientPublicData} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthClientPublicData clientPublicData) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start SpreadSeasonService -> serve(FthLong).");
		if (clientPublicData == null) {
			throw new FthServiceException(
					"null parameter in SpreadSeasonService -> serve(FthLong).");
		}

		FthUpdateSpecifier updateSpecifier = new SpreadSeasonPassUpdateSpecifier(clientPublicData);
		try {
			FthRepository.INSTANCE.replace(updateSpecifier);
			LOGGER.log(Level.INFO,
					"Season pass spread.");
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in SpreadSeasonService -> serve(FthData).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish SpreadSeasonService -> serve(FthLong).");
		return FthServletCommand.EMPTY_JSON;
	}
}