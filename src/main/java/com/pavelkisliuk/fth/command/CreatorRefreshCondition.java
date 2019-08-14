/*  By Pavel Kisliuk, 08.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.model.FthRefreshCondition;

import javax.servlet.http.HttpServletRequest;

class CreatorRefreshCondition implements FthDataByRequestFactory {
	private static final String CONDITION_NAME = "condition_name";
	private static final String CONDITION_QUALITY = "condition_quality";

	@Override
	public FthRefreshCondition create(HttpServletRequest request) {
		FthRefreshCondition refreshCondition = new FthRefreshCondition();
		refreshCondition.setConditionName(request.getParameter(CONDITION_NAME).toUpperCase());
		refreshCondition.setConditionQuality(request.getParameter(CONDITION_QUALITY).toUpperCase());
		return refreshCondition;
	}
}