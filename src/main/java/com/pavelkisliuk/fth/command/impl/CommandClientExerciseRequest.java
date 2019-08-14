/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.service.exercise.ClientExerciseRequestService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandClientExerciseRequest} class is {@code FthServletCommand} realization for
 * creation new exercise for client.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandClientExerciseRequest implements FthServletCommand {
	/**
	 * Create new exercise for client.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return data for client page.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong clientId = (FthLong) request.getSession().getAttribute(ID_ATTRIBUTE);
		ClientExerciseRequestService clientExerciseRequestService = new ClientExerciseRequestService();
		try {
			return clientExerciseRequestService.serve(clientId);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandClientExerciseRequest -> execute(HttpServletRequest)", e);
		}
	}
}