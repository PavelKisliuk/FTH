package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.service.signin.ConfirmRegistrationService;

import javax.servlet.http.HttpServletRequest;

public class CommandConfirmRegistration implements FthServletCommand {
	@Override
	public String execute(HttpServletRequest request) throws FthCommandException {
		FthString registrationKey = new CreatorString().create(request);
		FthBoolean isTrainer = new CreatorBoolean().create(request);
		ConfirmRegistrationService confirmRegistrationService = new ConfirmRegistrationService(isTrainer);
		try {
			return confirmRegistrationService.serve(registrationKey);
		} catch (FthServiceException e) {
			throw new FthCommandException(
					"FthServiceException in CommandConfirmRegistration -> execute(HttpServletRequest)", e);
		}
	}
}
