/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.update;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthSetFromClient;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code SetFromClientUpdateSpecifier} class is {@code FthUpdateSpecifier} realization for
 * SetGroup updating selfConsistent, helpConsistent, weightTool.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class SetFromClientUpdateSpecifier implements FthUpdateSpecifier {
	/**
	 * Update request to database.
	 */
	private static final String REQUEST = "UPDATE " +
			"SetGroup " +
			"SET selfConsistent = ?, helpConsistent = ?, weightTool = ?" +
			"WHERE setId = ?";

	/**
	 * Set from client.
	 */
	private FthSetFromClient setFromClient;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param setFromClient for {@code setFromClient} initialization.
	 */
	public SetFromClientUpdateSpecifier(FthSetFromClient setFromClient) {
		this.setFromClient = setFromClient;
	}

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
			statement.setInt(1, setFromClient.getSelfConsistent());
			statement.setInt(2, setFromClient.getHelpConsistent());
			statement.setDouble(3, setFromClient.getWeightTool());
			statement.setLong(4, setFromClient.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in SetFromClientUpdateSpecifier -> update(PreparedStatement).", e);
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