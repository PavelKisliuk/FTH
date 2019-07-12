/*  By Pavel Kisliuk, 11.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.repository.FthRepositoryFactory;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;

/**
 * The class {@code AllClientEmailSelectSpecifier} is realization of {@code FthSelectSpecifier} for
 * selection all e-mail from table ClientAuthenticationData in database.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see com.pavelkisliuk.fth.repository.FthRepositorySingleton
 * @see FthRepositoryFactory
 * @see FthSelectSpecifier
 * @see AllRegistrationEmailSelectSpecifier
 * @see FthStringCreator
 * @since 12.0
 */
public class AllClientEmailSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Return {@code FthStringCreator} for creation data from database.
	 * <p>
	 *
	 * @return {@code FthStringCreator} for creation data from database by {@code FthRepositorySingleton}.
	 */
	@Override
	public FthRepositoryFactory createFactory() {
		return new FthStringCreator();
	}

	/**
	 * Return {@param statement}.
	 * <p>
	 *
	 * @return {@param statement}.
	 */
	@Override
	public PreparedStatement pasteMeta(PreparedStatement statement) {
		return statement;
	}

	/**
	 * Return SQL request as String.
	 * <p>
	 *
	 * @return SQL request for {@code PreparedStatement} as String.
	 */
	@Override
	public String deriveSequelRequest() {
		return "SELECT clientEmail FROM ClientAuthenticationData";
	}
}