/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.update;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code TrainerRemoveClientUpdateSpecifier} class is {@code FthUpdateSpecifier} realization to update
 * trainerId column in ClientPublicData table to -1 value.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class TrainerRemoveClientUpdateSpecifier implements FthUpdateSpecifier {
	/**
	 * Update request to database.
	 */
	private static final String REQUEST = "UPDATE " +
			"ClientPublicData " +
			"SET trainerId = -1 " +
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
	public TrainerRemoveClientUpdateSpecifier(FthLong clientId) {
		this.clientId = clientId;
	}

	/**
	 * Paste metadata in {@param statement} and update it(statement).
	 * <p>
	 *
	 * @param statement is precompiled SQL statement.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public void update(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, clientId.get());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in TrainerRemoveClientUpdateSpecifier -> update(PreparedStatement).", e);
		}
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