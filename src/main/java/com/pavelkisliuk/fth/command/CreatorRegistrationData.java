/*  By Pavel Kisliuk, 13.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.model.FthRegistrationData;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CreatorRegistrationData} class is realization of {@code FthDataByRequestFactory} for
 * creation {@code FthRegistrationData} instance.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthDataByRequestFactory
 * @see com.pavelkisliuk.fth.model.FthRegistrationData
 * @since 12.0
 */
class CreatorRegistrationData implements FthDataByRequestFactory {
	/**
	 * Name parameter in request.
	 */
	private static final String NAME = "nameSingUp";

	/**
	 * Surname parameter in request.
	 */
	private static final String SURNAME = "surnameSingUp";

	/**
	 * E-mail parameter in request.
	 */
	private static final String EMAIL = "emailSingUp";

	/**
	 * Password parameter in request.
	 */
	private static final String PASSWORD = "passwordSingUp";

	/**
	 * Confirm password parameter in request.
	 */
	private static final String CONFIRM_PASSWORD = "confirmPasswordSingUp";

	/**
	 * Return instance of {@code FthRegistrationData} created by {@code HttpServletRequest}.
	 * <p>
	 *
	 * @param request of data for creation {@code FthRegistrationData} instance.
	 * @return instance of {@code FthRegistrationData}.
	 */
	@Override
	public FthRegistrationData create(HttpServletRequest request) {
		FthRegistrationData registrationData = new FthRegistrationData();
		registrationData.setName(request.getParameter(NAME));
		registrationData.setSurname(request.getParameter(SURNAME));
		registrationData.setEmail(request.getParameter(EMAIL).toLowerCase().trim());
		registrationData.setPassword(request.getParameter(PASSWORD));
		registrationData.setConfirmPassword(request.getParameter(CONFIRM_PASSWORD));
		return registrationData;
	}
}