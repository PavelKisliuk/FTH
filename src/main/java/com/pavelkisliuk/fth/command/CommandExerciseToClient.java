/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.service.obtainment.ExerciseToClientService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandExerciseToClient} class is {@code FthServletCommand} realization for
 * retrieving exercise to client.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandExerciseToClient implements FthServletCommand {
	/**
	 * Obtain client exercise.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return data for client page.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong clientId = (FthLong) request.getSession().getAttribute(ID_ATTRIBUTE);
		ExerciseToClientService exerciseToClientService = new ExerciseToClientService();
		try {
			return exerciseToClientService.serve(clientId);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandExerciseToClient -> execute(HttpServletRequest)", e);
		}
	}
}