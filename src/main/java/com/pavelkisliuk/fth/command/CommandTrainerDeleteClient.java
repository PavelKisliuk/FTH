/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.controller.pageservice.TrainerPageService;
import com.pavelkisliuk.fth.controller.removeservice.TrainerRemoveClientService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthLong;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandTrainerDeleteClient} is {@code FthServletCommand} realization to unchain
 * conjunction between trainer and client.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class CommandTrainerDeleteClient implements FthServletCommand {
	/**
	 * Delete conjunction between trainer and client in database and return new
	 * list of trainer client's.
	 * <p>
	 *
	 * @param request is {@code HttpServletRequest} from client.
	 * @return list of trainer client's.
	 * @throws FthCommandException if {@code FthControllerException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong clientId = new CreatorLong().create(request);
		String message;
		try {
			//Unchain.
			new TrainerRemoveClientService().serve(clientId);
			FthLong trainerId = (FthLong) request.getSession().getAttribute(FthServletCommand.TRAINER_ID_ATTRIBUTE);
			//Retrieve list of trainer clients.
			message = new TrainerPageService().serve(trainerId);
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandTrainerDeleteClient -> execute(HttpServletRequest)", e);
		}
		return message;
	}
}