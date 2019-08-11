/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.insert;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthSetGroup;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code SetGroupFromTrainerInsertSpecifier} class is {@code FthInsertSpecifier} realization
 * for insertion in SetGroup table setNumber, necessaryReps, weightTool, restTime,
 * exerciseId, drillId as new row.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class SetGroupFromTrainerInsertSpecifier implements FthInsertSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "INSERT INTO SetGroup " +
			"(setNumber, necessaryReps, weightTool, restTime, exerciseId, drillId) " +
			"VALUES (?, ?, ?, ?, ?, ?)";

	/**
	 * Field describe SetGroup table in database.
	 */
	private FthSetGroup setGroup;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param setGroup for {@code setGroup} initialization.
	 */
	public SetGroupFromTrainerInsertSpecifier(FthSetGroup setGroup) {
		this.setGroup = setGroup;
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
			statement.setInt(1, setGroup.getSetNumber());
			statement.setInt(2, setGroup.getNecessaryReps());
			statement.setDouble(3, setGroup.getWeightTool());
			statement.setInt(4, setGroup.getRestTime());
			statement.setLong(5, setGroup.getExerciseId());
			statement.setLong(6, setGroup.getDrillId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in SetGroupFromTrainerInsertSpecifier -> insert().", e);
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