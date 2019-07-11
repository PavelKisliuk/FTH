/*  By Pavel Kisliuk, 10.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.model;

/**
 * The class {@code FthRegistrationData} is class for storing data of user, for registration
 * in database.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthData
 * @see FthString
 * @since 12.0
 */
public class FthRegistrationData implements FthData {
	/**
	 * Name of user.
	 */
	private String name;
	/**
	 * Surname of user.
	 */
	private String surname;
	/**
	 * E-mail of user.
	 */
	private String email;
	/**
	 * Password of user
	 */
	private String password;
	/**
	 * Repeated password.
	 */
	private String confirmPassword;
	/**
	 * Key for e-mail confirmation of registration.
	 */
	private String key;

	/**
	 * The {@code name} getter.
	 * <p>
	 *
	 * @return {@code name}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * The {@code name} setter.
	 * <p>
	 *
	 * @param name is variable for setting.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The {@code surname} getter.
	 * <p>
	 *
	 * @return {@code surname}.
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * The {@code surname} setter.
	 * <p>
	 *
	 * @param surname is variable for setting.
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * The {@code email} getter.
	 * <p>
	 *
	 * @return {@code email}.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * The {@code email} setter.
	 * <p>
	 *
	 * @param email is variable for setting.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * The {@code password} getter.
	 * <p>
	 *
	 * @return {@code password}.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * The {@code password} setter.
	 * <p>
	 *
	 * @param password is variable for setting.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * The {@code confirmPassword} getter.
	 * <p>
	 *
	 * @return {@code confirmPassword}.
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * The {@code confirmPassword} setter.
	 * <p>
	 *
	 * @param confirmPassword is variable for setting.
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * The {@code key} getter.
	 * <p>
	 *
	 * @return {@code key}.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * The {@code key} setter.
	 * <p>
	 *
	 * @param key is variable for setting.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FthRegistrationData that = (FthRegistrationData) o;

		if (!name.equals(that.name)) return false;
		if (!surname.equals(that.surname)) return false;
		if (!email.equals(that.email)) return false;
		if (!password.equals(that.password)) return false;
		if (!confirmPassword.equals(that.confirmPassword)) return false;
		return key.equals(that.key);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + surname.hashCode();
		result = 31 * result + email.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + confirmPassword.hashCode();
		result = 31 * result + key.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "FthRegistrationData{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", confirmPassword='" + confirmPassword + '\'' +
				", key='" + key + '\'' +
				'}';
	}
}