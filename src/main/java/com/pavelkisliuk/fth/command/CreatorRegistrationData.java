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
	private static final String NAME = "name";

	/**
	 * Surname parameter in request.
	 */
	private static final String SURNAME = "surname";

	/**
	 * E-mail parameter in request.
	 */
	private static final String EMAIL = "email";

	/**
	 * Password parameter in request.
	 */
	private static final String PASSWORD = "password";

	/**
	 * Confirm password parameter in request.
	 */
	private static final String CONFIRM_PASSWORD = "confirmPassword";

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
		registrationData.setName(request.getParameter(NAME).strip());
		registrationData.setSurname(request.getParameter(SURNAME).strip());
		registrationData.setEmail(request.getParameter(EMAIL).toLowerCase().strip());
		registrationData.setPassword(request.getParameter(PASSWORD));
		registrationData.setConfirmPassword(request.getParameter(CONFIRM_PASSWORD));
		return registrationData;
	}
}