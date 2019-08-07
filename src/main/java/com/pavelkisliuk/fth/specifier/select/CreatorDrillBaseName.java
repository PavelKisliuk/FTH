package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthDrillBase;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatorDrillBaseName implements FthDataByResultSetFactory {
	private static final String MUSCLE_GROUP_ID = "muscleGroupId";
	private static final String DRILL_NAME = "drillName";

	@Override
	public FthDrillBase create(ResultSet resultSet) throws FthRepositoryException {
		FthDrillBase drillBase = new FthDrillBase();
		try {
			drillBase.setMuscleGroupId(resultSet.getLong(MUSCLE_GROUP_ID));
			drillBase.setDrillName(resultSet.getString(DRILL_NAME));
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in CreatorDrillBaseName -> create(ResultSet).", e);
		}
		return drillBase;
	}
}