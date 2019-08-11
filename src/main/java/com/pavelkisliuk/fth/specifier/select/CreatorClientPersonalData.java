package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthPersonalData;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatorClientPersonalData implements FthDataByResultSetFactory {
	private static final String CLIENT_ID = "clientId";
	private static final String NAME = "firstName";
	private static final String SURNAME = "lastName";
	private static final String PHOTO_PATH = "photoPath";
	private static final String EXERCISE_REQUEST = "exerciseRequest";

	@Override
	public FthPersonalData create(ResultSet resultSet) throws FthRepositoryException {
		FthPersonalData clientPersonalData = new FthPersonalData();
		try {
			clientPersonalData.setClientId(resultSet.getLong(CLIENT_ID));
			clientPersonalData.setFirstName(resultSet.getString(NAME));
			clientPersonalData.setLastName(resultSet.getString(SURNAME));
			clientPersonalData.setPhotoPath(resultSet.getString(PHOTO_PATH));
			clientPersonalData.setExerciseRequest(resultSet.getBoolean(EXERCISE_REQUEST));
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in CreatorClientPersonalData -> create(ResultSet).", e);
		}
		return clientPersonalData;
	}
}