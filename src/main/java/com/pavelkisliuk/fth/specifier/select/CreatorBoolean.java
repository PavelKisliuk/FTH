package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatorBoolean implements FthDataByResultSetFactory {
	@Override
	public FthBoolean create(ResultSet resultSet) throws FthRepositoryException {
		try {
			return new FthBoolean(resultSet.getBoolean(FIRST_ELEMENT));
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in CreatorBoolean -> create(ResultSet).", e);
		}
	}
}
