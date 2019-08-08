/*  By Pavel Kisliuk, 08.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.controller.obtainmentservice.ConditionClientGroupByTrainerService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthRefreshCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CommandRefreshClientGroup} class is {@code FthServletCommand} realization for
 * retrieving list of client's which satisfy designate condition's.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class CommandRefreshClientGroup implements FthServletCommand {
	/**
	 * Create {@code FthRefreshCondition} instance by request and obtain specify list of client's by it.
	 * <p>
	 *
	 * @param request is request from user.
	 * @return specify list of client's.
	 * @throws FthCommandException if {@code FthControllerException} occurred.
	 */
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthRefreshCondition refreshCondition = new CreatorRefreshCondition().create(request);
		refreshCondition.setTrainerId(((FthLong) request.getSession().getAttribute(ID_ATTRIBUTE)).get());
		try {
			return new ConditionClientGroupByTrainerService().serve(refreshCondition);
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandRefreshClientGroup -> execute(HttpServletRequest)", e);
		}
	}
}