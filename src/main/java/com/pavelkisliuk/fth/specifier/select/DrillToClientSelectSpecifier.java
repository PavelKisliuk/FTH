package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DrillToClientSelectSpecifier implements FthSelectSpecifier {
	private static final String REQUEST = "SELECT " +
			"drillId, drillBaseId " +
			"FROM DrillGroup WHERE exerciseId = ? " +
			"ORDER BY drillNumber";

	private FthLong exerciseId;

	public DrillToClientSelectSpecifier(FthLong exerciseId) {
		this.exerciseId = exerciseId;
	}

	@Override
	public CreatorDrillToClient createFactory() {
		return new CreatorDrillToClient();
	}

	@Override
	public PreparedStatement pasteMeta(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, exerciseId.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in DrillToClientSelectSpecifier -> pasteMeta(PreparedStatement).", e);
		}
		return statement;
	}

	@Override
	public String deriveSequelRequest() {
		return REQUEST;
	}
}
