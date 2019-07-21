package com.pavelkisliuk.fth.controller.pageservice;

import com.google.gson.Gson;
import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.servlet.PageType;

import java.util.HashMap;
import java.util.Map;

public class PageRedirectionService implements FthService {
	@Override
	public String serve(FthData data) throws FthControllerException {
		Map<String, String> responseJson = new HashMap<>();
		FthString fthString = (FthString) data;
		try {
			responseJson.put("page", PageType.valueOf(fthString.get()).get());
		} catch (IllegalArgumentException | NullPointerException e) {

		}
		return new Gson().toJson(responseJson);
	}
}