/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.service.updating.FireSeasonPassService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandFireSeasonPass} class is {@code FthServletCommand} realization for
 * fire client season pass.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandFireSeasonPass implements FthServletCommand {
	/**
	 * Fire client season pass.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return message about successful insertion.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong clientId = new FthLong(Long.parseLong(request.getParameter(CLIENT_ID)));
		FthLong trainerId = ((FthLong) request.getSession().getAttribute(ID_ATTRIBUTE));
		if (!ACTION_VALIDATOR.isClientOfTrainer(trainerId, clientId)) {
			throw new FthCommandException(
					"Attempt to change data of client of another trainer" +
							"in CommandFireSeasonPass -> execute(HttpServletRequest)");
		}
		FireSeasonPassService fireSeasonPassService = new FireSeasonPassService();
		try {
			return fireSeasonPassService.serve(clientId);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandFireSeasonPass -> execute(HttpServletRequest).", e);
		}
	}
}