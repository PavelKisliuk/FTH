package com.pavelkisliuk.fth.model;

public class FthDrillBase implements FthData {
	private long drillBaseId;
	private long muscleGroupId;
	private String drillName;

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

	public String getDrillName() {
		return drillName;
	}

	public void setDrillName(String drillName) {
		this.drillName = drillName;
	}
}