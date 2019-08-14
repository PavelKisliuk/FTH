package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;

public enum CommandGetType {
	TRAINER_PAGE(new CommandTrainerPage()),
	CLIENT_PAGE(new CommandClientPage()),
	IN_SYSTEM(new CommandInSystemConfirmation()),
	REQUEST_AND_EXPIRED(new CommandClientRequestAndExpired()),
	DRILL_BASE(new CommandDrillBase()),
	CONDITION_REFRESH(new CommandRefreshClientGroup()),
	TO_CLIENT_EXERCISE(new CommandExerciseToClient()),
	OBTAIN_TRAINER(new CommandTrainerForClient()),
	CONFIRM(new CommandConfirmRegistration()),
	OUT(new CommandOut());

	private FthServletCommand command;

	CommandGetType(FthServletCommand command) {
		this.command = command;
	}

	public FthServletCommand create() {
		return command;
	}
}
