/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.page.ClientPageService;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.TrainerIdByClientSelectSpecifier;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandClientPage} class is {@code FthServletCommand} realization for
 * retrieving main client information for page compilation.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandClientPage implements FthServletCommand {
	/**
	 * Obtain data for client page by client id.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return data for client page.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong clientId = (FthLong) request.getSession().getAttribute(ID_ATTRIBUTE);
		FthSelectSpecifier selectSpecifier = new TrainerIdByClientSelectSpecifier(clientId);
		FthLong trainerId;
		try {
			trainerId = (FthLong) FthRepository.INSTANCE.query(selectSpecifier).get(0);
		} catch (FthRepositoryException e) {
			throw new FthCommandException(
					"FthRepositoryException in CommandClientPage -> execute(HttpServletRequest)", e);
		}
		if (trainerId.get() == 0) {
			return WITHOUT_TRAINER;
		}
		ClientPageService clientPageService = new ClientPageService();
		try {
			return clientPageService.serve(clientId);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandClientPage -> execute(HttpServletRequest)", e);
		}
	}
}