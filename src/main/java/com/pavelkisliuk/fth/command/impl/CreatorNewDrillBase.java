package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.model.FthDrillBase;

import javax.servlet.http.HttpServletRequest;

class CreatorNewDrillBase implements FthDataByRequestFactory {
	private static final String DRILL_NAME = "name";
	private static final String MUSCLE_GROUP_ID = "muscle";

	@Override
	public FthDrillBase create(HttpServletRequest request) throws FthCommandException {
		FthDrillBase drillBase = new FthDrillBase();
		drillBase.setDrillName(request.getParameter(DRILL_NAME));
		drillBase.setMuscleGroupId(Long.parseLong(request.getParameter(MUSCLE_GROUP_ID)));
		return drillBase;
	}
}