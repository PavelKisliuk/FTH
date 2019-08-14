package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDataSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"firstName, lastName " +
			"FROM RegistrationData WHERE registrationKey = ?";

	private FthString registrationKey;

	public RegistrationDataSelectSpecifier(FthString registrationKey) {
		this.registrationKey = registrationKey;
	}

	@Override
	public CreatorPureUserData createFactory() {
		return new CreatorPureUserData();
	}

	@Override
	public PreparedStatement pasteMeta(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setString(1, registrationKey.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in RegistrationDataSelectSpecifier -> pasteMeta(PreparedStatement).", e);
		}
		return statement;
	}

	@Override
	public String deriveSequelRequest() {
		return REQUEST;
	}
}
