package com.pavelkisliuk.fth.model;

public class FthClientPublicData implements FthData {
	private long clientId;
	private String unavailableTrainerGroup;
	private boolean exerciseRequest;
	private long expiredDay;
	private int restVisitation;
	private long trainerId;

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getUnavailableTrainerGroup() {
		return unavailableTrainerGroup;
	}

	public void setUnavailableTrainerGroup(String unavailableTrainerGroup) {
		this.unavailableTrainerGroup = unavailableTrainerGroup;
	}

	public boolean isExerciseRequest() {
		return exerciseRequest;
	}

	public void setExerciseRequest(boolean exerciseRequest) {
		this.exerciseRequest = exerciseRequest;
	}

	public long getExpiredDay() {
		return expiredDay;
	}

	public void setExpiredDay(long expiredDay) {
		this.expiredDay = expiredDay;
	}

	public int getRestVisitation() {
		return restVisitation;
	}

	public void setRestVisitation(int restVisitation) {
		this.restVisitation = restVisitation;
	}

	public long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(long trainerId) {
		this.trainerId = trainerId;
	}
}
