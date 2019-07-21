package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthenticateSpecifier implements FthSelectSpecifier {
	private static final String request = "SELECT " +
			"COUNT(*) " +
			"FROM AuthenticationData WHERE eMail = ? AND password = ?";

	private FthAuthenticationData authenticationData;

	public AuthenticateSpecifier(FthAuthenticationData authenticationData) {
		this.authenticationData = authenticationData;
	}

	@Override
	public CreatorInteger createFactory() {
		return new CreatorInteger();
	}

	@Override
	public PreparedStatement pasteMeta(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setString(1, authenticationData.getEmail());
			statement.setString(2, authenticationData.getPassword());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in AuthenticateSpecifier -> pasteMeta(PreparedStatement).", e);
		}
		return statement;
	}

	@Override
	public String deriveSequelRequest() {
		return request;
	}
}