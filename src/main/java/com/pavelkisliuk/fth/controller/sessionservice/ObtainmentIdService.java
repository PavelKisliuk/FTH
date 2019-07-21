package com.pavelkisliuk.fth.controller.sessionservice;

import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.IdByEmailSpecifier;

public class ObtainmentIdService implements FthService {
	@Override
	public String serve(FthData data) throws FthControllerException {
		FthAuthenticationData authenticationData = (FthAuthenticationData) data;
		try {
			return FthRepository.INSTANCE.query(
					new IdByEmailSpecifier(
							new FthString(authenticationData.getEmail()))).get(0).toString();
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in ObtainmentIdService -> serve(FthData).", e);
		}
	}
}