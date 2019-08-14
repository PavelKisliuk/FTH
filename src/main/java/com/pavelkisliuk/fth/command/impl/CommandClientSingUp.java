/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthRegistrationData;
import com.pavelkisliuk.fth.service.singup.SingUpService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandSingUp} class is realization of {@code FthServletCommand} for
 * process data from client from registration page.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthServletCommand
 * @see SingUpService
 * @see com.pavelkisliuk.fth.model.FthRegistrationData
 * @since 12.0
 */
class CommandClientSingUp implements FthServletCommand {
	/**
	 * Obtain data and response it to {@code FthSingUpService} for processing.
	 * <p>
	 *
	 * @throws FthCommandException if some exception occurred.
	 */
	@Override
	public String  execute(HttpServletRequest request) throws FthCommandException {
		FthRegistrationData registrationData = new CreatorRegistrationData().create(request);
		FthBoolean isTrainer = new CreatorBoolean().create(request);
		SingUpService singUpService = new SingUpService(isTrainer);
		try {
			return singUpService.serve(registrationData);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandSingUp -> execute(HttpServletRequest)", e);
		}
	}
}