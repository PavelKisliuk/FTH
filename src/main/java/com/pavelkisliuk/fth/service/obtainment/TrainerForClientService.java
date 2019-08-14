/*  By Pavel Kisliuk, 08.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.obtainment;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.model.FthUserMainData;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.AllTrainerByIdSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.ClientUnavailableTrainerSelectSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code TrainerForClientService} class is {@code FthService} realization for
 * list of trainers for client choice.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class TrainerForClientService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Retrieve trainer for client and return it as JSON string.
	 * <p>
	 *
	 * @param clientId is id of client.
	 * @return exercise for client as JSON string.
	 * @throws FthServiceException if {@param clientId} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong clientId) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start TrainerForClientService -> serve(FthLong).");
		if (clientId == null) {
			throw new FthServiceException(
					"null parameter in TrainerForClientService -> serve(FthLong).");
		}

		FthSelectSpecifier selectSpecifier = new AllTrainerByIdSelectSpecifier();
		Map<String, ArrayList<FthUserMainData>> responseJson = new HashMap<>();
		try {
			List<FthData> allTrainerList = FthRepository.INSTANCE.query(selectSpecifier);
			LOGGER.log(Level.INFO,
					"List of trainer's obtained.");
			selectSpecifier = new ClientUnavailableTrainerSelectSpecifier(clientId);
			FthString unavailableTrainer = (FthString) FthRepository.INSTANCE.query(selectSpecifier).get(0);
			LOGGER.log(Level.INFO,
					"Unavailable list of trainer's obtained.");
			String[] unavailable = unavailableTrainer.get().split(",");
			ArrayList<FthUserMainData> availableTrainerGroup = new ArrayList<>();
			for (FthData data : allTrainerList) {
				FthUserMainData trainer = (FthUserMainData) data;
				boolean flag = true;
				for (String trainerId : unavailable) {
					if (trainer.getUserId() == Long.parseLong(trainerId)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					availableTrainerGroup.add(trainer);
				}
			}
			LOGGER.log(Level.INFO,
					"Available list of trainer's created.");
			responseJson.put(KEY_DATA, availableTrainerGroup);
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in TrainerForClientService -> serve(FthData).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish TrainerForClientService -> serve(FthAuthenticationData).");
		return GSON.toJson(responseJson);
	}
}