/*  By Pavel Kisliuk, 11.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.validator;

import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthData;

/**
 * The interface {@code FthValidator} is interface for {@code FthData} validation.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see com.pavelkisliuk.fth.model.FthData
 * @see RegistrationDataValidator
 * @since 12.0
 */
public interface FthValidator<T extends FthData> {
	/**
	 * Inspect {@code FthData} instance for correct data.
	 * <p>
	 *
	 * @param data is data for validation.
	 * @return {@code true} if {@param data} valid, else return {@code false}.
	 */
	boolean isCorrect(T data) throws FthControllerException;
}