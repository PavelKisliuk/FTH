package com.pavelkisliuk.fth.model;

public class FthSetFromClient implements FthData {
	private long id;
	private int selfConsistent;
	private int helpConsistent;
	private double weightTool;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSelfConsistent() {
		return selfConsistent;
	}

	public void setSelfConsistent(int selfConsistent) {
		this.selfConsistent = selfConsistent;
	}

	public int getHelpConsistent() {
		return helpConsistent;
	}

	public void setHelpConsistent(int helpConsistent) {
		this.helpConsistent = helpConsistent;
	}

	public double getWeightTool() {
		return weightTool;
	}

	public void setWeightTool(double weightTool) {
		this.weightTool = weightTool;
	}
}
