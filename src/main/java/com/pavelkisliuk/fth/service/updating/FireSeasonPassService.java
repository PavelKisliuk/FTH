/*  By Pavel Kisliuk, 07.07.2019
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
import com.pavelkisliuk.fth.specifier.update.FireSeasonPassUpdateSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code FireSeasonPassService} class is {@code FthService} realization for
 * discarding client season pass.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class FireSeasonPassService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Update client rest visitation's to zero.
	 * <p>
	 *
	 * @param clientId is id of client.
	 * @return EMPTY_JSON.
	 * @throws FthServiceException if {@param clientId} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong clientId) throws FthServiceException {
		FthUpdateSpecifier updateSpecifier = new FireSeasonPassUpdateSpecifier(clientId);
		try {
			FthRepository.INSTANCE.replace(updateSpecifier);
			LOGGER.log(Level.INFO,
					"Season pass fired.");
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in FireSeasonPassService -> serve(FthData).", e);
		}
		return FthServletCommand.EMPTY_JSON;
	}
}