package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthSetToClient;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatorSetToClient implements FthDataByResultSetFactory {
	private static final String SET_ID = "setId";
	private static final String NECESSARY_REPS = "necessaryReps";
	private static final String WEIGHT_TOOL = "weightTool";
	private static final String REST_TIME = "restTime";

	@Override
	public FthSetToClient create(ResultSet resultSet) throws FthRepositoryException {
		FthSetToClient drillToClient = new FthSetToClient();
		try {
			drillToClient.setSetId(resultSet.getLong(SET_ID));
			drillToClient.setNecessaryReps(resultSet.getInt(NECESSARY_REPS));
			drillToClient.setWeightTool(resultSet.getDouble(WEIGHT_TOOL));
			drillToClient.setRestTime(resultSet.getInt(REST_TIME));
		} catch (SQLException e) {
			throw new FthRepositoryException("SQL exception in CreatorSetToClient -> create(ResultSet).", e);
		}
		return drillToClient;
	}
}
