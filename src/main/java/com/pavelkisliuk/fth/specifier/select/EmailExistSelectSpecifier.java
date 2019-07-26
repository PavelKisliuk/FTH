/*  By Pavel Kisliuk, 26.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code EmailExistSelectSpecifier} class is {@code FthSelectSpecifier} realization
 * for checking of existence concrete e-mail in AuthenticationData table in database.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class EmailExistSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"COUNT(*) " +
			"FROM AuthenticationData WHERE eMail = ?";

	/**
	 * Searching e-mail.
	 */
	private FthString eMail;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param eMail for {@code eMail} initialization.
	 */
	public EmailExistSelectSpecifier(FthString eMail) {
		this.eMail = eMail;
	}

	/**
	 * Return factory for {@code FthInt} creation.
	 * <p>
	 *
	 * @return factory for {@code FthInt} creation.
	 */
	@Override
	public FthDataByResultSetFactory createFactory() {
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
			statement.setString(1, eMail.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in EmailExistSelectSpecifier -> pasteMeta(PreparedStatement).", e);
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
