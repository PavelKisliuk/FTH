/*  By Pavel Kisliuk, 07.08.2019
 *  This is class for education and nothing rights don't reserved.
 */
package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code LastNumberClientExerciseSelectSpecifier} class is {@code FthSelectSpecifier} realization
 * for obtainment from ExerciseGroup table last client exerciseNumber(it's max element).
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class LastNumberClientExerciseSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"MAX(exerciseNumber) " +
			"FROM ExerciseGroup WHERE clientId = ?";

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
	public LastNumberClientExerciseSelectSpecifier(FthLong clientId) {
		this.clientId = clientId;
	}

	/**
	 * Return factory for {@code FthInt} creation.
	 * <p>
	 *
	 * @return factory for {@code FthInt} creation.
	 */
	@Override
	public CreatorInt createFactory() {
		return new CreatorInt();
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
					"SQLException in LastNumberClientExerciseSelectSpecifier -> pasteMeta(PreparedStatement).", e);
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