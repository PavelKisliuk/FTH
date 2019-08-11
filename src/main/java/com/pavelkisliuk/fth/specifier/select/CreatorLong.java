/*  By Pavel Kisliuk, 20.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
class CreatorLong implements FthDataByResultSetFactory {

	/**
	 * Return first element in {@code ResultSet}, where long expected.
	 * <p>
	 *
	 * @param resultSet of data for creation {@code FthData} instance.
	 * @return first element as {@code FthLong}.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public FthLong create(ResultSet resultSet) throws FthRepositoryException {
		try {
			return new FthLong(resultSet.getLong(FIRST_ELEMENT));
		} catch (SQLException e) {
			throw new FthRepositoryException("SQLException in CreatorLong -> create(ResultSet).", e);
		}
	}
}
