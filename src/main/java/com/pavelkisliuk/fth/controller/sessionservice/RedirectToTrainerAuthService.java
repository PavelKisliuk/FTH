package com.pavelkisliuk.fth.controller.sessionservice;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthData;

import java.util.HashMap;
import java.util.Map;

public class RedirectToTrainerAuthService implements FthService {
	@Override
	public String serve(FthData data) throws FthControllerException {
		Map<String, String> responseJson = new HashMap<>();
		responseJson.put("errorRedirect", "http://localhost:8080/FTH/jsp/TrainerAuth.jsp");
		return new Gson().toJson(responseJson);
	}
}
