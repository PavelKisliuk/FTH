package com.pavelkisliuk.fth.specifier.insert;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthenticateDataInsertSpecifier implements FthInsertSpecifier {
	private static final String BASE = "INSERT INTO AuthenticationData (";
	/**
	 * Insert request to database.
	 */
	private static final String REQUEST = "" +
			", eMail, password) " +
			"(SELECT ?, eMail, password " +
			"FROM RegistrationData " +
			"WHERE registrationKey = ?)";

	private FthBoolean isTrainer;
	private FthLong id;
	private FthString registrationKey;

	public AuthenticateDataInsertSpecifier(FthString registrationKey, FthLong id, FthBoolean isTrainer) {
		this.registrationKey = registrationKey;
		this.id = id;
		this.isTrainer = isTrainer;
	}

	@Override
	public void insert(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, id.get());
			statement.setString(2, registrationKey.get());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in AuthenticateDataInsertSpecifier -> insert().", e);
		}
	}

	@Override
	public String deriveSequelRequest() {
		return BASE + toRequest() + REQUEST;
	}

	private String toRequest() {
		if (isTrainer.get()) {
			return "trainerId";
		} else {
			return "clientId";
		}
	}
}
