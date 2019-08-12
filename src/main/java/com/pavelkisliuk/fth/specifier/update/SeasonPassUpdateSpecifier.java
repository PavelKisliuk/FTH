/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.update;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthInt;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code SeasonPassUpdateSpecifier} class is {@code FthUpdateSpecifier} realization for
 * ClientPublicData updating restVisitation.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class SeasonPassUpdateSpecifier implements FthUpdateSpecifier {
	/**
	 * Update request to database.
	 */
	private static final String REQUEST = "UPDATE " +
			"ClientPublicData " +
			"SET restVisitation = restVisitation + ?" +
			"WHERE clientId = ?";

	private FthInt augend;
	private FthLong clientId;

	public SeasonPassUpdateSpecifier(FthInt augend, FthLong clientId) {
		this.augend = augend;
		this.clientId = clientId;
	}

	@Override
	public void update(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, augend.get());
			statement.setLong(2, clientId.get());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in SeasonPassUpdateSpecifier -> update(PreparedStatement).", e);
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