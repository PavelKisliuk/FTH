/*  By Pavel Kisliuk, 08.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.controller.obtainmentservice;

import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthRefreshCondition;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.ClientGroupByConditionSelectSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code ConditionClientGroupByTrainerService} class is {@code FthService} realization for
 * obtainment specify list of client's in special order.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ConditionClientGroupByTrainerService implements FthService<FthRefreshCondition> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Retrieve list of clint's satisfied designated condition's and specified order and return it as JSON string.
	 * <p>
	 *
	 * @param refreshCondition is condition wrapper.
	 * @return list of clint's satisfied designated condition's and specified order.
	 * @throws FthControllerException if {@param refreshCondition} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthRefreshCondition refreshCondition) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start ConditionClientGroupByTrainerService -> serve(FthLong).");
		if (refreshCondition == null) {
			throw new FthControllerException(
					"null parameter in ConditionClientGroupByTrainerService -> serve(FthLong).");
		}

		Map<String, List<FthData>> responseJson = new HashMap<>();
		try {
			List<FthData> clientGroup = FthRepository.INSTANCE.query(
					new ClientGroupByConditionSelectSpecifier(refreshCondition));
			responseJson.put(KEY_CLIENT, clientGroup);
			LOGGER.log(Level.INFO,
					"clientGroup obtained.");
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in ConditionClientGroupByTrainerService -> serve(FthData).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish ConditionClientGroupByTrainerService -> serve(FthAuthenticationData).");
		return GSON.toJson(responseJson);
	}
}