package com.pavelkisliuk.fth.model;

public class FthRefreshCondition implements FthData {
	private long trainerId;
	private String conditionName;
	private String conditionQuality;

	public long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(long trainerId) {
		this.trainerId = trainerId;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getConditionQuality() {
		return conditionQuality;
	}

	public void setConditionQuality(String conditionQuality) {
		this.conditionQuality = conditionQuality;
	}
}