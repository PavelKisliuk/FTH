/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.service.singup.FthSingUpService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthRegistrationData;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandSingUp} class is realization of {@code FthServletCommand} for
 * process data from client from registration page.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthServletCommand
 * @see FthSingUpService
 * @see com.pavelkisliuk.fth.model.FthRegistrationData
 * @since 12.0
 */
class CommandSingUp implements FthServletCommand {
	/**
	 * Obtain data and response it to {@code FthSingUpService} for processing.
	 * <p>
	 *
	 * @throws FthCommandException if some exception occurred.
	 */
	@Override
	public String  execute(HttpServletRequest request) throws FthCommandException {
		FthRegistrationData registrationData = new CreatorRegistrationData().create(request);
		String message;
		try {
			message = new FthSingUpService().serve(registrationData);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandSingUp -> execute(HttpServletRequest)", e);
		}
		return message;
	}
}