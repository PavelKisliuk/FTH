package com.pavelkisliuk.fth.controller;

import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthData;

public interface FthService {
	String serve(FthData data) throws FthControllerException;
}
