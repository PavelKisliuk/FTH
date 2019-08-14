/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.command.impl.TrainerActionValidator;
import com.pavelkisliuk.fth.exception.FthCommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code FthServletCommand} is interface described behaviour of server which obtain data from client.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public interface FthServletCommand {
	/**
	 * Mark of ID attribute.
	 */
	String ID_ATTRIBUTE = "ID";

	String ROLE_ATTRIBUTE = "role";

	String TRAINER_ROLE = "trainer";
	String CLIENT_ROLE = "client";


	String CLIENT_ID = "clientId";
	String TRAINER_ID = "trainerId";


	String SET_GROUP = "setGroup";
	String WITHOUT_TRAINER = "{\"without\" : \"true\"}";

	/**
	 * Representation of empty JSON string.
	 */
	String EMPTY_JSON = "\"\"";

	TrainerActionValidator ACTION_VALIDATOR = new TrainerActionValidator();
	/**
	 * Method obtain data from client and decide how to act with it.
	 * <p>
	 *
	 * @throws FthCommandException if some exceptions occurred.
	 */
	String execute(HttpServletRequest request) throws FthCommandException;
}