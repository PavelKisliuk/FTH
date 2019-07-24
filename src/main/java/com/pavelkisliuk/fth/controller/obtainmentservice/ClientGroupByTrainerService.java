/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.controller.obtainmentservice;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.ClientGroupByTrainerSpecifier;
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
public class ClientGroupByTrainerService implements FthService {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Query list of client's from database by ID of the trainer.
	 * <p>
	 *
	 * @param data is id of trainer to find all his client's.
	 * @return list of client's.
	 * @throws FthControllerException if {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthData data) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start ClientGroupByTrainerService -> serve(FthData).");
		if (data == null ||
				data.getClass() != FthLong.class) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in ClientGroupByTrainerService -> serve(FthData)!!!");
			return "";
		}

		Map<String, List<FthData>> responseJson = new HashMap<>();
		FthLong trainerId = (FthLong) data;

		List<FthData> clientGroup;
		try {
			LOGGER.log(Level.INFO,
					"Start obtaining list of clients.");
			clientGroup = FthRepository.INSTANCE.query(new ClientGroupByTrainerSpecifier(trainerId));
			LOGGER.log(Level.INFO,
					"List retrieved.");
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in ClientGroupByTrainerService -> serve(FthData).", e);
		}
		responseJson.put("client", clientGroup);
		String message = new Gson().toJson(responseJson);
		LOGGER.log(Level.DEBUG,
				"Finish ClientGroupByTrainerService -> serve(FthData).");
		return message;
	}
}