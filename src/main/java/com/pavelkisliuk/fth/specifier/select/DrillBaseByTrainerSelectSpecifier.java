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
 * The {@code DrillBaseByTrainerSelectSpecifier} class is {@code FthSelectSpecifier} realization
 * for obtainment from DrillBase table muscleGroupId, drillName, drillBaseId.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class DrillBaseByTrainerSelectSpecifier implements FthSelectSpecifier {
	private static final String REQUEST = "SELECT " +
			"drillBaseId, muscleGroupId, drillName " +
			"FROM DrillBase WHERE trainerId = ? ORDER BY muscleGroupId, drillName";

	/**
	 * ID of trainer.
	 */
	private FthLong trainerId;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param trainerId for {@code trainerId} initialization.
	 */
	public DrillBaseByTrainerSelectSpecifier(FthLong trainerId) {
		this.trainerId = trainerId;
	}

	/**
	 * Return factory for {@code FthDrillBase} creation.
	 * <p>
	 *
	 * @return factory for {@code FthDrillBase} creation.
	 */
	@Override
	public CreatorDrillBase createFactory() {
		return new CreatorDrillBase();
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
			statement.setLong(1, trainerId.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in DrillBaseByTrainerSelectSpecifier -> pasteMeta(PreparedStatement).", e);
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