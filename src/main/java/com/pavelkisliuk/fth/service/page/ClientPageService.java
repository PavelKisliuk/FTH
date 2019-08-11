/*  By Pavel Kisliuk, 12.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.page;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.ClientByIdSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.ClientRequestedSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.IsExerciseOutedSelectSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code ClientPageService} class is {@code FthService} realization for
 * data for client page obtainment.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ClientPageService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Retrieve client main information and create condition's for button's on page from database
	 * and return it as JSON string.
	 * <p>
	 *
	 * @param clientId is id of client.
	 * @return client main information and condition's for button's.
	 * @throws FthServiceException if {@param clientId} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong clientId) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start ClientPageService -> serve(FthLong).");
		if (clientId == null) {
			throw new FthServiceException(
					"null parameter in ClientPageService -> serve(FthLong).");
		}

		Map<String, List<? extends FthData>> responseJson = new HashMap<>();
		try {
			FthSelectSpecifier selectSpecifier = new ClientByIdSelectSpecifier(clientId);
			List<FthData> clientData = FthRepository.INSTANCE.query(selectSpecifier);
			responseJson.put(KEY_CLIENT, clientData);
			LOGGER.log(Level.INFO,
					"clientData obtained.");

			ArrayList<FthBoolean> hiddenElementList = new ArrayList<>();
			setButtonCondition(hiddenElementList, clientId);
			responseJson.put(KEY_BUTTON_CONDITION, hiddenElementList);
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in ClientPageService -> serve(FthData).", e);
		}
		LOGGER.log(Level.DEBUG,
				"Finish ClientPageService -> serve(FthAuthenticationData).");
		return GSON.toJson(responseJson);
	}

	/**
	 * Set condition's for specify button's.
	 * <p>
	 *
	 * @param hiddenElementList is list for condition's insertion.
	 * @param clientId          is id of client.
	 * @throws FthServiceException {@code FthRepositoryException} occurred.
	 */
	private void setButtonCondition(ArrayList<FthBoolean> hiddenElementList, FthLong clientId)
			throws FthServiceException {
		FthSelectSpecifier selectSpecifier = new ClientRequestedSelectSpecifier(clientId);
		try {
			FthBoolean isRequested = (FthBoolean) FthRepository.INSTANCE.query(selectSpecifier).get(0);
			if (isRequested.get()) {
				LOGGER.log(Level.INFO,
						"Request = true. Set hidden conditions.");
				hiddenElementList.add(new FthBoolean(false));
				hiddenElementList.add(new FthBoolean(false));
				hiddenElementList.add(new FthBoolean(false));
				hiddenElementList.add(new FthBoolean(true));
			} else {
				LOGGER.log(Level.INFO,
						"Request = false.");
				selectSpecifier = new IsExerciseOutedSelectSpecifier(clientId);
				FthBoolean isExerciseOuted = (FthBoolean) FthRepository.INSTANCE.query(selectSpecifier).get(0);
				if (isExerciseOuted.get()) {
					LOGGER.log(Level.INFO,
							"Exercise outed. Set hidden conditions.");
					hiddenElementList.add(new FthBoolean(true));
					hiddenElementList.add(new FthBoolean(false));
					hiddenElementList.add(new FthBoolean(false));
					hiddenElementList.add(new FthBoolean(true));
				} else {
					LOGGER.log(Level.INFO,
							"Exercise NOT outed. Set hidden conditions.");
					hiddenElementList.add(new FthBoolean(false));
					hiddenElementList.add(new FthBoolean(false));
					hiddenElementList.add(new FthBoolean(true));
					hiddenElementList.add(new FthBoolean(false));
				}
			}
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in setButtonCondition -> serve(ArrayList<FthBoolean>, FthLong).", e);
		}
	}
}