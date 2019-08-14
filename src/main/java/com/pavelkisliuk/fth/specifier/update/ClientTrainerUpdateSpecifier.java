package com.pavelkisliuk.fth.specifier.update;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientTrainerUpdateSpecifier implements FthUpdateSpecifier {
	/**
	 * Update request to database.
	 */
	private static final String REQUEST = "UPDATE " +
			"ClientPublicData " +
			"SET trainerId = ? " +
			"WHERE clientId = ?";

	/**
	 * ID of client.
	 */
	private FthLong clientId;

	/**
	 * New condition of client request to exercise.
	 */
	private FthLong trainerId;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param clientId  for {@code clientId} initialization.
	 * @param trainerId for {@code trainerId} initialization.
	 */
	public ClientTrainerUpdateSpecifier(FthLong clientId, FthLong trainerId) {
		this.clientId = clientId;
		this.trainerId = trainerId;
	}

	/**
	 * Paste metadata in {@code PreparedStatement} to update this data to database.
	 * <p>
	 *
	 * @param statement for pasting metadata into.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public void update(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, trainerId.get());
			statement.setLong(2, clientId.get());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in ClientTrainerUpdateSpecifier -> update(PreparedStatement).", e);
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
