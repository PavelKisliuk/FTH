/*  By Pavel Kisliuk, 16.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.service.page.TrainerPageService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandTrainerPage} class is {@code FthServletCommand} realization for
 * retrieving main trainer information for page compilation.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandTrainerPage implements FthServletCommand {
	/**
	 * Obtain data for trainer page by trainer id.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return data for trainer page.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong trainerId = (FthLong) request.getSession().getAttribute(ID_ATTRIBUTE);
		TrainerPageService trainerPageService = new TrainerPageService();
		try {
			return trainerPageService.serve(trainerId);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandTrainerPage -> execute(HttpServletRequest)", e);
		}
	}
}