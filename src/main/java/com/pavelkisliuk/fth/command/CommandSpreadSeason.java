/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */
package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthClientPublicData;
import com.pavelkisliuk.fth.service.updating.SpreadSeasonService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandSpreadSeason} class is {@code FthServletCommand} realization for
 * spreading client season pass.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class CommandSpreadSeason implements FthServletCommand {
	/**
	 * Spread client season pass.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return data for client page.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthClientPublicData clientPublicData = new CreatorClientPublicDataSeasonPass().create(request);
		SpreadSeasonService spreadSeasonService = new SpreadSeasonService();
		try {
			return spreadSeasonService.serve(clientPublicData);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandSpreadSeason -> execute(HttpServletRequest).", e);
		}
	}
}