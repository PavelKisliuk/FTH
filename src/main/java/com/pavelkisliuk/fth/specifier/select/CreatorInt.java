package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthInt;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

class CreatorInt implements FthDataByResultSetFactory {
	@Override
	public FthInt create(ResultSet resultSet) throws FthRepositoryException {
		try {
			return new FthInt(resultSet.getInt(FIRST_ELEMENT));
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in CreatorInt -> create(ResultSet).", e);
		}
	}
}
