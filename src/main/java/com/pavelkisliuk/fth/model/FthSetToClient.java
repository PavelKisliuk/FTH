package com.pavelkisliuk.fth.model;

public class FthSetToClient implements FthData {
	private long setId;
	private int necessaryReps;
	private double weightTool;
	private int restTime;

	public long getSetId() {
		return setId;
	}

	public void setSetId(long setId) {
		this.setId = setId;
	}

	public int getNecessaryReps() {
		return necessaryReps;
	}

	public void setNecessaryReps(int necessaryReps) {
		this.necessaryReps = necessaryReps;
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
}
