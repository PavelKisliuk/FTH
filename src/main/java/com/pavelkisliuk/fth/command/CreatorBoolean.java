package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.model.FthBoolean;

import javax.servlet.http.HttpServletRequest;

class CreatorBoolean implements FthDataByRequestFactory {
	private static final String BOOL_PARAMETER = "bool";

	@Override
	public FthBoolean create(HttpServletRequest request) {
		return new FthBoolean(Boolean.parseBoolean(request.getParameter(BOOL_PARAMETER)));
	}
}
