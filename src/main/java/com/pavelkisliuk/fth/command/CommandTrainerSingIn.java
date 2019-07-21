/*  By Pavel Kisliuk, 16.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.controller.sessionservice.ObtainmentIdService;
import com.pavelkisliuk.fth.controller.signinservice.FthTrainerSingInService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthServletException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.model.FthLong;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandTrainerSingIn} class is realization of {@code FthServletCommand} for
 * process data from client from registration page.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthServletCommand
 * @see com.pavelkisliuk.fth.model.FthRegistrationData
 * @since 12.0
 */
class CommandTrainerSingIn implements FthServletCommand {
	/**
	 * @throws FthCommandException
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthAuthenticationData authenticationData = new CreatorAuthenticationData().create(request);
		FthTrainerSingInService trainerSingInService = new FthTrainerSingInService();
		String message;
		try {
			message = trainerSingInService.serve(authenticationData);
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandTrainerSingIn -> execute()", e);
		}
		if (trainerSingInService.isTrainerExist()) {
			manipulateSession(request, authenticationData);
		}
		return message;
	}

	private void manipulateSession(HttpServletRequest request, FthAuthenticationData authenticationData)
			throws FthCommandException {
		FthLong fthLong;
		try {
			String stringLong = new ObtainmentIdService().serve(authenticationData);
			fthLong = new FthLong(Long.valueOf(stringLong));
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandTrainerSingIn -> execute()", e);
		}
		request.getSession().setAttribute(FthServletCommand.TRAINER_ID_ATTRIBUTE, fthLong);
	}
}