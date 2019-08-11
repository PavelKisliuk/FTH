package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetToClientSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"setId, necessaryReps, weightTool, restTime " +
			"FROM SetGroup " +
			"WHERE exerciseId = ? AND drillId = ? ORDER BY setNumber";

	private FthLong exerciseId;
	private FthLong drillId;

	public SetToClientSelectSpecifier(FthLong exerciseId, FthLong drillId) {
		this.exerciseId = exerciseId;
		this.drillId = drillId;
	}

	@Override
	public CreatorSetToClient createFactory() {
		return new CreatorSetToClient();
	}

	@Override
	public PreparedStatement pasteMeta(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, exerciseId.get());
			statement.setLong(2, drillId.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in SetToClientSelectSpecifier -> pasteMeta(PreparedStatement).", e);
		}
		return statement;
	}

	@Override
	public String deriveSequelRequest() {
		return REQUEST;
	}
}
