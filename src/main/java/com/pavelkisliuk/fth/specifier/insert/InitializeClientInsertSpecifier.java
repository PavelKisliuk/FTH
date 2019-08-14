package com.pavelkisliuk.fth.specifier.insert;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitializeClientInsertSpecifier implements FthInsertSpecifier {
	/**
	 * Insert request to database.
	 */
	private static final String REQUEST = "INSERT INTO ClientPublicData " +
			"(clientId, unavailableTrainerGroup, exerciseRequest, expiredDay, restVisitation) " +
			"VALUES (?, ',', false, 0, 0)";

	private FthLong clientId;

	public InitializeClientInsertSpecifier(FthLong clientId) {
		this.clientId = clientId;
	}

	/**
	 * Paste metadata in {@code PreparedStatement} to insert this data to database.
	 * <p>
	 *
	 * @param statement for pasting metadata into.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public void insert(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, clientId.get());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in InitializeClientInsertSpecifier -> insert().", e);
		}
	}

	/**
	 * Return SQL request as String.
	 * <p>
	 *
	 * @return SQL request for {@code PreparedStatement} as String.
	 */
	@Override
	public String deriveSequelRequest() {
		return REQUEST;
	}
}
