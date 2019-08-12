/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code DrillToClientSelectSpecifier} class is {@code FthSelectSpecifier} realization
 * for obtainment from DrillGroup table drillId, drillBaseId.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class DrillToClientSelectSpecifier implements FthSelectSpecifier {
	private static final String REQUEST = "SELECT " +
			"drillId, drillBaseId " +
			"FROM DrillGroup WHERE exerciseId = ? " +
			"ORDER BY drillNumber";

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
	public DrillToClientSelectSpecifier(FthLong exerciseId) {
		this.exerciseId = exerciseId;
	}

	/**
	 * Return factory for {@code FthDrillToClient} creation.
	 * <p>
	 *
	 * @return factory for {@code FthDrillToClient} creation.
	 */
	@Override
	public CreatorDrillToClient createFactory() {
		return new CreatorDrillToClient();
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
			statement.setLong(1, exerciseId.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in DrillToClientSelectSpecifier -> pasteMeta(PreparedStatement).", e);
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