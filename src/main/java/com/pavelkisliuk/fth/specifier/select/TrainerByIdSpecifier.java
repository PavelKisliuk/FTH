package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainerByIdSpecifier implements FthSelectSpecifier {
	private static final String  REQUEST = "SELECT " +
			"trainerId, firstName, lastName, photoPath " +
			"FROM TrainerData " +
			"WHERE trainerId = ?";

	private FthLong trainerId;

	public TrainerByIdSpecifier(FthLong trainerId) {
		this.trainerId = trainerId;
	}

	@Override
	public CreatorTrainerData createFactory() {
		return new CreatorTrainerData();
	}

	@Override
	public PreparedStatement pasteMeta(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, trainerId.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in IdByEmailSpecifier -> pasteMeta(PreparedStatement).", e);
		}
		return statement;
	}

	@Override
	public String deriveSequelRequest() {
		return REQUEST;
	}
}
