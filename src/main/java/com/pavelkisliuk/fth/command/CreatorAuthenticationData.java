/*  By Pavel Kisliuk, 16.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.model.FthAuthenticationData;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code CreatorAuthenticationData} class is realization of {@code FthDataByRequestFactory} for
 * creation {@code FthAuthenticationData} instance.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthDataByRequestFactory
 * @see com.pavelkisliuk.fth.model.FthRegistrationData
 * @since 12.0
 */
public class CreatorAuthenticationData implements FthDataByRequestFactory {
	/**
	 * E-mail as login parameter in request.
	 */
	private static final String EMAIL = "emailSingIn";

	/**
	 * Password parameter in request.
	 */
	private static final String PASSWORD = "passwordSingIn";

	/**
	 * Return instance of {@code FthAuthenticationData} created by {@code HttpServletRequest}.
	 * <p>
	 *
	 * @param request of data for creation {@code FthData} instance.
	 * @return instance of {@code FthAuthenticationData}.
	 */
	@Override
	public FthAuthenticationData create(HttpServletRequest request) {
		FthAuthenticationData authenticationData = new FthAuthenticationData();
		authenticationData.setEmail(request.getParameter(EMAIL).toLowerCase().strip());
		authenticationData.setPassword(request.getParameter(PASSWORD));
		return authenticationData;
	}
}