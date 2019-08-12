/*  By Pavel Kisliuk, 07.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code AuthenticateTrainerSelectSpecifier} class is {@code FthSelectSpecifier} realization
 * for checking of existence concrete trainer in AuthenticationData table in database.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class AuthenticateTrainerSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"COUNT(*) " +
			"FROM AuthenticationData WHERE eMail = ? AND trainerId IS NOT NULL";

	/**
	 * Class with e-mail and password fields.
	 */
	private FthAuthenticationData authenticationData;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param authenticationData for {@code authenticationData} initialization.
	 */
	public AuthenticateTrainerSelectSpecifier(FthAuthenticationData authenticationData) {
		this.authenticationData = authenticationData;
	}

	/**
	 * Return factory for {@code FthInt} creation.
	 * <p>
	 *
	 * @return factory for {@code FthInt} creation.
	 */
	@Override
	public CreatorInteger createFactory() {
		return new CreatorInteger();
	}

	/**
	 * Paste metadata in {@param statement} and return it.
	 * <p>
	 *
	 * @param statement for pasting metadata into.
	 * @return {@param statement}.
	 */
	@Override
	public PreparedStatement pasteMeta(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setString(1, authenticationData.getEmail());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in AuthenticateSelectSpecifier -> pasteMeta(PreparedStatement).", e);
		}
		return statement;
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