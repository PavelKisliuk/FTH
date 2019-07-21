package com.pavelkisliuk.fth.model;

public class FthInt implements FthData {
	/**
	 * Wrapped int variable.
	 */
	private int integer;

	/**
	 * Constructor for initialization.
	 * <p>
	 *
	 * @param integer is variable for initialization {@code FthInt}.
	 */
	public FthInt(int integer) {
		this.integer = integer;
	}

	/**
	 * Getter for {@code FthInt}.
	 * <p>
	 *
	 * @return {@code integer}.
	 */
	public int get() {
		return integer;
	}

	/**
	 * Setter for {@code FthInt}.
	 * <p>
	 *
	 * @param integer is variable for setting.
	 */
	public void set(int integer) {
		this.integer = integer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FthInt fthInt = (FthInt) o;

		return integer == fthInt.integer;
	}

	@Override
	public int hashCode() {
		return integer;
	}

	@Override
	public String toString() {
		return "FthInt{" +
				"integer=" + integer +
				'}';
	}
}