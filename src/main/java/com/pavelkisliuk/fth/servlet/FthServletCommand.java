/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.servlet;

import com.pavelkisliuk.fth.exception.FthServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code FthServletCommand} is interface described behaviour of server which obtain data from client.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
interface FthServletCommand {
	/**
	 * Method obtain data from client and decide how to act with it.
	 * <p>
	 *
	 * @throws FthServletException if some exceptions occurred.
	 */
	void execute(HttpServletRequest request, HttpServletResponse response) throws FthServletException;
}