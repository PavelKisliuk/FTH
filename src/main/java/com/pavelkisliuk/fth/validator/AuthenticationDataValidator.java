package com.pavelkisliuk.fth.validator;

import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthInt;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.AuthenticateSpecifier;

import java.util.ArrayList;

public class AuthenticationDataValidator implements FthValidator {
	private static final String EMAIL_EMPTY = "Field \"email\" empty.\n";
	private static final String PASSWORD_EMPTY = "Field \"password\" empty.\n";

	private static final String ICORRECT = "Incorrect e-mail or password.";
	/**
	 * {@code ArrayList} for messages about incorrect data.
	 */
	private ArrayList<String> messageGroup;

	@Override
	public boolean isCorrect(FthData data) throws FthControllerException {
		messageGroup = new ArrayList<>();
		FthAuthenticationData authenticationData = (FthAuthenticationData) data;
		return isDataNotEmpty(authenticationData) && isExist(authenticationData);
	}

	private boolean isDataNotEmpty(FthAuthenticationData authenticationData) {
		boolean flag = true;
		if (authenticationData.getEmail() == null || authenticationData.getEmail().isBlank()) {
			flag = false;
			messageGroup.add(EMAIL_EMPTY);
		}
		if (authenticationData.getPassword() == null || authenticationData.getPassword().isBlank()) {
			flag = false;
			messageGroup.add(PASSWORD_EMPTY);
		}
		return flag;
	}

	private boolean isExist(FthAuthenticationData authenticationData) throws FthControllerException {
		boolean flag = true;
		try {
			FthInt fthInt = (FthInt) FthRepository.INSTANCE.query(new AuthenticateSpecifier(authenticationData)).get(0);
			if(fthInt.get() != 1) {
				messageGroup.add(ICORRECT);
				flag = false;
			}
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in AuthenticationDataValidator -> isExist(FthAuthenticationData).", e);
		}
		return flag;
	}

	@Override
	public String toString() {
		return messageGroup.toString().replace("[", "- ").
				replace(']', ' ').
				replace(',', '-').
				strip();
	}
}