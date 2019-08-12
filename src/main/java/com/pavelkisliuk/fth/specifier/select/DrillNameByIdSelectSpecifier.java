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
 * The {@code DrillNameByIdSelectSpecifier} class is {@code FthSelectSpecifier} realization
 * for obtainment from DrillBase table drillName.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class DrillNameByIdSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"drillName " +
			"FROM DrillBase " +
			"WHERE drillBaseId = ?";

	/**
	 * ID of drillBase element.
	 */
	private FthLong drillBaseId;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param drillBaseId for {@code drillBaseId} initialization.
	 */
	public DrillNameByIdSelectSpecifier(FthLong drillBaseId) {
		this.drillBaseId = drillBaseId;
	}

	/**
	 * Return factory for {@code FthString} creation.
	 * <p>
	 *
	 * @return factory for {@code FthString} creation.
	 */
	@Override
	public CreatorString createFactory() {
		return new CreatorString();
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
			statement.setLong(1, drillBaseId.get());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in DrillNameByIdSelectSpecifier -> pasteMeta(PreparedStatement).", e);
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