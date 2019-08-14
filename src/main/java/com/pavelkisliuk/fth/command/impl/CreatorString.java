package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.model.FthString;

import javax.servlet.http.HttpServletRequest;

class CreatorString implements FthDataByRequestFactory {
	@Override
	public FthString create(HttpServletRequest request) {
		return new FthString(request.getParameter("str"));
	}
}