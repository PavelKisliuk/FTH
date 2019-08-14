/*  By Pavel Kisliuk, 07.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.service.obtainment.DrillBaseByTrainerService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandDrillBase} class is {@code FthServletCommand} realization for
 * retrieving list of drills of trainer.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandDrillBase implements FthServletCommand {
	/**
	 * Obtain list of drills created by concrete trainer.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return list of drills created by concrete trainer.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong trainerId = (FthLong) request.getSession().getAttribute(ID_ATTRIBUTE);
		try {
			return new DrillBaseByTrainerService().serve(trainerId);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandDrillBase -> execute(HttpServletRequest).", e);
		}
	}
}