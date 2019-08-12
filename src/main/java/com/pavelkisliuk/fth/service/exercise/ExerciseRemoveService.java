/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.exercise;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.FthDeleteSpecifier;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;
import com.pavelkisliuk.fth.specifier.delete.RemoveDrillByExerciseDeleteSpecifier;
import com.pavelkisliuk.fth.specifier.delete.RemoveExerciseByIdDeleteSpecifier;
import com.pavelkisliuk.fth.specifier.delete.RemoveSetByExerciseDeleteSpecifier;
import com.pavelkisliuk.fth.specifier.select.LastClientExerciseSelectSpecifier;
import com.pavelkisliuk.fth.specifier.update.ClientRequestUpdateSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * The {@code ExerciseRemoveService} is {@code FthService} realization to removing
 * client exercise and changing his request status.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ExerciseRemoveService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Remove client exercise and change his request to exercise false by transaction.
	 * <p>
	 *
	 * @param clientId is id of client.
	 * @return empty JSON string.
	 * @throws FthServiceException if {@param clientId} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong clientId) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start ExerciseRemoveService -> serve(FthLong).");
		if (clientId == null) {
			throw new FthServiceException(
					"null parameter in ExerciseRemoveService -> serve(FthLong).");
		}

		FthSelectSpecifier selectSpecifier = new LastClientExerciseSelectSpecifier(clientId);
		try {
			FthLong exerciseId = (FthLong) FthRepository.INSTANCE.query(selectSpecifier).get(0);
			ArrayList<FthRepository.TransactionDescriber> transactionList = new ArrayList<>();
			LOGGER.log(Level.INFO,
					"ID of exercise retrieved.");
			FthDeleteSpecifier deleteSpecifier = new RemoveSetByExerciseDeleteSpecifier(exerciseId);
			FthRepository.TransactionDescriber transactionDescriber =
					new FthRepository.TransactionDescriber(
							FthRepository.TransactionOperationType.DELETE, deleteSpecifier);
			transactionList.add(transactionDescriber);
			LOGGER.log(Level.INFO,
					"Set's deleted.");
			deleteSpecifier = new RemoveDrillByExerciseDeleteSpecifier(exerciseId);
			transactionDescriber =
					new FthRepository.TransactionDescriber(
							FthRepository.TransactionOperationType.DELETE, deleteSpecifier);
			transactionList.add(transactionDescriber);
			LOGGER.log(Level.INFO,
					"Drill's deleted.");
			deleteSpecifier = new RemoveExerciseByIdDeleteSpecifier(exerciseId);
			transactionDescriber =
					new FthRepository.TransactionDescriber(
							FthRepository.TransactionOperationType.DELETE, deleteSpecifier);
			transactionList.add(transactionDescriber);
			LOGGER.log(Level.INFO,
					"Exercise deleted.");
			FthUpdateSpecifier updateSpecifier = new ClientRequestUpdateSpecifier(clientId, new FthBoolean(false));
			transactionDescriber =
					new FthRepository.TransactionDescriber(
							FthRepository.TransactionOperationType.UPDATE, updateSpecifier);
			transactionList.add(transactionDescriber);
			LOGGER.log(Level.INFO,
					"Request status updated.");
			FthRepository.INSTANCE.operateTransaction(transactionList);
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in ExerciseRemoveService -> serve(FthLong).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish ExerciseRemoveService -> serve(FthLong).");
		return FthServletCommand.EMPTY_JSON;
	}
}