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
 * The {@code AllClientByTrainerSelectSpecifier} is {@code FthUpdateSpecifier} realization to update
 * trainerId column in ClientPublicData table to -1 value.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class AllClientByTrainerSelectSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"clientID, firstName, lastName, photoPath, trainerId " +
			"FROM ClientPersonalData " +
			"WHERE trainerId = ?";

	/**
	 * ID of trainer.
	 */
	private FthLong trainerId;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param trainerId for {@code clientId} initialization.
	 */
	public AllClientByTrainerSelectSpecifier(FthLong trainerId) {
		this.trainerId = trainerId;
	}

	/**
	 * Return factory for {@code FthPersonalData} creation.
	 * <p>
	 *
	 * @return factory for {@code FthPersonalData} creation.
	 */
	@Override
	public CreatorClientPersonalData createFactory() {
		return new CreatorClientPersonalData();
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
					"SQLException in AllClientByTrainerSelectSpecifier -> pasteMeta(PreparedStatement).", e);
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