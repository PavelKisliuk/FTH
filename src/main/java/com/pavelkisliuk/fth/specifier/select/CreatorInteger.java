/*  By Pavel Kisliuk, 20.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthInt;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
class CreatorInteger implements FthDataByResultSetFactory {
	/**
	 * Define {@code ResultSet} {@code columnLabel} first element.
	 */
	private static final int FIRST_ELEMENT = 1;

	/**
	 * Return first element in {@code ResultSet}, where int expected.
	 * <p>
	 *
	 * @param resultSet of data for creation {@code FthData} instance.
	 * @return first element as {@code FthInt}.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public FthInt create(ResultSet resultSet) throws FthRepositoryException {
		try {
			return new FthInt(resultSet.getInt(FIRST_ELEMENT));
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in CreatorInteger -> create(ResultSet).", e);
		}
	}
}