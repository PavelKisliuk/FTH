/*  By Pavel Kisliuk, 24.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.specifier.select;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthRefreshCondition;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * The {@code ClientGroupByConditionSelectSpecifier} class is {@code FthSelectSpecifier} realization
 * for obtainment from ClientPersonalData, ClientPublicData table clientId, firstName, lastName,
 * photoPath, exerciseRequest but only if data satisfy specified conditions.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ClientGroupByConditionSelectSpecifier implements FthSelectSpecifier {
	/**
	 * The {@code SortConditionNameType} class is {@code enum} class for describing sort of data
	 * from database.
	 */
	public enum SortConditionNameType {
		//sort data by name
		NAME(" ORDER BY ClientPersonalData.lastName, ClientPersonalData.firstName"),
		//sort data by surname
		SURNAME(" ORDER BY ClientPersonalData.lastName, ClientPersonalData.firstName");
		private String sortCondition;

		SortConditionNameType(String sortCondition) {
			this.sortCondition = sortCondition;
		}
	}

	/**
	 * The {@code SortConditionNameType} class is {@code enum} class for describing conditions of data
	 * which can be selected from database.
	 */
	public enum SortConditionQualityType {
		//return client's with expired season's
		EXPIRED(" AND (ClientPublicData.expiredDay < " + new Date().getTime() +
				" OR ClientPublicData.restVisitation = 0)"),
		//return client's with actual season's
		ACTUAL(" AND (ClientPublicData.expiredDay >= " + new Date().getTime() +
				" AND (ClientPublicData.restVisitation > 0 OR ClientPublicData.restVisitation = -1))"),
		//return client's who made request to exercise
		REQUESTED(" AND ClientPublicData.exerciseRequest = true"),
		//return all client's
		EACH_AND_EVERY("");
		private String chooseCondition;

		SortConditionQualityType(String chooseCondition) {
			this.chooseCondition = chooseCondition;
		}
	}

	/**
	 * Select request to database.
	 */
	private static final String REQUEST = "SELECT " +
			"ClientPersonalData.clientId, " +
			"ClientPersonalData.firstName, " +
			"ClientPersonalData.lastName, " +
			"ClientPersonalData.photoPath, " +
			"ClientPublicData.exerciseRequest " +
			"FROM ClientPersonalData INNER JOIN ClientPublicData " +
			"ON ClientPersonalData.clientId = ClientPublicData.clientId " +
			"WHERE trainerId = ?";

	/**
	 * Data for creation of request to database, which contain special condition's.
	 */
	private FthRefreshCondition refreshCondition;

	/**
	 * Constructor for fields initialization.
	 * <p>
	 *
	 * @param refreshCondition for {@code refreshCondition} initialization.
	 */
	public ClientGroupByConditionSelectSpecifier(FthRefreshCondition refreshCondition) {
		this.refreshCondition = refreshCondition;
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
			statement.setLong(1, refreshCondition.getTrainerId());
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in ClientGroupByConditionSelectSpecifier -> pasteMeta(PreparedStatement).", e);
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
		return improveRequest();
	}

	/**
	 * Add to {@code REQUEST} necessary additional element's.
	 * <p>
	 *
	 * @return improved request to database.
	 */
	private String improveRequest() {
		StringBuilder request = new StringBuilder(REQUEST);
		switch (SortConditionQualityType.valueOf(refreshCondition.getConditionQuality())) {
			case EXPIRED:
				request.append(SortConditionQualityType.EXPIRED.chooseCondition);
				break;
			case ACTUAL:
				request.append(SortConditionQualityType.ACTUAL.chooseCondition);
				break;
			case REQUESTED:
				request.append(SortConditionQualityType.REQUESTED.chooseCondition);
				break;
			case EACH_AND_EVERY:
				break;
			default:
				throw new EnumConstantNotPresentException(SortConditionQualityType.class,
						"Not correct enum element in SortConditionQualityType" +
								"ClientGroupByConditionSelectSpecifier -> " +
								"operateTransaction(List<TransactionDescriber>).");
		}
		switch (SortConditionNameType.valueOf(refreshCondition.getConditionName())) {
			case NAME:
				request.append(SortConditionNameType.NAME.sortCondition);
				break;
			case SURNAME:
				request.append(SortConditionNameType.SURNAME.sortCondition);
				break;
			default:
				throw new EnumConstantNotPresentException(SortConditionNameType.class,
						"Not correct enum element in SortConditionNameType" +
								"ClientGroupByConditionSelectSpecifier -> " +
								"operateTransaction(List<TransactionDescriber>).");
		}
		return request.toString();
	}
}