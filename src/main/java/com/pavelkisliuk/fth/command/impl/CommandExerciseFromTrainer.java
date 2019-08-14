/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.exercise.ExerciseComponent;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.exercise.ExerciseFromTrainerService;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.IsExerciseRequestedSelectSpecifier;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandExerciseFromTrainer} class is {@code FthServletCommand} realization for
 * insertion exercise from trainer.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandExerciseFromTrainer implements FthServletCommand {
	/**
	 * Obtain exercise from request and insert it.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return message about successful insertion.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong trainerId = ((FthLong) request.getSession().getAttribute(ID_ATTRIBUTE));
		FthLong clientId = new FthLong(Long.parseLong(request.getParameter(CLIENT_ID)));
		if (!ACTION_VALIDATOR.isClientOfTrainer(trainerId, clientId)) {
			throw new FthCommandException(
					"Attempt to change data of client of another trainer" +
							"in CommandExerciseFromTrainer -> execute(HttpServletRequest)");
		}
		if (!isRequested(clientId)) {
			throw new FthCommandException(
					"Attempt to change data of client without exercise request" +
							"in CommandExerciseFromTrainer -> execute(HttpServletRequest)");
		}
		ExerciseComponent exerciseComponent = new CreatorExerciseFromTrainer().create(request);
		ExerciseFromTrainerService exerciseFromTrainerService = new ExerciseFromTrainerService();
		try {
			return exerciseFromTrainerService.serve(exerciseComponent);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandExerciseFromTrainer -> execute(HttpServletRequest).", e);
		}
	}

	/**
	 * Verify client request to exercise.
	 * <p>
	 *
	 * @param clientId is ID of client.
	 * @return {@code true} if client requested exercise, otherwise return {@code false}.
	 * @throws FthCommandException if {@code FthRepositoryException} occurred.
	 */
	private boolean isRequested(FthLong clientId) throws FthCommandException {
		FthSelectSpecifier selectSpecifier = new IsExerciseRequestedSelectSpecifier(clientId);
		try {
			FthBoolean isRequested = (FthBoolean) FthRepository.INSTANCE.query(selectSpecifier).get(0);
			return isRequested.get();
		} catch (FthRepositoryException e) {
			throw new FthCommandException(
					"FthRepositoryException in CommandExerciseFromTrainer -> isRequested(FthLong).", e);
		}
	}
}