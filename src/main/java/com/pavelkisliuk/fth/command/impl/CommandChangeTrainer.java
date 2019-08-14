package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.service.updating.ChangeTrainerService;

import javax.servlet.http.HttpServletRequest;

public class CommandChangeTrainer implements FthServletCommand {
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong clientId = (FthLong) request.getSession().getAttribute(ID_ATTRIBUTE);
		FthLong trainerId = new CreatorLong().create(request);
		ChangeTrainerService changeTrainerService = new ChangeTrainerService(clientId);
		try {
			String answer = changeTrainerService.serve(trainerId);
			request.getSession().setAttribute(TRAINER_ID, trainerId);
			return answer;
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in ChangeTrainerService -> execute(HttpServletRequest)", e);
		}
	}
}