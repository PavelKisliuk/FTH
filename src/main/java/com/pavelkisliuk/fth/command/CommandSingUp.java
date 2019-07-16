/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.controller.singupservice.FthSingUpService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthRegistrationData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
	public void execute(HttpServletRequest request, HttpServletResponse response) throws FthCommandException {
		FthRegistrationData registrationData = new CreatorRegistrationData().create(request);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(new FthSingUpService().signUp(registrationData));
		} catch (IOException e) {
			throw new FthCommandException("IOException in CommandSingUp -> execute().", e);
		} catch (FthControllerException e) {
			throw new FthCommandException("FthControllerException in CommandSingUp -> execute().", e);
		}
	}
}