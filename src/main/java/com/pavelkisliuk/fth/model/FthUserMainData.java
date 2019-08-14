package com.pavelkisliuk.fth.model;

import java.util.Objects;

public class FthUserMainData implements FthData {
	private Long userId;
	private String name;
	private String surname;
	private String photoPath;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

		FthUserMainData that = (FthUserMainData) o;

		if (!Objects.equals(userId, that.userId)) return false;
		if (!Objects.equals(name, that.name)) return false;
		if (!Objects.equals(surname, that.surname)) return false;
		return Objects.equals(photoPath, that.photoPath);
	}

	@Override
	public int hashCode() {
		int result = userId != null ? userId.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (surname != null ? surname.hashCode() : 0);
		result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "FthTrainerData{" +
				"trainerId=" + userId +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", photoPath='" + photoPath + '\'' +
				'}';
	}
}