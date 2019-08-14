/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.service.exercise.FinishExerciseService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandFinishExercise} class is {@code FthServletCommand} realization for
 * finished exercise (update set's, client season pass).
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandFinishExercise implements FthServletCommand {
	/**
	 * Finish exercise by updating specify data.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return data for client page.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthString jsonSetGroup = new FthString(request.getParameter(SET_GROUP));
		FthLong clientId = (FthLong) request.getSession().getAttribute(ID_ATTRIBUTE);
		FinishExerciseService finishExerciseService = new FinishExerciseService(clientId);
		try {
			return finishExerciseService.serve(jsonSetGroup);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandFinishExercise -> execute(HttpServletRequest).", e);
		}
	}
}