/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.session.ObtainmentIdService;
import com.pavelkisliuk.fth.service.signin.ClientSignInService;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.TrainerIdByClientSelectSpecifier;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandClientSignIn} class is {@code FthServletCommand} realization for
 * processing client entrance in system.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class CommandClientSignIn implements FthServletCommand {
	/**
	 * Determine existence of client in system. If trainer exist return client page, otherwise return
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
		ClientSignInService trainerSingInService = new ClientSignInService();

		String message;
		try {
			message = trainerSingInService.serve(authenticationData);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandTrainerSingIn -> execute(HttpServletRequest)", e);
		}
		if (trainerSingInService.isClientExist()) {
			specifySession(request, authenticationData);
		}
		return message;
	}

	/**
	 * Specify client {@code ID_ATTRIBUTE} in session.
	 * <p>
	 *
	 * @param request            is request from user.
	 * @param authenticationData is e-mail and password of client.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	private void specifySession(HttpServletRequest request, FthAuthenticationData authenticationData)
			throws FthCommandException {
		FthLong clientId;
		FthLong trainerId;
		try {
			String stringLong = new ObtainmentIdService().serve(authenticationData);
			clientId = new FthLong(Long.parseLong(stringLong));
			FthSelectSpecifier selectSpecifier = new TrainerIdByClientSelectSpecifier(clientId);
			trainerId = (FthLong) FthRepository.INSTANCE.query(selectSpecifier);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandTrainerSingIn -> execute(HttpServletRequest)", e);
		} catch (FthRepositoryException e) {
			throw new FthCommandException(
					"FthRepositoryException in CommandTrainerSingIn -> execute(HttpServletRequest)", e);
		}
		request.getSession().setAttribute(ID_ATTRIBUTE, clientId);
		request.getSession().setAttribute(TRAINER_ID, trainerId);
		request.getSession().setAttribute(ROLE_ATTRIBUTE, CLIENT_ROLE);
	}
}