package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.model.FthLong;

import javax.servlet.http.HttpServletRequest;

class CreatorLong implements FthDataByRequestFactory {
	private static final String LONG_PARAMETER = "long";
	@Override
	public FthLong create(HttpServletRequest request) {
		return new FthLong(Long.parseLong(request.getParameter(LONG_PARAMETER)));
	}
}
