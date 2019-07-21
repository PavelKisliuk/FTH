/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
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
public interface FthServletCommand {
	String TRAINER_ID_ATTRIBUTE = "trainerId";
	/**
	 * Method obtain data from client and decide how to act with it.
	 * <p>
	 *
	 * @throws FthCommandException if some exceptions occurred.
	 */
	String execute(HttpServletRequest request) throws FthCommandException;
}