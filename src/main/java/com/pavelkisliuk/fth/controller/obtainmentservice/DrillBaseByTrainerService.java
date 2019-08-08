/*  By Pavel Kisliuk, 08.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.controller.obtainmentservice;

import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.DrillBaseByTrainerSelectSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code DrillBaseByTrainerService} class is {@code FthService} realization for
 * obtainment all drill's concrete trainer.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class DrillBaseByTrainerService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Retrieve drill's and return it as JSON string.
	 * <p>
	 *
	 * @param trainerId is id of trainer.
	 * @return drill's group as JSON string.
	 * @throws FthControllerException if {@param trainerId} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong trainerId) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start DrillBaseByTrainerService -> serve(FthLong).");
		if (trainerId == null) {
			throw new FthControllerException(
					"null parameter in DrillBaseByTrainerService -> serve(FthLong).");
		}

		Map<String, List<FthData>> responseJson = new HashMap<>();
		try {
			List<FthData> drillBaseGroup =
					FthRepository.INSTANCE.query(
							new DrillBaseByTrainerSelectSpecifier(trainerId));
			LOGGER.log(Level.INFO,
					"drillBaseGroup obtained.");
			responseJson.put(KEY_DATA, drillBaseGroup);
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in ClientRequestAndExpiredService -> serve(FthData).", e);
		}
		return GSON.toJson(responseJson);
	}
}