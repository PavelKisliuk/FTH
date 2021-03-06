/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.repository;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;

import java.sql.ResultSet;

/**
 * FthDataByResultSetFactory is interface for creation {@code FthData} instances in {@code FthRepository}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthRepository
 * @see FthData
 * @since 12.0
 */
public interface FthDataByResultSetFactory {
	/**
	 * Define {@code ResultSet} {@code columnLabel} first element.
	 */
	int FIRST_ELEMENT = 1;

	/**
	 * Return instance of {@code FthData}.
	 * <p>
	 *
	 * @param resultSet of data for creation {@code FthData} instance.
	 * @return instance of {@code FthData}.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	FthData create(ResultSet resultSet) throws FthRepositoryException;
}