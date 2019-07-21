package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientGroupByTrainerSpecifier implements FthSelectSpecifier {
	private static final String REQUEST = "SELECT " +
			"clientID, firstName, lastName, photoPath, trainerId " +
			"FROM ClientPersonalData " +
			"WHERE trainerId = ?";

	private FthLong trainerId;

	public ClientGroupByTrainerSpecifier(FthLong trainerId) {
		this.trainerId = trainerId;
	}

	@Override
	public CreatorClientPersonalData createFactory() {
		return new CreatorClientPersonalData();
	}

	@Override
	public PreparedStatement pasteMeta(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, trainerId.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in ClientGroupByTrainerSpecifier -> pasteMeta(PreparedStatement).", e);
		}
		return statement;
	}

	@Override
	public String deriveSequelRequest() {
		return REQUEST;
	}
}