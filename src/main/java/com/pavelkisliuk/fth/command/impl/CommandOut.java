package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;

import javax.servlet.http.HttpServletRequest;

class CommandOut implements FthServletCommand {
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		request.getSession().invalidate();
		return EMPTY_JSON;
	}
}
