/*  By Pavel Kisliuk, 11.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.repository.FthDataByResultSetFactory;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;

/**
 * The class {@code AllRegistrationEmailSelectSpecifier} is realization of {@code FthSelectSpecifier} for
 * selection all e-mail from table RegistrationData in database.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see FthRepository
 * @see FthDataByResultSetFactory
 * @see FthSelectSpecifier
 * @see AllClientEmailSelectSpecifier
 * @see CreatorString
 * @since 12.0
 */
public class AllRegistrationEmailSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Return {@code CreatorString} for creation data from database.
	 * <p>
	 *
	 * @return {@code CreatorString} for creation data from database by {@code FthRepository}.
	 */
	@Override
	public FthDataByResultSetFactory createFactory() {
		return new CreatorString();
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
		return "SELECT eMail FROM RegistrationData";
	}
}