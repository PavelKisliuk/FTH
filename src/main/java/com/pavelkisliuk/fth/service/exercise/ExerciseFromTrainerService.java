/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.exercise;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.exercise.ExerciseComponent;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.service.FthService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code ExerciseFromTrainerService} is {@code FthService} realization to inserting
 * exercise from trainer.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ExerciseFromTrainerService implements FthService<ExerciseComponent> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Message to successful insertion.
	 */
	private static final String EXERCISE_FROM_TRAINER_SUCCESS = "Exercise outed.";

	/**
	 * Start up process exercise insertion..
	 * <p>
	 *
	 * @param exerciseComponent is composition of exercise from trainer.
	 * @return successful message.
	 * @throws FthServiceException iif {@param exerciseComponent} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(ExerciseComponent exerciseComponent) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start ExerciseFromTrainerService -> serve(ExerciseComponent).");
		if (exerciseComponent == null) {
			throw new FthServiceException(
					"null parameter in ExerciseFromTrainerService -> serve(ExerciseComponent).");
		}
		Map<String, String> responseJson = new HashMap<>();
		try {
			FthRepository.INSTANCE.exerciseInsert(exerciseComponent);
		} catch (FthRepositoryException e) {
			throw new FthServiceException(
					"FthRepositoryException in ExerciseFromTrainerService -> serve(ExerciseComponent).", e);
		}
		responseJson.put(KEY_MESSAGE, EXERCISE_FROM_TRAINER_SUCCESS);
		return GSON.toJson(responseJson);
	}
}