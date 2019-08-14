package com.pavelkisliuk.fth.specifier.delete;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.specifier.FthDeleteSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveConfirmedUser implements FthDeleteSpecifier {
	/**
	 * Delete request to database.
	 */
	private static final String REQUEST = "DELETE FROM RegistrationData " +
			"WHERE registrationKey = ?";

	private FthString registrationKey;

	public RemoveConfirmedUser(FthString registrationKey) {
		this.registrationKey = registrationKey;
	}

	/**
	 * Paste metadata in {@code PreparedStatement} to delete this data from database.
	 * <p>
	 *
	 * @param statement for pasting metadata into.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public void delete(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setString(1, registrationKey.get());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in RemoveConfirmedClient -> delete().", e);
		}
	}

	/**
	 * Return {@code REQUEST}.
	 * <p>
	 *
	 * @return {@code REQUEST}.
	 */
	@Override
	public String deriveSequelRequest() {
		return REQUEST;
	}
}
