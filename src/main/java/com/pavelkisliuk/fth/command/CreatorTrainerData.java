package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthTrainerData;

import javax.servlet.http.HttpServletRequest;

public class CreatorTrainerData implements FthDataByRequestFactory {
	@Override
	public FthTrainerData create(HttpServletRequest request) {
		FthTrainerData trainerData = new FthTrainerData();
		return null;
	}
}