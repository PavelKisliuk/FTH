package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IdByEmailSpecifier implements FthSelectSpecifier {
	private static final String REQUEST = "SELECT " +
			"personalID " +
			"FROM AuthenticationData " +
			"WHERE eMail = ?";

	private FthString eMail;

	public IdByEmailSpecifier(FthString eMail) {
		this.eMail = eMail;
	}

	@Override
	public CreatorLong createFactory() {
		return new CreatorLong();
	}

	@Override
	public PreparedStatement pasteMeta(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setString(1, eMail.get());
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