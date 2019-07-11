/*  By Pavel Kisliuk, 10.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.model;

/**
 * The class {@code FthLong} is class-wrapper for {@code Long}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthData
 * @see FthString
 * @since 12.0
 */
public class FthLong implements FthData {
	/**
	 * Wrapped variable.
	 */
	private long bigInt;

	/**
	 * Constructor for initialization.
	 * <p>
	 *
	 * @param bigInt is variable for initialization {@code FthLong}.
	 */
	public FthLong(long bigInt) {
		this.bigInt = bigInt;
	}

	/**
	 * Getter for {@code FthLong}.
	 * <p>
	 *
	 * @return {@code bigInt}.
	 */
	public long get() {
		return bigInt;
	}

	/**
	 * Setter for {@code FthLong}.
	 * <p>
	 *
	 * @param bigInt is variable for setting.
	 */
	public void set(long bigInt) {
		this.bigInt = bigInt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FthLong fthLong = (FthLong) o;

		return bigInt == fthLong.bigInt;
	}

	@Override
	public int hashCode() {
		return (int) (bigInt ^ (bigInt >>> 32));
	}

	@Override
	public String toString() {
		return "FthLong{" +
				"bigInt=" + bigInt +
				'}';
	}
}