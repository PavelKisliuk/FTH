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
 * The {@code SetToClientSelectSpecifier} class is {@code FthSelectSpecifier} realization
 * for obtainment from DrillBase table drillName.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class SetToClientSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"setId, necessaryReps, weightTool, restTime " +
			"FROM SetGroup " +
			"WHERE exerciseId = ? AND drillId = ? ORDER BY setNumber";

	/**
	 * ID of exercise.
	 */
	private FthLong exerciseId;

	/**
	 * ID of drill.
	 */
	private FthLong drillId;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param exerciseId for {@code exerciseId} initialization.
	 * @param drillId for {@code drillId} initialization.
	 */
	public SetToClientSelectSpecifier(FthLong exerciseId, FthLong drillId) {
		this.exerciseId = exerciseId;
		this.drillId = drillId;
	}

	/**
	 * Return factory for {@code FthSetToClient} creation.
	 * <p>
	 *
	 * @return factory for {@code FthSetToClient} creation.
	 */
	@Override
	public CreatorSetToClient createFactory() {
		return new CreatorSetToClient();
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
			statement.setLong(2, drillId.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in SetToClientSelectSpecifier -> pasteMeta(PreparedStatement).", e);
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