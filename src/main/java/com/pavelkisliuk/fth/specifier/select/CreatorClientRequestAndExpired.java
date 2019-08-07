/*  By Pavel Kisliuk, 07.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthClientPublicData;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatorClientRequestAndExpired implements FthDataByResultSetFactory {
	private static final String EXERCISE_REQUEST = "exerciseRequest";
	private static final String EXPIRED_DAY = "expiredDay";
	private static final String REST_VISITATION = "restVisitation";

	@Override
	public FthClientPublicData create(ResultSet resultSet) throws FthRepositoryException {
		FthClientPublicData clientPublicData = new FthClientPublicData();
		try {
			clientPublicData.setExerciseRequest(resultSet.getBoolean(EXERCISE_REQUEST));
			clientPublicData.setExpiredDay(resultSet.getLong(EXPIRED_DAY));
			clientPublicData.setRestVisitation(resultSet.getInt(REST_VISITATION));
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in CreatorClientRequestAndExpired -> create(ResultSet).", e);
		}
		return clientPublicData;
	}
}