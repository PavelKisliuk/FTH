package com.pavelkisliuk.fth.command;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.controller.pageservice.PageRedirectionService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthString;

import javax.servlet.http.HttpServletRequest;

public class CommandInSystemConfirmation implements FthServletCommand {
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthString page = new CreatorString().create(request);
		String message = "\"\"";
		try {
			boolean inSystemFlag = request.getSession().getAttribute(FthServletCommand.TRAINER_ID_ATTRIBUTE) != null;
			boolean condition = Boolean.valueOf(request.getParameter("condition"));
			if (inSystemFlag == condition) {
				message = new PageRedirectionService().serve(page);
			}
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandInSystemConfirmation -> execute(HttpServletRequest)", e);
		}
		return message;
	}
}