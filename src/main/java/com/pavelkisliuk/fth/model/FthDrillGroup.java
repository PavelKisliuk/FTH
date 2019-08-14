package com.pavelkisliuk.fth.model;

public class FthDrillGroup implements FthData {
	private int drillNumber;
	private long exerciseId;
	private long drillBaseId;
	private long muscleGroupId;

	public int getDrillNumber() {
		return drillNumber;
	}

	public void setDrillNumber(int drillNumber) {
		this.drillNumber = drillNumber;
	}

	public long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(long exerciseId) {
		this.exerciseId = exerciseId;
	}

	public long getDrillBaseId() {
		return drillBaseId;
	}

	public void setDrillBaseId(long drillBaseId) {
		this.drillBaseId = drillBaseId;
	}

	public long getMuscleGroupId() {
		return muscleGroupId;
	}

	public void setMuscleGroupId(long muscleGroupId) {
		this.muscleGroupId = muscleGroupId;
	}
}