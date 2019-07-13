/*  By Pavel Kisliuk, 13.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.servlet;

import com.pavelkisliuk.fth.model.FthRegistrationData;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code FthRegistrationDataCreator} class is realization of {@code FthDataByRequestFactory} for
 * creation {@code FthRegistrationData} instance.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthDataByRequestFactory
 * @see com.pavelkisliuk.fth.model.FthRegistrationData
 * @since 12.0
 */
class FthRegistrationDataCreator implements FthDataByRequestFactory {
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
		registrationData.setName(request.getParameter("nameSingUp"));
		registrationData.setSurname(request.getParameter("surnameSingUp"));
		registrationData.setEmail(request.getParameter("emailSingUp").toLowerCase().trim());
		registrationData.setPassword(request.getParameter("passwordSingUp"));
		registrationData.setPassword(request.getParameter("confirmSingUp"));
		return registrationData;
	}
}