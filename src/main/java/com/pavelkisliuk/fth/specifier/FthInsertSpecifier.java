/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.repository.FthRepository;

import java.sql.PreparedStatement;

/**
 * FthInsertSpecifier extends FthSpecifier is interface for realization
 * insertion data to database by {@code FthRepository}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthRepository
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
	 * @param preparedStatement for pasting metadata into.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	void insert(PreparedStatement preparedStatement) throws FthRepositoryException;
}