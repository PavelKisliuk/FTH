/*  By Pavel Kisliuk, 10.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.model;

/**
 * The class {@code FthString} is class-wrapper for {@code String}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthData
 * @see FthLong
 * @since 12.0
 */
public class FthString implements FthData {
	/**
	 * Wrapped variable.
	 */
	private String string;

	/**
	 * Constructor for initialization.
	 *
	 * @param string is variable for initialization {@code FthString}.
	 */
	public FthString(String string) {
		this.string = string;
	}

	/**
	 * Getter for {@code FthString}.
	 *
	 * @return {@code string}.
	 */
	public String get() {
		return string;
	}

	/**
	 * Setter for {@code FthString}.
	 *
	 * @param string is variable for setting.
	 */
	public void set(String string) {
		this.string = string;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FthString fthString = (FthString) o;

		return string.equals(fthString.string);
	}

	@Override
	public int hashCode() {
		return string.hashCode();
	}

	@Override
	public String toString() {
		return "FthString{" +
				"string='" + string + '\'' +
				'}';
	}
}