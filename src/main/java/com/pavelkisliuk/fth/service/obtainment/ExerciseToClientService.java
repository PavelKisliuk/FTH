/*  By Pavel Kisliuk, 08.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.obtainment;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.model.FthDrillToClient;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthSetToClient;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.DrillNameByIdSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.DrillToClientSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.LastClientExerciseSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.SetToClientSelectSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code ExerciseToClientService} class is {@code FthService} realization for
 * obtainment client exercise.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ExerciseToClientService implements FthService<FthLong> {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String KEY_DRILL_NAME = "drillName";
	private static final String KEY_SET_INFO = "setInfo";
	private static final String KEY_ID = "ID";

	private static final String KEY_ANSWER1 = "answer1";
	private static final String KEY_ANSWER2 = "answer2";
	private static final String KEY_ANSWER3 = "answer3";

	/**
	 * Retrieve exercise for client and return it as JSON string.
	 * <p>
	 *
	 * @param clientId is id of client.
	 * @return exercise for client as JSON string.
	 * @throws FthServiceException if {@param clientId} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthLong clientId) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start ExerciseToClientService -> serve(FthLong).");
		if (clientId == null) {
			throw new FthServiceException(
					"null parameter in ExerciseToClientService -> serve(FthLong).");
		}

		FthSelectSpecifier selectSpecifier = new LastClientExerciseSelectSpecifier(clientId);
		ArrayList<String> drillNameList = new ArrayList<>();
		ArrayList<FthSetToClient> setToClientList = new ArrayList<>();

		ArrayList<ArrayList<Long>> allId = new ArrayList<>();

		try {
			FthLong exerciseId = (FthLong) FthRepository.INSTANCE.query(selectSpecifier).get(0);
			selectSpecifier = new DrillToClientSelectSpecifier(exerciseId);
			List<FthData> drillToClientList = FthRepository.INSTANCE.query(selectSpecifier);


			for (FthData element : drillToClientList) {
				FthDrillToClient drill = (FthDrillToClient) element;
				FthLong drillBaseId = new FthLong(drill.getDrillBaseId());
				selectSpecifier = new DrillNameByIdSelectSpecifier(drillBaseId);
				FthString drillName = (FthString) FthRepository.INSTANCE.query(selectSpecifier).get(0);
				drillNameList.add(drillName.get());

				FthLong drillId = new FthLong(drill.getDrillId());
				selectSpecifier = new SetToClientSelectSpecifier(exerciseId, drillId);
				List<FthData> setList = FthRepository.INSTANCE.query(selectSpecifier);
				FthSetToClient setToClient = createSetData(setList, allId);
				setToClientList.add(setToClient);
			}


		} catch (FthRepositoryException e) {
			e.printStackTrace();
		}
		HashMap<String, ArrayList<String>> answer1 = new HashMap<>();
		answer1.put(KEY_DRILL_NAME, drillNameList);
		HashMap<String, ArrayList<FthSetToClient>> answer2 = new HashMap<>();
		answer2.put(KEY_SET_INFO, setToClientList);
		HashMap<String, ArrayList<ArrayList<Long>>> answer3 = new HashMap<>();
		answer3.put(KEY_ID, allId);
		String json1 = GSON.toJson(answer1);
		String json2 = GSON.toJson(answer2);
		String json3 = GSON.toJson(answer3);

		Map<String, String> responseJson = new HashMap<>();
		responseJson.put(KEY_ANSWER1, json1);
		responseJson.put(KEY_ANSWER2, json2);
		responseJson.put(KEY_ANSWER3, json3);

		return GSON.toJson(responseJson);
	}

	private FthSetToClient createSetData(List<FthData> setList, ArrayList<ArrayList<Long>> allId) {
		ArrayList<Long> setIdList = new ArrayList<>();
		for (FthData element : setList) {
			FthSetToClient set = (FthSetToClient) element;
			setIdList.add(set.getSetId());
		}
		allId.add(setIdList);
		return (FthSetToClient) setList.get(0);
	}
}