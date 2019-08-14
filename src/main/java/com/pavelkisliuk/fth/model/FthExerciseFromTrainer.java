package com.pavelkisliuk.fth.model;

public class FthExerciseFromTrainer implements FthData {
	private long drillId;
	private long muscleGroupId;
	private int setAmount;
	private int repeatAmount;
	private int restTime;
	private double weightTool;

	public long getDrillId() {
		return drillId;
	}

	public void setDrillId(long drillId) {
		this.drillId = drillId;
	}

	public long getMuscleGroupId() {
		return muscleGroupId;
	}

	public void setMuscleGroupId(long muscleGroupId) {
		this.muscleGroupId = muscleGroupId;
	}

	public int getSetAmount() {
		return setAmount;
	}

	public void setSetAmount(int setAmount) {
		this.setAmount = setAmount;
	}

	public int getRepeatAmount() {
		return repeatAmount;
	}

	public void setRepeatAmount(int repeatAmount) {
		this.repeatAmount = repeatAmount;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
	}

	public double getWeightTool() {
		return weightTool;
	}

	public void setWeightTool(double weightTool) {
		this.weightTool = weightTool;
	}
}
