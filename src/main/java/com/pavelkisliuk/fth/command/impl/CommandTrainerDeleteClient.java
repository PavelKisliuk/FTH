/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.service.obtainment.ClientGroupByTrainerService;
import com.pavelkisliuk.fth.service.remove.TrainerRemoveClientService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandTrainerDeleteClient} class is {@code FthServletCommand} realization to unchain
 * conjunction between trainer and client and retrieve new list of trainer's client's.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandTrainerDeleteClient implements FthServletCommand {

	/**
	 * Delete conjunction between trainer and client in database and return new
	 * list of trainer's client's.
	 * <p>
	 *
	 * @param request is {@code HttpServletRequest} from client.
	 * @return list of trainer's client's.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong clientId = new CreatorLong().create(request);
		try {
			//Unchain.
			TrainerRemoveClientService trainerRemoveClientService = new TrainerRemoveClientService();
			trainerRemoveClientService.serve(clientId);
			FthLong trainerId = (FthLong) request.getSession().getAttribute(FthServletCommand.ID_ATTRIBUTE);
			//Retrieve list of trainer clients.
			ClientGroupByTrainerService clientGroupByTrainerService = new ClientGroupByTrainerService();
			return clientGroupByTrainerService.serve(trainerId);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandTrainerDeleteClient -> execute(HttpServletRequest)", e);
		}
	}
}