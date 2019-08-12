/*  By Pavel Kisliuk, 13.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthDrillBase;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.service.updating.CreateDrillBaseService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandCreateDrillBase} class is {@code FthServletCommand} realization for
 * new drill creation.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class CommandCreateDrillBase implements FthServletCommand {
	/**
	 * Create new drill.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return message about successful insertion.
	 * @throws FthCommandException if {@code FthServiceException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong trainerId = (FthLong) request.getSession().getAttribute(ID_ATTRIBUTE);
		FthDrillBase drillBase = new CreatorNewDrillBase().create(request);
		CreateDrillBaseService createDrillBaseService = new CreateDrillBaseService(trainerId);
		try {
			return createDrillBaseService.serve(drillBase);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandCreateDrillBase -> execute(HttpServletRequest)", e);
		}
	}
}