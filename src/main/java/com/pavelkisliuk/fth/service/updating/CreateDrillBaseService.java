/*  By Pavel Kisliuk, 13.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.updating;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthDrillBase;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;
import com.pavelkisliuk.fth.specifier.insert.NewDrillInsertSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code CreateDrillBaseService} class is {@code FthService} realization for
 * new drill insertion.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class CreateDrillBaseService implements FthService<FthDrillBase> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * ID of trainer.
	 */
	private FthLong trainerId;

	public CreateDrillBaseService(FthLong trainerId) {
		this.trainerId = trainerId;
	}

	/**
	 * Create new drill in system.
	 * <p>
	 *
	 * @param drillBase is drill describer.
	 * @return EMPTY_JSON.
	 * @throws FthServiceException if {@param drillBase} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthDrillBase drillBase) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start SpreadSeasonService -> serve(FthLong).");
		if (drillBase == null) {
			throw new FthServiceException(
					"null parameter in CreateDrillBaseService -> serve(FthLong).");
		}

		FthInsertSpecifier insertSpecifier = new NewDrillInsertSpecifier(trainerId, drillBase);
		try {
			FthRepository.INSTANCE.add(insertSpecifier);
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in CreateDrillBaseService -> serve(FthData).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish CreateDrillBaseService -> serve(FthLong).");
		return FthServletCommand.EMPTY_JSON;
	}
}