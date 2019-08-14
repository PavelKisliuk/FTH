/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.repository;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FthSpecialTransactionOperator is interface for special transaction situation's.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthRepository
 * @see FthData
 * @since 12.0
 */
interface FthSpecialTransactionOperator<T extends FthData> {
	/**
	 * @param data       for database insertion.
	 * @param connection is connection to database.
	 * @throws FthRepositoryException if {@code Exception} occurred.
	 */
	void insert(T data, Connection connection) throws FthRepositoryException;

	/**
	 * Retrieve last generated id.
	 * <p>
	 *
	 * @param statement is statement used for insertion.
	 * @return last generated id.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	default long getLastGeneratedKey(PreparedStatement statement) throws FthRepositoryException {
		try {
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			} else {
				throw new FthRepositoryException(
						"Can't retrieve id of created table" +
								"in FthRepository -> getLastGeneratedKey(PreparedStatement).");
			}
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in FthRepository -> getLastGeneratedKey(PreparedStatement).", e);
		}
	}
}