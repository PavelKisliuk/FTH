package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.controller.pageservice.TrainerPageService;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthLong;

import javax.servlet.http.HttpServletRequest;

public class CommandTrainerPage implements FthServletCommand {
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthLong trainerId = (FthLong) request.getSession().getAttribute(FthServletCommand.TRAINER_ID_ATTRIBUTE);
		String message = null;
		try {
			return new TrainerPageService().serve(trainerId);
		} catch (FthControllerException e) {
			throw new FthCommandException(
					"FthControllerException in CommandTrainerPage -> execute(HttpServletRequest)", e);
		}
	}
}