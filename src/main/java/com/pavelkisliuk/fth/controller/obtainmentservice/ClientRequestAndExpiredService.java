/*  By Pavel Kisliuk, 07.08.2019
 *  This is class for education and nothing rights don't reserved.
 */
package com.pavelkisliuk.fth.controller.obtainmentservice;

import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthClientPublicData;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.ClientRequestExpiredByIdSelectSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code ClientRequestAndExpiredService} class is {@code FthService} realization for
 * retrieving information about expiration of client's season's and request's to exercises.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ClientRequestAndExpiredService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Retrieve information about client season condition (expired day, amount of visitation) and condition of
	 * request to exercise.
	 * <p>
	 *
	 * @param clientId is id of client.
	 * @return information about client season condition and condition of
	 * request to exercise as JSON string.
	 * @throws FthControllerException if {@param clientId} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong clientId) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start ClientRequestAndExpiredService -> serve(FthLong).");
		if (clientId == null) {
			throw new FthControllerException(
					"null parameter in ClientRequestAndExpiredService -> serve(FthLong).");
		}

		Map<String, FthData> responseJson = new HashMap<>();
		try {
			FthClientPublicData clientPublicData =
					(FthClientPublicData) FthRepository.INSTANCE.query(
							new ClientRequestExpiredByIdSelectSpecifier(clientId)).get(0);
			responseJson.put(KEY_DATA, clientPublicData);
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in ClientRequestAndExpiredService -> serve(FthData).", e);
		}
		return GSON.toJson(responseJson);
	}
}