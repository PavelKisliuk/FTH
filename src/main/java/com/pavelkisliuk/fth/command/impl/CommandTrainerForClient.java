package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.service.obtainment.TrainerForClientService;

import javax.servlet.http.HttpServletRequest;

public class CommandTrainerForClient implements FthServletCommand {
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong clientId = (FthLong) request.getSession().getAttribute(ID_ATTRIBUTE);
		TrainerForClientService trainerForClientService = new TrainerForClientService();
		try {
			return trainerForClientService.serve(clientId);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandTrainerForClient -> execute(HttpServletRequest)", e);
		}
	}
}
