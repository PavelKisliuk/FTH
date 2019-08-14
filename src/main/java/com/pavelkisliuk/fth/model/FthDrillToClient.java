package com.pavelkisliuk.fth.model;

public class FthDrillToClient implements FthData {
	private long drillId;
	private long drillBaseId;

	public long getDrillId() {
		return drillId;
	}

	public void setDrillId(long drillId) {
		this.drillId = drillId;
	}

	public long getDrillBaseId() {
		return drillBaseId;
	}

	public void setDrillBaseId(long drillBaseId) {
		this.drillBaseId = drillBaseId;
	}
}
