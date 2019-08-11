/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.insert;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthDrillGroup;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code DrillGroupFromTrainerInsertSpecifier} class is {@code FthInsertSpecifier} realization
 * for insertion in DrillGroup table drillNumber, exerciseId, drillBaseId, muscleGroupId as new row.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class DrillGroupFromTrainerInsertSpecifier implements FthInsertSpecifier {
	/**
	 * Insert request to database.
	 */
	private static final String REQUEST = "INSERT INTO DrillGroup " +
			"(drillNumber, exerciseId, drillBaseId, muscleGroupId) " +
			"VALUES (?, ?, ?, ?)";

	/**
	 * Field describe DrillGroup table in database.
	 */
	private FthDrillGroup drillGroup;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param drillGroup for {@code drillGroup} initialization.
	 */
	public DrillGroupFromTrainerInsertSpecifier(FthDrillGroup drillGroup) {
		this.drillGroup = drillGroup;
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
			statement.setInt(1, drillGroup.getDrillNumber());
			statement.setLong(2, drillGroup.getExerciseId());
			statement.setLong(3, drillGroup.getDrillBaseId());
			statement.setLong(4, drillGroup.getMuscleGroupId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in DrillGroupFromTrainerInsertSpecifier -> insert().", e);
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