/*  By Pavel Kisliuk, 11.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryFactoryException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code FthStringCreator} class is commandfactory for creation {@code FthString}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthDataByResultSetFactory
 * @see com.pavelkisliuk.fth.model.FthData
 * @see com.pavelkisliuk.fth.model.FthString
 * @since 12.0
 */
class FthStringCreator implements FthDataByResultSetFactory {
	/**
	 * Return instance of {@code FthString}.
	 * <p>
	 *
	 * @param resultSet of data for creation {@code FthData} instance.
	 * @return instance of {@code FthString}.
	 * @throws FthRepositoryFactoryException if {@code SQLException} occurred.
	 */
	@Override
	public FthData create(ResultSet resultSet) throws FthRepositoryFactoryException {
		try {
			return new FthString(resultSet.getString(1));
		} catch (SQLException e) {
			throw new FthRepositoryFactoryException("SQL exception in FthStringCreator -> create().", e);
		}
	}
}