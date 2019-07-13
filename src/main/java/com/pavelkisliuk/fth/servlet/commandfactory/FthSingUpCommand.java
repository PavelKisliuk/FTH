/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.servlet.commandfactory;

import com.pavelkisliuk.fth.controller.singupservice.FthSingUp;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthRegistrationData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@code FthSingUpCommand} class is realization of {@code FthServletCommand} for
 * process data from client from registration page.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthServletCommand
 * @see com.pavelkisliuk.fth.controller.singupservice.FthSingUp
 * @see com.pavelkisliuk.fth.model.FthRegistrationData
 * @since 12.0
 */
class FthSingUpCommand implements FthServletCommand {
	/**
	 * Obtain data and response it to {@code FthSingUp} for processing.
	 * <p>
	 *
	 * @throws FthCommandException if some exception occurred.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws FthCommandException {
		FthRegistrationData registrationData = new FthRegistrationDataCreator().create(request);
		try {
			response.getWriter().write(new FthSingUp().signUp(registrationData));
		} catch (IOException e) {
			throw new FthCommandException("IOException in FthSingUpCommand -> execute().", e);
		} catch (FthControllerException e) {
			throw new FthCommandException("FthControllerException in FthSingUpCommand -> execute().", e);
		}
	}
}