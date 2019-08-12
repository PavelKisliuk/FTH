/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.insert;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthDrillBase;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code NewDrillInsertSpecifier} class is {@code FthInsertSpecifier} realization
 * for insertion in DrillBase table new row.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class NewDrillInsertSpecifier implements FthInsertSpecifier {
	/**
	 * Insert request to database.
	 */
	private static final String REQUEST = "INSERT INTO DrillBase " +
			"(muscleGroupId, drillName, trainerId) " +
			"VALUES (?, ?, ?)";

	/**
	 * ID of trainer.
	 */
	private FthLong trainerId;

	/**
	 * Drill base class.
	 */
	private FthDrillBase drillBase;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param trainerId for {@code clientId} initialization.
	 * @param drillBase for {@code drillBase} initialization.
	 */
	public NewDrillInsertSpecifier(FthLong trainerId, FthDrillBase drillBase) {
		this.trainerId = trainerId;
		this.drillBase = drillBase;
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
			statement.setLong(1, drillBase.getMuscleGroupId());
			statement.setString(2, drillBase.getDrillName());
			statement.setLong(3, trainerId.get());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQL exception in NewDrillInsertSpecifier -> insert().", e);
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