/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.controller.obtainmentservice.ClientGroupByTrainerService;
import com.pavelkisliuk.fth.controller.removeservice.TrainerRemoveClientService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthLong;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandTrainerDeleteClient} is {@code FthServletCommand} realization to unchain
 * conjunction between trainer and client and retrieve new list of trainer's client's.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class CommandTrainerDeleteClient implements FthServletCommand {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Delete conjunction between trainer and client in database and return new
	 * list of trainer's client's.
	 * <p>
	 *
	 * @param request is {@code HttpServletRequest} from client.
	 * @return list of trainer's client's.
	 * @throws FthCommandException if {@code FthControllerException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		LOGGER.log(Level.DEBUG,
				"Start CommandTrainerDeleteClient -> execute(HttpServletRequest).");
		if (request == null ||
				request.getClass() != HttpServletRequest.class) {
			LOGGER.log(Level.ERROR,
					"Incorrect parameter in CommandTrainerDeleteClient -> execute(HttpServletRequest)!!!");
			return "";
		}

		LOGGER.log(Level.INFO,
				"Start creating clientId.");
		FthLong clientId = new CreatorLong().create(request);
		LOGGER.log(Level.INFO,
				"clientId created.");

		String message;
		try {
			LOGGER.log(Level.INFO,
					"Start unchaining and obtaining trainer data.");
			//Unchain.
			new TrainerRemoveClientService().serve(clientId);
			FthLong trainerId = (FthLong) request.getSession().getAttribute(FthServletCommand.TRAINER_ID_ATTRIBUTE);
			//Retrieve list of trainer clients.
			message = new ClientGroupByTrainerService().serve(trainerId);
			LOGGER.log(Level.INFO,
					"Conjunction unchained, data retrieved.");
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandTrainerDeleteClient -> execute(HttpServletRequest)", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish CommandTrainerDeleteClient -> execute(HttpServletRequest).");
		return message;
	}
}