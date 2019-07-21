package com.pavelkisliuk.fth.command;

public enum CommandType {
	SING_UP(new CommandSingUp()),
	TRAINER_SING_IN(new CommandTrainerSingIn()),
	TRAINER_PAGE(new CommandTrainerPage()),
	IN_SYSTEM(new CommandInSystemConfirmation());

	private FthServletCommand command;

	CommandType(FthServletCommand command) {
		this.command = command;
	}

	public FthServletCommand create() {
		return command;
	}
}
