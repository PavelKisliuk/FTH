/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.insert;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code NewExerciseInsertSpecifier} class is {@code FthInsertSpecifier} realization
 * for insertion in ExerciseGroup table new row.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class NewExerciseInsertSpecifier implements FthInsertSpecifier {
	/**
	 * Insert request to database.
	 */
	private static final String REQUEST = "INSERT INTO ExerciseGroup " +
			"(clientId) " +
			"VALUES (?)";

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
	public NewExerciseInsertSpecifier(FthLong clientId) {
		this.clientId = clientId;
	}

	/**
	 * Paste metadata in {@code PreparedStatement} to insert this data to database.
	 * <p>
	 *
	 * @param statement for pasting metadata into.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public void insert(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, clientId.get());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in NewExerciseInsertSpecifier -> insert().", e);
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