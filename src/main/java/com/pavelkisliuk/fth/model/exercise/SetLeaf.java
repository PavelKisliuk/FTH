package com.pavelkisliuk.fth.model.exercise;

import java.sql.Date;

public class SetLeaf implements ExerciseComponent {

	private int number;
	private int necessaryReps;
	private int selfConsistent;
	private int helpConsistent;
	private double weightTool;
	private int restTime;

	class Exercise {
		private int number;
		private String name;
		private String muscleGroup;
	}

	private Exercise exercise;

	class WorkOut {
		private int number;
		private Date exerciseDate;
	}

	private WorkOut workOut;
}
