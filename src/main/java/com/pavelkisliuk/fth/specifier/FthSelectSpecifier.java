/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;
import com.pavelkisliuk.fth.repository.FthRepository;

import java.sql.PreparedStatement;

/**
 * FthSelectSpecifier extends FthSpecifier is interface for realization
 * selection data from database by {@code FthRepository}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthRepository
 * @see FthDataByResultSetFactory
 * @see FthSpecifier
 * @see FthInsertSpecifier
 * @see FthDeleteSpecifier
 * @since 12.0
 */
public interface FthSelectSpecifier extends FthSpecifier {
	/**
	 * Return commandfactory for creation data from database.
	 * <p>
	 *
	 * @return commandfactory for creation data from database by {@code FthRepository}.
	 */
	FthDataByResultSetFactory createFactory();

	/**
	 * Paste metadata in {@code PreparedStatement} and return this {@code PreparedStatement}
	 * with pasted metadata to select data from database by {@code FthRepository}.
	 * <p>
	 *
	 * @param statement for pasting metadata into.
	 * @return {@param statement} with pasted metadata.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	PreparedStatement pasteMeta(PreparedStatement statement) throws FthRepositoryException;
}