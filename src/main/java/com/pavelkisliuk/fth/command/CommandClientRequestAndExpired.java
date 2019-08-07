/*  By Pavel Kisliuk, 07.08.2019
 *  This is class for education and nothing rights don't reserved.
 */
package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.controller.obtainmentservice.ClientRequestAndExpiredService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthLong;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandClientRequestAndExpired} class is {@code FthServletCommand} realization for
 * retrieving conditions of client's season's and exercises request's.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class CommandClientRequestAndExpired implements FthServletCommand {
	/**
	 * Obtain conditions of client's season's and exercises request's.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return data about conditions of client's season's and exercises request's.
	 * @throws FthCommandException if {@code FthControllerException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong clientId = new CreatorLong().create(request);
		try {
			return new ClientRequestAndExpiredService().serve(clientId);
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandClientRequestAndExpired -> execute(HttpServletRequest)", e);
		}
	}
}