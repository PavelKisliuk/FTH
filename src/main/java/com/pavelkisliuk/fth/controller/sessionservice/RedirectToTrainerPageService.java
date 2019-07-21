package com.pavelkisliuk.fth.controller.sessionservice;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthData;

import java.util.HashMap;
import java.util.Map;

public class RedirectToTrainerPageService implements FthService {
	@Override
	public String serve(FthData data) throws FthControllerException {
		Map<String, String> responseJson = new HashMap<>();
		responseJson.put("redirect", "http://localhost:8080/FTH/jsp/TrainerPage.jsp");
		return new Gson().toJson(responseJson);
	}
}
