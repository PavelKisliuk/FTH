/*  By Pavel Kisliuk, 16.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.controller.sessionservice.ObtainmentIdService;
import com.pavelkisliuk.fth.controller.signinservice.TrainerSingInService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.model.FthLong;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandTrainerSingIn} class is {@code FthServletCommand} realization for
 * processing trainer entrance in system.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandTrainerSingIn implements FthServletCommand {
	/**
	 * Determine existence of trainer in system. If trainer exist return trainer page, otherwise return
	 * error message.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return redirect page if necessary, or return empty JSON string.
	 * @throws FthCommandException if {@code FthControllerException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthAuthenticationData authenticationData = new CreatorAuthenticationData().create(request);
		TrainerSingInService trainerSingInService = new TrainerSingInService();

		String message;
		try {
			message = trainerSingInService.serve(authenticationData);
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandTrainerSingIn -> execute(HttpServletRequest)", e);
		}
		if (trainerSingInService.isTrainerExist()) {
			manipulateSession(request, authenticationData);
		}
		return message;
	}

	/**
	 * Specify trainer {@code ID_ATTRIBUTE} in session.
	 * <p>
	 *
	 * @param request            is request from user.
	 * @param authenticationData is e-mail and password of trainer.
	 * @throws FthCommandException if {@code FthControllerException} occurred.
	 */
	private void manipulateSession(HttpServletRequest request, FthAuthenticationData authenticationData)
			throws FthCommandException {
		FthLong fthLong;
		try {
			String stringLong = new ObtainmentIdService().serve(authenticationData);
			fthLong = new FthLong(Long.parseLong(stringLong));
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandTrainerSingIn -> execute(HttpServletRequest)", e);
		}
		request.getSession().setAttribute(ID_ATTRIBUTE, fthLong);
	}
}