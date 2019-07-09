/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.repository;

import com.pavelkisliuk.fth.exception.FthRepositoryFactoryException;
import com.pavelkisliuk.fth.model.FthData;

import java.sql.ResultSet;

/**
 * FthRepositoryFactory is interface for creation {@code FthData} instances in {@code FthRepositorySingleton}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see com.pavelkisliuk.fth.repository.FthRepositorySingleton
 * @see FthData
 * @since 12.0
 */
public interface FthRepositoryFactory {
	/**
	 * Return instance of {@code FthData}.
	 *
	 * @param resultSet of data for creation {@code FthData} instance.
	 * @return instance of {@code FthData}.
	 * @throws FthRepositoryFactoryException if {@code SQLException} occurred.
	 */
	FthData create(ResultSet resultSet) throws FthRepositoryFactoryException;
}