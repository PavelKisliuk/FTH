package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.model.FthClientPublicData;

import javax.servlet.http.HttpServletRequest;

class CreatorClientPublicDataSeasonPass implements FthDataByRequestFactory {
	private static final String CLIENT_ID = "clientId";
	private static final String AMOUNT = "amount";
	private static final String EXPIRED_TIME = "expiredDate";

	@Override
	public FthClientPublicData create(HttpServletRequest request) throws FthCommandException {
		FthClientPublicData clientPublicData = new FthClientPublicData();
		clientPublicData.setClientId(Long.parseLong(request.getParameter(CLIENT_ID)));
		clientPublicData.setRestVisitation(Integer.parseInt(request.getParameter(AMOUNT)));
		clientPublicData.setExpiredDay(Long.parseLong(request.getParameter(EXPIRED_TIME)));
		return clientPublicData;
	}
}