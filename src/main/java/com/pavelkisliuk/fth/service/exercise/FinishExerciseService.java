/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.exercise;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthInt;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthSetFromClient;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;
import com.pavelkisliuk.fth.specifier.update.CurrentDateExerciseUpdateSpecifier;
import com.pavelkisliuk.fth.specifier.update.SeasonPassUpdateSpecifier;
import com.pavelkisliuk.fth.specifier.update.SetFromClientUpdateSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * The {@code FinishExerciseService} is {@code FthService} realization for
 * setting et's information.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class FinishExerciseService implements FthService<FthString> {
	private static final Logger LOGGER = LogManager.getLogger();

	private FthLong clientId;

	public FinishExerciseService(FthLong clientId) {
		this.clientId = clientId;
	}

	/**
	 * Update client information about set's and decrement amount of rest visitation's.
	 * <p>
	 *
	 * @param jsonSetGroup is set's from client.
	 * @return empty json message.
	 * @throws FthServiceException iif {@param jsonSetGroup} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthString jsonSetGroup) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start FinishExerciseService -> serve(FthString).");
		if (jsonSetGroup == null) {
			throw new FthServiceException(
					"null parameter in FinishExerciseService -> serve(FthString).");
		}

		FthSetFromClient[] setFromClientGroup =
				FthService.GSON.fromJson(jsonSetGroup.get(), (Type) FthSetFromClient[].class);
		LOGGER.log(Level.INFO,
				"Set's translated.");
		ArrayList<FthRepository.TransactionDescriber> transactionList = new ArrayList<>();
		FthUpdateSpecifier updateSpecifier;
		FthRepository.TransactionDescriber transactionDescriber;
		for (FthSetFromClient setFromClient : setFromClientGroup) {
			updateSpecifier = new SetFromClientUpdateSpecifier(setFromClient);
			transactionDescriber =
					new FthRepository.TransactionDescriber(
							FthRepository.TransactionOperationType.UPDATE, updateSpecifier);
			transactionList.add(transactionDescriber);
		}
		LOGGER.log(Level.INFO,
				"Set's updated.");
		FthInt decrement = new FthInt(-1);
		updateSpecifier = new SeasonPassUpdateSpecifier(decrement, clientId);
		transactionDescriber =
				new FthRepository.TransactionDescriber(
						FthRepository.TransactionOperationType.UPDATE, updateSpecifier);
		transactionList.add(transactionDescriber);
		LOGGER.log(Level.INFO,
				"Season pass updated.");
		updateSpecifier = new CurrentDateExerciseUpdateSpecifier(clientId);
		transactionDescriber =
				new FthRepository.TransactionDescriber(
						FthRepository.TransactionOperationType.UPDATE, updateSpecifier);
		transactionList.add(transactionDescriber);
		LOGGER.log(Level.INFO,
				"Date of exercise updated.");
		try {
			FthRepository.INSTANCE.operateTransaction(transactionList);
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in FinishExerciseService -> serve(FthLong).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish FinishExerciseService -> serve(FthLong).");
		return FthServletCommand.EMPTY_JSON;
	}
}