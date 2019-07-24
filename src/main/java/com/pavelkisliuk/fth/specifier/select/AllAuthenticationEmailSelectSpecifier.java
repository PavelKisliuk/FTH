/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;

/**
 * The {@code AllAuthenticationEmailSelectSpecifier} class is {@code FthSelectSpecifier} realization
 * for selection all e-mail from table AuthenticationData in database.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class AllAuthenticationEmailSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"eMail " +
			"FROM AuthenticationData";

	/**
	 * Return factory for {@code FthString} creation.
	 * <p>
	 *
	 * @return factory for {@code FthString} creation.
	 */
	@Override
	public FthDataByResultSetFactory createFactory() {
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
	public PreparedStatement pasteMeta(PreparedStatement statement) {
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