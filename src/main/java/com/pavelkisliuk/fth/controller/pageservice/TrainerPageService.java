/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.controller.pageservice;

import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.AllClientByTrainerSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.TrainerByIdSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code TrainerPageService} class is {@code FthService} realization for
 * data for trainer page obtainment.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class TrainerPageService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Retrieve trainer and his client's main information from database
	 * and return it as JSON string.
	 * <p>
	 *
	 * @param trainerId is id of trainer.
	 * @return trainer and his client's main information as JSON string.
	 * @throws FthControllerException if {@param trainerId} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong trainerId) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start TrainerPageService -> serve(FthLong).");
		if (trainerId == null) {
			throw new FthControllerException(
					"null parameter in TrainerPageService -> serve(FthLong).");
		}

		Map<String, List<FthData>> responseJson = new HashMap<>();
		try {
			List<FthData> trainerData = FthRepository.INSTANCE.query(new TrainerByIdSpecifier(trainerId));
			responseJson.put("trainer", trainerData);
			LOGGER.log(Level.INFO,
					"trainerData obtained.");

			List<FthData> clientGroup = FthRepository.INSTANCE.query(new AllClientByTrainerSelectSpecifier(trainerId));
			responseJson.put("client", clientGroup);
			LOGGER.log(Level.INFO,
					"clientGroup obtained.");
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in TrainerPageService -> serve(FthData).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish TrainerSingInService -> serve(FthAuthenticationData).");
		return GSON.toJson(responseJson);
	}
}