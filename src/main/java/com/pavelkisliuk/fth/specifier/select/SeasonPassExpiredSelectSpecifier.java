/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * The {@code ClientByIdSelectSpecifier} class is {@code FthUpdateSpecifier} realization for retrieving condition of client
 * season pass.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class SeasonPassExpiredSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"(CASE WHEN expiredDay > " + new Date().getTime() + " AND restVisitation != 0 " +
			"THEN true ELSE false END) " +
			"AS RESULT FROM ClientPublicData " +
			"WHERE clientId = ?";

	/**
	 * ID of client.
	 */
	private FthLong clientId;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param clientId for {@code clientId} initialization.
	 */
	public SeasonPassExpiredSelectSpecifier(FthLong clientId) {
		this.clientId = clientId;
	}

	/**
	 * Return factory for {@code FthBoolean} creation.
	 * <p>
	 *
	 * @return factory for {@code FthBoolean} creation.
	 */
	@Override
	public CreatorBoolean createFactory() {
		return new CreatorBoolean();
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
			statement.setLong(1, clientId.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in SeasonPassExpiredSelectSpecifier -> pasteMeta(PreparedStatement).", e);
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