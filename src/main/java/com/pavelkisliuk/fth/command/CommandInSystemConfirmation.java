/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.controller.pageservice.PageRedirectionService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthString;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandInSystemConfirmation} class is {@code FthServletCommand} realization for
 * determination whether user in system.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class CommandInSystemConfirmation implements FthServletCommand {
	/**
	 * Expected condition of session for further actions.
	 * We can redirect to another page if already in system, or if not in system,
	 * and condition designate it.
	 * E.g. on authentication page we redirect to main page if session established,
	 * but on main page we redirect if session NOT established.
	 */
	private static final String CONDITION = "condition";

	/**
	 * Determine condition of user in system and in terms of {@code CONDITION} decide
	 * redirect his to another page or not.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return redirect page if necessary, or return empty JSON string.
	 * @throws FthCommandException if {@code FthControllerException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthString redirectionPage = new CreatorString().create(request);
		String message = EMPTY_JSON;
		try {
			//check is session already established for special ID
			boolean inSystemFlag = request.getSession().getAttribute(ID_ATTRIBUTE) != null;
			boolean condition = Boolean.parseBoolean(request.getParameter(CONDITION));
			if (inSystemFlag == condition) {
				message = new PageRedirectionService().serve(redirectionPage);
			}
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandInSystemConfirmation -> execute(HttpServletRequest)", e);
		}
		return message;
	}
}