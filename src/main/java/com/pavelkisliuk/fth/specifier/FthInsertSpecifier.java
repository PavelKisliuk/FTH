/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier;

import com.pavelkisliuk.fth.exception.FthSpecifierException;

import java.sql.PreparedStatement;

/**
 * FthInsertSpecifier extends FthSpecifier is interface for realization
 * insertion data to database by {@code FthRepositorySingleton}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see com.pavelkisliuk.fth.repository.FthRepositorySingleton
 * @see FthSpecifier
 * @see FthSelectSpecifier
 * @see FthDeleteSpecifier
 * @since 12.0
 */
public interface FthInsertSpecifier extends FthSpecifier {
	/**
	 * Paste metadata in {@code PreparedStatement} to insert this data to database.
	 * <p>
	 *
	 * @param statement for pasting metadata into.
	 * @throws FthSpecifierException if {@code SQLException} occurred.
	 */
	void insert(PreparedStatement statement) throws FthSpecifierException;
}