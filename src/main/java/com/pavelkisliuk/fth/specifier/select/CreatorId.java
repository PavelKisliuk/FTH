package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatorId implements FthDataByResultSetFactory {
	private static final int TRAINER_ID = 1;
	private static final int CLIENT_ID = 2;

	@Override
	public FthLong create(ResultSet resultSet) throws FthRepositoryException {
		try {
			long trainerId = resultSet.getLong(TRAINER_ID);
			long clientId = resultSet.getLong(CLIENT_ID);
			return (trainerId != 0) ? new FthLong(trainerId) : new FthLong(clientId);
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in CreatorId -> create(ResultSet).", e);
		}
	}
}