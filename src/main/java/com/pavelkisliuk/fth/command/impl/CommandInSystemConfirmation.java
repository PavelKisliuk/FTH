/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.service.page.PageRedirectionService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandInSystemConfirmation} class is {@code FthServletCommand} realization for
 * determination whether user in system.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandInSystemConfirmation implements FthServletCommand {

	/**
	 * Define page for redirection.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return page for redirection as JSON string.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		try {
			FthLong id = (FthLong) request.getSession().getAttribute(ID_ATTRIBUTE);
			String role = (String) request.getSession().getAttribute(ROLE_ATTRIBUTE);
			FthString fthRole = new FthString(role);

			FthString page = new CreatorString().create(request);

			PageRedirectionService pageRedirectionService = new PageRedirectionService(id, fthRole);
			return pageRedirectionService.serve(page);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandInSystemConfirmation -> execute(HttpServletRequest)", e);
		}
	}
}