package com.pavelkisliuk.fth.model;

public class FthBoolean implements FthData {
	boolean bool;

	public FthBoolean(boolean bool) {
		this.bool = bool;
	}

	public boolean get() {
		return bool;
	}

	public void set(boolean bool) {
		this.bool = bool;
	}
}
