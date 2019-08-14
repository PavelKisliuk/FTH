package com.pavelkisliuk.fth.model;

public class FthSetGroup implements FthData {
	private int setNumber;
	private int necessaryReps;
	private int selfConsistent;
	private int helpConsistent;
	private double weightTool;
	private int restTime;
	private long exerciseId;
	private long drillId;

	public int getSetNumber() {
		return setNumber;
	}

	public void setSetNumber(int setNumber) {
		this.setNumber = setNumber;
	}

	public int getNecessaryReps() {
		return necessaryReps;
	}

	public void setNecessaryReps(int necessaryReps) {
		this.necessaryReps = necessaryReps;
	}

	public int getSelfConsistent() {
		return selfConsistent;
	}

	public void setSelfConsistent(int selfConsistent) {
		this.selfConsistent = selfConsistent;
	}

	public int getHelpConsistent() {
		return helpConsistent;
	}

	public void setHelpConsistent(int helpConsistent) {
		this.helpConsistent = helpConsistent;
	}

	public double getWeightTool() {
		return weightTool;
	}

	public void setWeightTool(double weightTool) {
		this.weightTool = weightTool;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
	}

	public long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(long exerciseId) {
		this.exerciseId = exerciseId;
	}

	public long getDrillId() {
		return drillId;
	}

	public void setDrillId(long drillId) {
		this.drillId = drillId;
	}
}