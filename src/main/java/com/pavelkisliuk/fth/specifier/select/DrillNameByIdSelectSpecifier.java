package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DrillNameByIdSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"drillName " +
			"FROM DrillBase " +
			"WHERE drillBaseId = ?";

	private FthLong drillBaseId;

	public DrillNameByIdSelectSpecifier(FthLong drillBaseId) {
		this.drillBaseId = drillBaseId;
	}

	@Override
	public CreatorString createFactory() {
		return new CreatorString();
	}

	@Override
	public PreparedStatement pasteMeta(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, drillBaseId.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in DrillNameByIdSelectSpecifier -> pasteMeta(PreparedStatement).", e);
		}
		return statement;
	}

	@Override
	public String deriveSequelRequest() {
		return REQUEST;
	}
}
