package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.command.FthServletCommand;

public enum CommandPostType {
	SING_UP(new CommandClientSingUp()),
	TRAINER_SING_IN(new CommandTrainerSingIn()),
	CLIENT_SIGN_IN(new CommandClientSignIn()),
	TRAINER_EXERCISE(new CommandExerciseFromTrainer()),
	DISCARD_EXERCISE(new CommandDiscardExercise()),
	DENY_CLIENT(new CommandDenyClientToExercise()),
	REQUEST_EXERCISE(new CommandClientExerciseRequest()),
	FINISH_EXERCISE(new CommandFinishExercise()),
	SPREAD_SEASON(new CommandSpreadSeason()),
	FIRE_SEASON(new CommandFireSeasonPass()),
	CREATE_DRILL(new CommandCreateDrillBase()),
	CHANGE_TRAINER(new CommandChangeTrainer()),
	REMOVE_CLIENT(new CommandTrainerDeleteClient());

	private FthServletCommand command;

	CommandPostType(FthServletCommand command) {
		this.command = command;
	}

	public FthServletCommand create() {
		return command;
	}
}
