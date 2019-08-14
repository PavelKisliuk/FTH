/*  By Pavel Kisliuk, 07.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthDrillBase;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

class CreatorDrillBase implements FthDataByResultSetFactory {
	private static final String DRILL_BASE_ID = "drillBaseId";
	private static final String MUSCLE_GROUP_ID = "muscleGroupId";
	private static final String DRILL_NAME = "drillName";

	@Override
	public FthDrillBase create(ResultSet resultSet) throws FthRepositoryException {
		FthDrillBase drillBase = new FthDrillBase();
		try {
			drillBase.setDrillBaseId(resultSet.getLong(DRILL_BASE_ID));
			drillBase.setMuscleGroupId(resultSet.getLong(MUSCLE_GROUP_ID));
			drillBase.setDrillName(resultSet.getString(DRILL_NAME));
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in CreatorDrillBase -> create(ResultSet).", e);
		}
		return drillBase;
	}
}