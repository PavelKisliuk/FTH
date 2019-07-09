/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier;

import com.pavelkisliuk.fth.exception.FthSpecifierException;
import com.pavelkisliuk.fth.repository.FthRepositoryFactory;

import java.sql.PreparedStatement;

/**
 * FthSelectSpecifier extends FthSpecifier is interface for realization
 * selection data from database by {@code FthRepositorySingleton}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see com.pavelkisliuk.fth.repository.FthRepositorySingleton
 * @see FthRepositoryFactory
 * @see FthSpecifier
 * @see FthInsertSpecifier
 * @see FthDeleteSpecifier
 * @since 12.0
 */
public interface FthSelectSpecifier extends FthSpecifier {
	/**
	 * Return factory for creation data from database.
	 * <p>
	 *
	 * @return factory for creation data from database by {@code FthRepositorySingleton}.
	 */
	FthRepositoryFactory createFactory();

	/**
	 * Paste metadata in {@code PreparedStatement} and return this {@code PreparedStatement}
	 * with pasted metadata to select data from database by {@code FthRepositorySingleton}.
	 * <p>
	 *
	 * @param statement for pasting metadata into.
	 * @return {@param statement} with pasted metadata.
	 * @throws FthSpecifierException if {@code SQLException} occurred.
	 */
	PreparedStatement pasteMeta(PreparedStatement statement) throws FthSpecifierException;
}