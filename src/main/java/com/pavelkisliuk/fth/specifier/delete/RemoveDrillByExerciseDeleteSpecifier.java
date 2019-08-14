/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.delete;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthDeleteSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code RemoveDrillByExerciseDeleteSpecifier} class is {@code FthDeleteSpecifier} realization for
 * removing from DrillGroup all row's by exerciseId.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class RemoveDrillByExerciseDeleteSpecifier implements FthDeleteSpecifier {
	/**
	 * Delete request to database.
	 */
	private static final String REQUEST = "DELETE FROM DrillGroup " +
			"WHERE exerciseId = ?";

	/**
	 * ID of exercise.
	 */
	private FthLong exerciseId;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param exerciseId for {@code exerciseId} initialization.
	 */
	public RemoveDrillByExerciseDeleteSpecifier(FthLong exerciseId) {
		this.exerciseId = exerciseId;
	}

	/**
	 * Paste metadata in {@code PreparedStatement} to delete this data from database.
	 * <p>
	 *
	 * @param statement for pasting metadata into.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public void delete(PreparedStatement statement) throws FthRepositoryException {
		try {
			statement.setLong(1, exerciseId.get());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in RemoveDrillByExerciseDeleteSpecifier -> delete().", e);
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