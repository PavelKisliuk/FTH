/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.obtainment;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.select.AllClientByTrainerSelectSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code ClientGroupByTrainerService} is {@code FthService} realization to retrieving
 * list of client's of specify trainer.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ClientGroupByTrainerService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Query list of client's from database by ID of the trainer.
	 * <p>
	 *
	 * @param trainerId is id of trainer to find all his client's.
	 * @return list of client's.
	 * @throws FthServiceException if {@param trainerId} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong trainerId) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start ClientGroupByTrainerService -> serve(FthLong).");
		if (trainerId == null) {
			throw new FthServiceException(
					"null parameter in ClientGroupByTrainerService -> serve(FthLong).");
		}

		Map<String, List<FthData>> responseJson = new HashMap<>();
		List<FthData> clientGroup;
		try {
			LOGGER.log(Level.INFO,
					"Start obtaining list of clients.");
			clientGroup = FthRepository.INSTANCE.query(new AllClientByTrainerSelectSpecifier(trainerId));
			LOGGER.log(Level.INFO,
					"List retrieved.");
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in ClientGroupByTrainerService -> serve(FthLong).", e);
		}
		responseJson.put("client", clientGroup);
		LOGGER.log(Level.DEBUG,
				"Finish ClientGroupByTrainerService -> serve(FthLong).");
		return GSON.toJson(responseJson);
	}
}