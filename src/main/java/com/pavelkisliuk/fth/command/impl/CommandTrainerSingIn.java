/*  By Pavel Kisliuk, 16.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.service.session.ObtainmentIdService;
import com.pavelkisliuk.fth.service.signin.TrainerSingInService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
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
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthAuthenticationData authenticationData = new CreatorAuthenticationData().create(request);
		TrainerSingInService trainerSingInService = new TrainerSingInService();

		String message;
		try {
			message = trainerSingInService.serve(authenticationData);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandTrainerSingIn -> execute(HttpServletRequest)", e);
		}
		if (trainerSingInService.isTrainerExist()) {
			specifySession(request, authenticationData);
		}
		return message;
	}

	/**
	 * Specify trainer {@code ID_ATTRIBUTE} in session.
	 * <p>
	 *
	 * @param request            is request from user.
	 * @param authenticationData is e-mail and password of trainer.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	private void specifySession(HttpServletRequest request, FthAuthenticationData authenticationData)
			throws FthCommandException {
		FthLong id;
		try {
			String stringLong = new ObtainmentIdService().serve(authenticationData);
			id = new FthLong(Long.parseLong(stringLong));
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandTrainerSingIn -> execute(HttpServletRequest)", e);
		}
		request.getSession().setAttribute(ID_ATTRIBUTE, id);
		request.getSession().setAttribute(ROLE_ATTRIBUTE, TRAINER_ROLE);
	}
}