package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.model.FthData;

import javax.servlet.http.HttpServletRequest;

class CreatorSetFromClient implements FthDataByRequestFactory {
	private static final String SET_GROUP = "setGroup";

	@Override
	public FthData create(HttpServletRequest request) throws FthCommandException {
		String jsonExercise = request.getParameter(SET_GROUP);
		return null;
	}
}
