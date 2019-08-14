package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.model.FthUserMainData;

import javax.servlet.http.HttpServletRequest;

class CreatorTrainerData implements FthDataByRequestFactory {
	@Override
	public FthUserMainData create(HttpServletRequest request) {
		FthUserMainData trainerData = new FthUserMainData();
		return null;
	}
}