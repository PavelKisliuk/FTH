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
 * The {@code TrainerByIdSpecifier} class is {@code FthUpdateSpecifier} realization for retrieving trainer
 * main information by id.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class TrainerByIdSpecifier implements FthSelectSpecifier {
	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"trainerId, firstName, lastName, photoPath " +
			"FROM TrainerData " +
			"WHERE trainerId = ?";

	/**
	 * Trainer ID.
	 */
	private FthLong trainerId;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param trainerId for {@code trainerId} initialization.
	 */
	public TrainerByIdSpecifier(FthLong trainerId) {
		this.trainerId = trainerId;
	}

	/**
	 * Return factory for {@code FthTrainerData} creation.
	 * <p>
	 *
	 * @return factory for {@code FthTrainerData} creation.
	 */
	@Override
	public CreatorUserMainData createFactory() {
		return new CreatorUserMainData();
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
					"SQLException in IdByEmailSpecifier -> pasteMeta(PreparedStatement).", e);
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