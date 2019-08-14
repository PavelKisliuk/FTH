package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthUserMainData;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatorPureUserData implements FthDataByResultSetFactory {
	private static final String NAME = "firstName";
	private static final String SURNAME = "lastName";

	@Override
	public FthUserMainData create(ResultSet resultSet) throws FthRepositoryException {
		FthUserMainData trainerData = new FthUserMainData();
		try {
			trainerData.setName(resultSet.getString(NAME));
			trainerData.setSurname(resultSet.getString(SURNAME));
		} catch (SQLException e) {
			throw new FthRepositoryException("SQL exception in CreatorString -> create(ResultSet).", e);
		}
		return trainerData;
	}
}
