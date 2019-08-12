/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.exercise;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;
import com.pavelkisliuk.fth.specifier.insert.NewExerciseInsertSpecifier;
import com.pavelkisliuk.fth.specifier.update.ClientRequestUpdateSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code ClientExerciseRequestService} class is {@code FthService} realization for
 * exercise requesting.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ClientExerciseRequestService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Create new exercise field and update request condition of client.
	 * <p>
	 *
	 * @param clientId is id of client.
	 * @return success message.
	 * @throws FthServiceException if {@param clientId} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong clientId) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start ClientExerciseRequestService -> serve(FthLong).");
		if (clientId == null) {
			throw new FthServiceException(
					"null parameter in ClientExerciseRequestService -> serve(FthLong).");
		}

		ArrayList<FthRepository.TransactionDescriber> transactionList = new ArrayList<>();
		FthInsertSpecifier insertSpecifier = new NewExerciseInsertSpecifier(clientId);
		FthRepository.TransactionDescriber transactionDescriber =
				new FthRepository.TransactionDescriber(
						FthRepository.TransactionOperationType.INSERT, insertSpecifier);
		transactionList.add(transactionDescriber);
		LOGGER.log(Level.INFO,
				"New exercise row inserted.");
		FthUpdateSpecifier updateSpecifier = new ClientRequestUpdateSpecifier(clientId, new FthBoolean(true));
		transactionDescriber =
				new FthRepository.TransactionDescriber(
						FthRepository.TransactionOperationType.UPDATE, updateSpecifier);
		transactionList.add(transactionDescriber);
		LOGGER.log(Level.INFO,
				"Request condition updated.");
		try {
			FthRepository.INSTANCE.operateTransaction(transactionList);
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in ClientExerciseRequestService -> serve(FthLong).", e);
		}
		Map<String, String> responseJson = new HashMap<>();
		responseJson.put(KEY_MESSAGE, EXERCISE_REQUESTED);
		LOGGER.log(Level.DEBUG,
				"Finish ClientExerciseRequestService -> serve(FthAuthenticationData).");
		return GSON.toJson(responseJson);
	}
}