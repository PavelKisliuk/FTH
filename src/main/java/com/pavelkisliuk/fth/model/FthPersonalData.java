package com.pavelkisliuk.fth.model;

public class FthPersonalData implements FthData {
	private long clientId;
	private String firstName;
	private String lastName;
	private String photoPath;
	private boolean exerciseRequest;

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public boolean isExerciseRequest() {
		return exerciseRequest;
	}

	public void setExerciseRequest(boolean exerciseRequest) {
		this.exerciseRequest = exerciseRequest;
	}
}
