package com.pavelkisliuk.fth.controller.signinservice;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.validator.AuthenticationDataValidator;

import java.util.HashMap;
import java.util.Map;

public class FthTrainerSingInService implements FthService {
	private static final String KEY_MESSAGE = "message";
	private static final String KEY_REDIRECT = "redirect";

	private static final String REDIRECT_URL = "http://localhost:8080/FTH/jsp/TrainerPage.jsp";

	private boolean trainerExist;

	public String serve(FthData data) throws FthControllerException {
		Map<String, String> responseJson = new HashMap<>();
		FthAuthenticationData authenticationData = (FthAuthenticationData) data;
		AuthenticationDataValidator validator = new AuthenticationDataValidator();
		if (validator.isCorrect(authenticationData)) {
			responseJson.put(KEY_REDIRECT, REDIRECT_URL);
			trainerExist = true;
		} else {
			responseJson.put(KEY_MESSAGE, validator.toString());
		}
		return new Gson().toJson(responseJson);
	}

	public boolean isTrainerExist() {
		return trainerExist;
	}
}