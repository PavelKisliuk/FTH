/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.service.exercise.ExerciseRemoveService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandDiscardExercise} class is {@code FthServletCommand} realization for
 * denying exercise by trainer.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandDenyClientToExercise implements FthServletCommand {
	/**
	 * Deny client exercise.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return data for client page.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong trainerId = ((FthLong) request.getSession().getAttribute(ID_ATTRIBUTE));
		FthLong clientId = new FthLong(Long.parseLong(request.getParameter(CLIENT_ID)));
		if (!ACTION_VALIDATOR.isClientOfTrainer(trainerId, clientId)) {
			throw new FthCommandException(
					"Attempt to change data of client of another trainer" +
							"in CommandDenyClientToExercise -> execute(HttpServletRequest)");
		}
		ExerciseRemoveService exerciseRemoveService = new ExerciseRemoveService();
		try {
			return exerciseRemoveService.serve(clientId);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandDenyClientToExercise -> execute(HttpServletRequest)", e);
		}
	}
}