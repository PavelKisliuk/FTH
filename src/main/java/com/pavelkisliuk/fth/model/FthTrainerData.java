package com.pavelkisliuk.fth.model;

import java.util.Objects;

public class FthTrainerData implements FthData {
	private Long trainerId;
	private String name;
	private String surname;
	private String photoPath;

	public Long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FthTrainerData that = (FthTrainerData) o;

		if (!Objects.equals(trainerId, that.trainerId)) return false;
		if (!Objects.equals(name, that.name)) return false;
		if (!Objects.equals(surname, that.surname)) return false;
		return Objects.equals(photoPath, that.photoPath);
	}

	@Override
	public int hashCode() {
		int result = trainerId != null ? trainerId.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (surname != null ? surname.hashCode() : 0);
		result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "FthTrainerData{" +
				"trainerId=" + trainerId +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", photoPath='" + photoPath + '\'' +
				'}';
	}
}