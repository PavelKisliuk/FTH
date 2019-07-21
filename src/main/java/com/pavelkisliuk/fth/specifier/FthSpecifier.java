/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier;

import com.pavelkisliuk.fth.repository.FthRepository;

/**
 * FthSpecifier is interface for realization behaviour to acquire data from database by {@code FthRepository}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthRepository
 * @see FthSelectSpecifier
 * @see FthInsertSpecifier
 * @see FthDeleteSpecifier
 * @since 12.0
 */
public interface FthSpecifier {
	/**
	 * Return SQL request as String.
	 * <p>
	 *
	 * @return SQL request for {@code PreparedStatement} as String.
	 */
	String deriveSequelRequest();
}