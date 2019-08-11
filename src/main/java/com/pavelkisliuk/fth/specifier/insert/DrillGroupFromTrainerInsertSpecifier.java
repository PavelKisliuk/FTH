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
	 * Select request to database.
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
	 * @param preparedStatement for pasting metadata into.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public void insert(PreparedStatement preparedStatement) throws FthRepositoryException {
		try {
			preparedStatement.setInt(1, drillGroup.getDrillNumber());
			preparedStatement.setLong(2, drillGroup.getExerciseId());
			preparedStatement.setLong(3, drillGroup.getDrillBaseId());
			preparedStatement.setLong(4, drillGroup.getMuscleGroupId());
			preparedStatement.executeUpdate();
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