package com.pavelkisliuk.fth.model;

public class FthPureDrillBase implements FthData {
	private long muscleGroupId;
	private String drillName;

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
