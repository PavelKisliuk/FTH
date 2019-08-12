/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.update;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthClientPublicData;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code SeasonPassUpdateSpecifier} class is {@code FthUpdateSpecifier} realization for
 * ClientPublicData updating season pass information.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class SpreadSeasonPassUpdateSpecifier implements FthUpdateSpecifier {
	/**
	 * Update request to database.
	 */
	private static final String REQUEST = "UPDATE " +
			"ClientPublicData " +
			"SET restVisitation = ?, expiredDay = ? " +
			"WHERE clientId = ?";

	/**
	 * Public data about client.
	 */
	private FthClientPublicData clientPublicData;

	/**
	 * Paste metadata in {@code PreparedStatement} to update this data to database.
	 * <p>
	 *
	 * @param statement for pasting metadata into.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public void update(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, clientPublicData.getRestVisitation());
			statement.setLong(2, clientPublicData.getExpiredDay());
			statement.setLong(3, clientPublicData.getClientId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in SpreadSeasonPassUpdateSpecifier -> update(PreparedStatement).", e);
		}
	}

	/**
	 * Return SQL request as String.
	 * <p>
	 *
	 * @return SQL request for {@code PreparedStatement} as String.
	 */
	@Override
	public String deriveSequelRequest() {
		return REQUEST;
	}
}