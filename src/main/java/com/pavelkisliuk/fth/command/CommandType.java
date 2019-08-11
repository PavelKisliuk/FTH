package com.pavelkisliuk.fth.command;

public enum CommandType {
	SING_UP(new CommandSingUp()),
	TRAINER_SING_IN(new CommandTrainerSingIn()),
	TRAINER_PAGE(new CommandTrainerPage()),
	IN_SYSTEM(new CommandInSystemConfirmation()),
	CHANGE_PERSONAL_DATA(new CommandChangePersonalData()),
	TRAINER_DELETE_CLIENT(new CommandTrainerDeleteClient()),
	REQUEST_AND_EXPIRED(new CommandClientRequestAndExpired()),
	DRILL_BASE(new CommandDrillBase()),
	CONDITION_REFRESH(new CommandRefreshClientGroup()),
	TRAINER_EXERCISE(new CommandExerciseFromTrainer()),
	TO_CLIENT_EXERCISE(new CommandExerciseToClient());

	private FthServletCommand command;

	CommandType(FthServletCommand command) {
		this.command = command;
	}

	public FthServletCommand create() {
		return command;
	}
}
