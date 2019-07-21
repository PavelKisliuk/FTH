package com.pavelkisliuk.fth.model;

import java.util.Objects;

public class FthAuthenticationData implements FthData{
	/**
	 * E-mail of user.
	 */
	private String email;
	/**
	 * Password of user
	 */
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FthAuthenticationData that = (FthAuthenticationData) o;

		if (!Objects.equals(email, that.email)) return false;
		return Objects.equals(password, that.password);
	}

	@Override
	public int hashCode() {
		int result = email != null ? email.hashCode() : 0;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "FthAuthenticationData{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}