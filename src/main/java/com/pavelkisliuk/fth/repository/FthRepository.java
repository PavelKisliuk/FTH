/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.repository;

import com.pavelkisliuk.fth.exception.ConnectionPoolException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthData;
import com.pavelkisliuk.fth.pool.ConnectionPool;
import com.pavelkisliuk.fth.specifier.FthDeleteSpecifier;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.FthSpecifier;
import com.pavelkisliuk.fth.specifier.FthUpdateSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code FthRepository} class is thread-safety enum singleton class
 * for actions with database.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public enum FthRepository {
	/**
	 * Instance of singleton.
	 */
	INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Timeout for waiting connection from pool in millisecond.
	 */
	private static final int CONNECTION_WAITING_INTERVAL = 5000;

	/**
	 * The {@code TransactionOperationType} class enum describing actions with database in transactions.
	 */
	public enum TransactionOperationType {
		INSERT, UPDATE, DELETE
	}

	/**
	 * The {@code TransactionDescriber} class specify operation and specifier for this operation.
	 * Used for transaction actions.
	 */
	public static class TransactionDescriber {
		private TransactionOperationType operationType;
		private FthSpecifier specifier;

		public TransactionDescriber(TransactionOperationType operationType, FthSpecifier specifier) {
			this.operationType = operationType;
			this.specifier = specifier;
		}
	}

	/**
	 * Insert data into database.
	 * <p>
	 *
	 * @param specifier for obtaining special actions.
	 * @throws FthRepositoryException if {@code SQLException}, {@code FthSpecifierException} occurred.
	 */
	public void add(FthInsertSpecifier specifier) throws FthRepositoryException {
		LOGGER.log(Level.DEBUG,
				"Start FthRepository -> add(FthInsertSpecifier).");
		if (specifier == null) {
			throw new FthRepositoryException(
					"null parameter in FthRepository -> add(FthInsertSpecifier).");
		}

		add(specifier, obtainConnection());
		LOGGER.log(Level.DEBUG,
				"Finish FthRepository -> add(FthInsertSpecifier).");
	}

	private void add(FthInsertSpecifier specifier, Connection connection) throws FthRepositoryException {
		try (PreparedStatement statement = connection.prepareStatement(specifier.deriveSequelRequest())) {
			LOGGER.log(Level.INFO,
					"Insertion data.");
			specifier.insert(statement);
			LOGGER.log(Level.INFO,
					"Insertion achieved.");
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in FthRepository -> add(FthInsertSpecifier, Connection).", e);
		} finally {
			ConnectionPool.INSTANCE.releaseConnection(connection);
			LOGGER.log(Level.INFO,
					"Connection released.");
		}
	}

	/**
	 * Update data into database.
	 * <p>
	 *
	 * @param specifier for obtaining special actions.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	public void replace(FthUpdateSpecifier specifier) throws FthRepositoryException {
		LOGGER.log(Level.DEBUG,
				"Start FthRepository -> replace(FthUpdateSpecifier).");
		if (specifier == null) {
			throw new FthRepositoryException(
					"null parameter in FthRepository -> replace(FthUpdateSpecifier).");
		}

		replace(specifier, obtainConnection());
		LOGGER.log(Level.DEBUG,
				"Finish FthRepository -> replace(FthUpdateSpecifier).");
	}

	private void replace(FthUpdateSpecifier specifier, Connection connection) throws FthRepositoryException {
		try (PreparedStatement statement = connection.prepareStatement(specifier.deriveSequelRequest())) {
			LOGGER.log(Level.INFO,
					"Updating data.");
			specifier.update(statement);
			LOGGER.log(Level.INFO,
					"Updating achieved.");
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in FthRepository -> replace(FthUpdateSpecifier, Connection).", e);
		} finally {
			ConnectionPool.INSTANCE.releaseConnection(connection);
			LOGGER.log(Level.INFO,
					"Connection released.");
		}
	}

	/**
	 * Delete data from database.
	 * <p>
	 *
	 * @param specifier for obtaining special actions.
	 * @throws FthRepositoryException if {@code SQLException}, {@code FthSpecifierException} occurred.
	 */
	public void remove(FthDeleteSpecifier specifier) throws FthRepositoryException {
		LOGGER.log(Level.DEBUG,
				"Start FthRepository -> remove(FthDeleteSpecifier).");
		if (specifier == null) {
			throw new FthRepositoryException(
					"null parameter in FthRepository -> remove(FthDeleteSpecifier).");
		}

		remove(specifier, obtainConnection());
		LOGGER.log(Level.DEBUG,
				"Finish FthRepository -> remove(FthDeleteSpecifier).");
	}

	private void remove(FthDeleteSpecifier specifier, Connection connection) throws FthRepositoryException {
		try (PreparedStatement statement = connection.prepareStatement(specifier.deriveSequelRequest())) {
			LOGGER.log(Level.INFO,
					"Deleting data.");
			specifier.delete(statement);
			LOGGER.log(Level.INFO,
					"Deletion achieved.");
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in FthRepository -> remove(FthDeleteSpecifier, Connection).", e);
		} finally {
			ConnectionPool.INSTANCE.releaseConnection(connection);
			LOGGER.log(Level.INFO,
					"Connection released.");
		}
	}

	public void operateTransaction(List<TransactionDescriber> transactionDescriberList) throws FthRepositoryException {
		if (transactionDescriberList == null) {
			throw new FthRepositoryException(
					"null parameter in FthRepository -> operateTransaction(List<TransactionDescriber>).");
		}

		Connection connection = obtainConnection();
		try {
			connection.setAutoCommit(false);
			for (TransactionDescriber describer : transactionDescriberList) {
				switch (describer.operationType) {
					case INSERT:
						add((FthInsertSpecifier) describer.specifier, connection);
						break;
					case UPDATE:
						replace((FthUpdateSpecifier) describer.specifier, connection);
						break;
					case DELETE:
						remove((FthDeleteSpecifier) describer.specifier, connection);
						break;
					default:
						// FIXME: 06.08.2019 правильно ли?
						throw new EnumConstantNotPresentException(TransactionOperationType.class,
								"Not correct enum element in " +
										"FthRepository -> operateTransaction(List<TransactionDescriber>).");
				}
			}
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
	 * Select data in database.
	 * <p>
	 *
	 * @param specifier for obtaining special actions.
	 * @return {@code List} of {@code FthData}.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	public List<FthData> query(FthSelectSpecifier specifier) throws FthRepositoryException {
		LOGGER.log(Level.DEBUG,
				"Start FthRepository -> query(FthSelectSpecifier).");
		if (specifier == null) {
			throw new FthRepositoryException(
					"null parameter in FthRepository -> query(FthSelectSpecifier).");
		}

		return query(specifier, obtainConnection());
	}

	private List<FthData> query(FthSelectSpecifier specifier, Connection connection) throws FthRepositoryException {
		LinkedList<FthData> dataList = new LinkedList<>();
		try (PreparedStatement statement = connection.prepareStatement(specifier.deriveSequelRequest());
			 ResultSet resultSet = specifier.pasteMeta(statement).executeQuery()) {
			FthDataByResultSetFactory factory = specifier.createFactory();
			LOGGER.log(Level.INFO,
					"Acquiring data.");
			while (resultSet.next()) {
				dataList.add(factory.create(resultSet));
			}
			LOGGER.log(Level.INFO,
					"Data acquired.");
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in FthRepository -> query(FthSelectSpecifier, Connection).", e);
		} finally {
			ConnectionPool.INSTANCE.releaseConnection(connection);
			LOGGER.log(Level.INFO,
					"Connection released.");
		}
		LOGGER.log(Level.DEBUG,
				"Finish FthRepository -> query(FthSelectSpecifier, Connection).");
		return dataList;
	}

	/**
	 * Obtain connection from {@code ConnectionPool}.
	 * <p>
	 *
	 * @return working {@code Connection} from {@code ConnectionPool}.
	 * @throws FthRepositoryException if awaiting interval of {@code Connection} exceeded;
	 *                                ConnectionPoolException occurred.
	 */
	private Connection obtainConnection() throws FthRepositoryException {
		LOGGER.log(Level.DEBUG,
				"Start FthRepository -> obtainConnection().");
		Optional<Connection> connectionOptional;
		Date date = new Date();
		long expiredTime = date.getTime() + CONNECTION_WAITING_INTERVAL;
		LOGGER.log(Level.INFO,
				"Obtaining of connection.");
		boolean firstEntry = true;
		do {
			try {
				connectionOptional = ConnectionPool.INSTANCE.obtainConnection();
			} catch (ConnectionPoolException e) {
				throw new FthRepositoryException(
						"ConnectionPoolException in FthRepository -> obtainConnection().", e);
			}

			if (firstEntry) {
				firstEntry = false;
			} else {
				if (date.getTime() >= expiredTime) {
					throw new FthRepositoryException(
							"Too long awaiting of Connection.");
				}
				try {
					int halfSecondMillis = 500;
					Thread.sleep(halfSecondMillis);
				} catch (InterruptedException e) {
					LOGGER.log(Level.ERROR,
							"Can't cease a thread!!!");
				}
			}
		} while (connectionOptional.isEmpty());
		LOGGER.log(Level.INFO,
				"Connection obtained.");
		LOGGER.log(Level.DEBUG,
				"Finish FthRepository -> obtainConnection().");
		return connectionOptional.get();
	}
}