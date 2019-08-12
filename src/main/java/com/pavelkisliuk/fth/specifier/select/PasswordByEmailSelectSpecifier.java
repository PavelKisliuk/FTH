/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code PasswordByEmailSelectSpecifier} class is {@code FthSelectSpecifier} realization
 * for obtainment from AuthenticationData table password.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class PasswordByEmailSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"password " +
			"FROM AuthenticationData WHERE eMail = ?";

	/**
	 * E-mail of user.
	 */
	private FthString eMail;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param eMail for {@code eMail} initialization.
	 */
	public PasswordByEmailSelectSpecifier(FthString eMail) {
		this.eMail = eMail;
	}

	/**
	 * Return factory for {@code FthString} creation.
	 * <p>
	 *
	 * @return factory for {@code FthString} creation.
	 */
	@Override
	public CreatorString createFactory() {
		return new CreatorString();
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
			statement.setString(1, eMail.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in PasswordByEmailSelectSpecifier -> pasteMeta(PreparedStatement).", e);
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