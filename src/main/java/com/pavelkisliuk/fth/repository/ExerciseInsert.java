/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.repository;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthDrillGroup;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthSetGroup;
import com.pavelkisliuk.fth.model.exercise.ExerciseComponent;
import com.pavelkisliuk.fth.model.exercise.SetLeaf;
import com.pavelkisliuk.fth.pool.ConnectionPool;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;
import com.pavelkisliuk.fth.specifier.insert.DrillGroupFromTrainerInsertSpecifier;
import com.pavelkisliuk.fth.specifier.insert.SetGroupFromTrainerInsertSpecifier;
import com.pavelkisliuk.fth.specifier.select.LastClientExerciseSelectSpecifier;
import com.pavelkisliuk.fth.specifier.update.ClientRequestRemoverUpdateSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The {@code ExerciseInsert} class is {@code FthSpecialTransactionOperator} realization for
 * attachment information about exercise from trainer to client in database.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class ExerciseInsert implements FthSpecialTransactionOperator<ExerciseComponent> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Insert exercise from trainer to database, and change client request condition.
	 * <p>
	 *
	 * @param exerciseComponent is composition of exercise from trainer.
	 * @param connection        is connection to database.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	@Override
	public void insert(ExerciseComponent exerciseComponent, Connection connection) throws FthRepositoryException {
		LOGGER.log(Level.DEBUG,
				"Start ExerciseInsert -> insert(ExerciseComponent, Connection).");
		try {
			connection.setAutoCommit(false);

			SetLeaf.Exercise pureExercise = exerciseComponent.getExercise();
			FthLong clientId = new FthLong(pureExercise.getClientId());
			FthSelectSpecifier selectSpecifier = new LastClientExerciseSelectSpecifier(clientId);
			FthLong exerciseId = (FthLong) FthRepository.INSTANCE.query(selectSpecifier).get(0);

			DrillGroupFromTrainerInsertSpecifier drillInsertSpecifier;
			SetGroupFromTrainerInsertSpecifier setInsertSpecifier;
			ExerciseComponent exercise = exerciseComponent.get(0);
			LOGGER.log(Level.INFO,
					"Start inserting exercise data.");
			for (int i = 0; i < exercise.size(); i++) {
				ExerciseComponent drill = exercise.get(i);
				FthDrillGroup drillGroup = createDrillGroup(drill, exerciseId);
				drillInsertSpecifier = new DrillGroupFromTrainerInsertSpecifier(drillGroup);
				long newDrillId = add(drillInsertSpecifier, connection);
				for (int j = 0; j < drill.size(); j++) {
					ExerciseComponent set = drill.get(j);
					FthSetGroup setGroup = createSetGroup(set, exerciseId, newDrillId);
					setInsertSpecifier = new SetGroupFromTrainerInsertSpecifier(setGroup);
					add(setInsertSpecifier, connection);
				}
			}
			LOGGER.log(Level.INFO,
					"Exercise data inserted.");
			updateClientRequest(clientId, connection);
			LOGGER.log(Level.INFO,
					"Client request condition updated.");

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				// FIXME: 06.08.2019 отправить письмо админу
				throw new FthRepositoryException(
						"SQLException in FthRepository -> operateTransaction(List<TransactionDescriber>).", ex);
			}
			throw new FthRepositoryException(
					"SQLException in FthRepository -> operateTransaction(List<TransactionDescriber>).", e);
		} finally {
			ConnectionPool.INSTANCE.releaseConnection(connection);
		}
	}

	/**
	 * Insert data about drill into database.
	 * <p>
	 *
	 * @param specifier  is {@code FthInsertSpecifier} realization for data insertion.
	 * @param connection is connection to database.
	 * @return id of inserted drill.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	private long add(DrillGroupFromTrainerInsertSpecifier specifier, Connection connection)
			throws FthRepositoryException {
		try (PreparedStatement statement =
					 connection.prepareStatement(specifier.deriveSequelRequest(), Statement.RETURN_GENERATED_KEYS)) {
			specifier.insert(statement);
			return getLastGeneratedKey(statement);
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in FthRepository -> add(DrillGroupFromTrainerInsertSpecifier, Connection).", e);
		}
	}

	/**
	 * Insert data about set into database.
	 * <p>
	 *
	 * @param specifier  is {@code FthInsertSpecifier} realization for data insertion.
	 * @param connection is connection to database.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	private void add(SetGroupFromTrainerInsertSpecifier specifier, Connection connection)
			throws FthRepositoryException {
		try (PreparedStatement statement =
					 connection.prepareStatement(specifier.deriveSequelRequest())) {
			specifier.insert(statement);
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in FthRepository -> add(SetGroupFromTrainerInsertSpecifier, Connection).", e);
		}
	}

	/**
	 * Retrieve last generated id of inserted to database drill.
	 * <p>
	 *
	 * @param statement is statement used for last drill to database insertion.
	 * @return ast generated id.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	private long getLastGeneratedKey(PreparedStatement statement) throws FthRepositoryException {
		try {
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			} else {
				throw new FthRepositoryException(
						"Can't retrieve id of created table" +
								"in FthRepository -> getLastGeneratedKey(PreparedStatement).");
			}
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in FthRepository -> getLastGeneratedKey(PreparedStatement).", e);
		}
	}

	/**
	 * Update request condition of client in database.
	 * <p>
	 *
	 * @param clientId   is id of client.
	 * @param connection s connection to database.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	private void updateClientRequest(FthLong clientId, Connection connection) throws FthRepositoryException {
		FthBoolean falseCondition = new FthBoolean(false);
		FthUpdateSpecifier updateSpecifier = new ClientRequestRemoverUpdateSpecifier(clientId, falseCondition);
		try (PreparedStatement statement =
					 connection.prepareStatement(updateSpecifier.deriveSequelRequest())) {
			updateSpecifier.update(statement);
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in FthRepository -> updateClientRequest(FthLong, Connection).", e);
		}
	}

	/**
	 * Create {@code FthDrillGroup} instance and return it.
	 * <p>
	 *
	 * @param drill      for {@code FthDrillGroup} instance information.
	 * @param exerciseId for {@code FthDrillGroup} instance information.
	 * @return instance of {@code FthDrillGroup}.
	 */
	private FthDrillGroup createDrillGroup(ExerciseComponent drill, FthLong exerciseId) {
		FthDrillGroup drillGroup = new FthDrillGroup();
		SetLeaf.Drill pureDrill = drill.getDrill();
		drillGroup.setExerciseId(exerciseId.get());
		drillGroup.setDrillNumber(pureDrill.getDrillOrder());
		drillGroup.setDrillBaseId(pureDrill.getDrillBaseId());
		drillGroup.setMuscleGroupId(pureDrill.getMuscleGroupId());
		return drillGroup;
	}

	/**
	 * Create {@code FthSetGroup} instance and return it.
	 * <p>
	 *
	 * @param set        for {@code FthSetGroup} instance information.
	 * @param exerciseId for {@code FthSetGroup} instance information.
	 * @param drillId    for {@code FthSetGroup} instance information.
	 * @return instance of {@code FthSetGroup}.
	 */
	private FthSetGroup createSetGroup(ExerciseComponent set, FthLong exerciseId, long drillId) {
		FthSetGroup setGroup = new FthSetGroup();
		SetLeaf leaf = (SetLeaf) set;
		setGroup.setSetNumber(leaf.getSetNumber());
		setGroup.setNecessaryReps(leaf.getNecessaryReps());
		setGroup.setWeightTool(leaf.getWeightTool());
		setGroup.setRestTime(leaf.getRestTime());
		setGroup.setExerciseId(exerciseId.get());
		setGroup.setDrillId(drillId);
		return setGroup;
	}
}