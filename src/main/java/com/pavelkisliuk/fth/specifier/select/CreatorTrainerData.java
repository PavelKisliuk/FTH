package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthTrainerData;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

class CreatorTrainerData implements FthDataByResultSetFactory {
	private static final String ID = "trainerId";
	private static final String NAME = "firstName";
	private static final String SURNAME = "lastName";
	private static final String PHOTO_PATH = "photoPath";

	@Override
	public FthTrainerData create(ResultSet resultSet) throws FthRepositoryException {
		FthTrainerData trainerData = new FthTrainerData();
		try {
			trainerData.setTrainerId(resultSet.getLong(ID));
			trainerData.setName(resultSet.getString(NAME));
			trainerData.setSurname(resultSet.getString(SURNAME));
			trainerData.setPhotoPath(resultSet.getString(PHOTO_PATH));
		} catch (SQLException e) {
			throw new FthRepositoryException("SQL exception in CreatorString -> create(ResultSet).", e);
		}
		return trainerData;
	}
}