/*  By Pavel Kisliuk, 12.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.model.FthData;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code FthDataByRequestFactory} is interface for creation {@code FthData} instances
 * from data from {@code HttpServletRequest} for future reference.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see com.pavelkisliuk.fth.model.FthData
 * @since 12.0
 */
interface FthDataByRequestFactory {
	/**
	 * Return instance of {@code FthData}.
	 * <p>
	 *
	 * @param request of data for creation {@code FthData} instance.
	 * @return instance of {@code FthData}.
	 */
	FthData create(HttpServletRequest request) throws FthCommandException;
}