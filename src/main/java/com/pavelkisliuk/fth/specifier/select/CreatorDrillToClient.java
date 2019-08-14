package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthDrillToClient;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

class CreatorDrillToClient implements FthDataByResultSetFactory {
	private static final String DRILL_ID = "drillId";
	private static final String DRILL_BASE_ID = "drillBaseId";

	@Override
	public FthDrillToClient create(ResultSet resultSet) throws FthRepositoryException {
		FthDrillToClient drillToClient = new FthDrillToClient();
		try {
			drillToClient.setDrillId(resultSet.getLong(DRILL_ID));
			drillToClient.setDrillBaseId(resultSet.getLong(DRILL_BASE_ID));
		} catch (SQLException e) {
			throw new FthRepositoryException("SQL exception in CreatorDrillToClient -> create(ResultSet).", e);
		}
		return drillToClient;
	}
}
