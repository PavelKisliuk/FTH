package com.pavelkisliuk.fth.specifier.insert;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.model.FthUserMainData;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewUserInsertSpecifier implements FthInsertSpecifier {
	private static final String TO_TRAINER_TABLE = "INSERT INTO TrainerData ";
	private static final String TO_CLIENT_TABLE = "INSERT INTO ClientPersonalData ";
	private static final String CLIENT_TABLE = "ClientPersonalData";
	/**
	 * Insert request to database.
	 */
	private static final String REQUEST = " " +
			"(firstName, lastName, photoPath) " +
			"VALUES (?, ?, ?)";

	private FthString tableName;
	private FthUserMainData userMainData;

	public NewUserInsertSpecifier(FthString tableName, FthUserMainData userMainData) {
		this.tableName = tableName;
		this.userMainData = userMainData;
	}

	@Override
	public void insert(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setString(1, userMainData.getName());
			statement.setString(2, userMainData.getSurname());
			statement.setString(3, "../avatars/default.jpg");
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in NewTrainerInsertSpecifier -> insert().", e);
		}
	}

	@Override
	public String deriveSequelRequest() {
		return toRequest() + REQUEST;
	}

	private String toRequest() {
		if (tableName.get().equals(CLIENT_TABLE)) {
			return TO_CLIENT_TABLE;
		} else {
			return TO_TRAINER_TABLE;
		}
	}
}
