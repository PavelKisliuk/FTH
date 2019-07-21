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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	 * Insert data into database.
	 * <p>
	 *
	 * @param specifier for obtaining special actions.
	 * @throws FthRepositoryException if {@code SQLException}, {@code FthSpecifierException} occurred.
	 */
	public void add(FthInsertSpecifier specifier) throws FthRepositoryException {
		LOGGER.log(Level.DEBUG, "Start FthRepository -> add().");
		add(specifier, obtainConnection());
		LOGGER.log(Level.DEBUG, "Finish FthRepository -> add().");
	}

	private void add(FthInsertSpecifier specifier, Connection connection) throws FthRepositoryException {
		try (PreparedStatement statement = connection.prepareStatement(specifier.deriveSequelRequest())) {
			specifier.insert(statement);
			LOGGER.log(Level.DEBUG, "Insertion achieved.");
		} catch (SQLException e) {
			throw new FthRepositoryException("Problem in add() method!", e);
		} finally {
			try {
				ConnectionPool.INSTANCE.releaseConnection(connection);
			} catch (ConnectionPoolException e) {
				LOGGER.log(Level.ERROR, "Damaged connection tried to realise!", e);
			}
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
		LOGGER.log(Level.DEBUG, "Start FthRepository -> remove().");
		Optional<Connection> connectionOptional;
		do {
			connectionOptional = ConnectionPool.INSTANCE.obtainConnection();
		} while (connectionOptional.isEmpty());
		Connection connection = connectionOptional.get();
		try (PreparedStatement statement = connection.prepareStatement(specifier.deriveSequelRequest())) {
			specifier.delete(statement);
			LOGGER.log(Level.DEBUG, "Deletion achieved.");
		} catch (SQLException e) {
			throw new FthRepositoryException("Problem in remove() method!", e);
		} finally {
			try {
				ConnectionPool.INSTANCE.releaseConnection(connection);
			} catch (ConnectionPoolException e) {
				LOGGER.log(Level.ERROR, "Damaged connection tried to realise!", e);
			}
		}
		LOGGER.log(Level.DEBUG, "Finish FthRepository -> remove().");
	}

	/**
	 * Select data in database.
	 * <p>
	 *
	 * @param specifier for obtaining special actions.
	 * @return {@code List} of {@code FthData}.
	 * @throws FthRepositoryException if {@code SQLException}, {@code FthSpecifierException} occurred.
	 */
	public List<FthData> query(FthSelectSpecifier specifier) throws FthRepositoryException {
		LOGGER.log(Level.DEBUG, "Start FthRepository -> query().");
		ArrayList<FthData> dataList = new ArrayList<>();
		Optional<Connection> connectionOptional;
		do {
			connectionOptional = ConnectionPool.INSTANCE.obtainConnection();
		} while (connectionOptional.isEmpty());
		Connection connection = connectionOptional.get();
		try (PreparedStatement statement = connection.prepareStatement(specifier.deriveSequelRequest());
			 ResultSet resultSet = specifier.pasteMeta(statement).executeQuery()) {
			FthDataByResultSetFactory factory = specifier.createFactory();
			while (resultSet.next()) {
				dataList.add(factory.create(resultSet));
			}
			LOGGER.log(Level.DEBUG, "Data acquired.");
		} catch (SQLException e) {
			throw new FthRepositoryException("Problem in query() method!", e);
		} finally {
			try {
				ConnectionPool.INSTANCE.releaseConnection(connection);
			} catch (ConnectionPoolException e) {
				LOGGER.log(Level.ERROR, "Damaged connection tried to realise!", e);
			}
		}
		LOGGER.log(Level.DEBUG, "Finish FthRepository -> query().");
		return dataList;
	}

	private Connection obtainConnection() {
		Optional<Connection> connectionOptional;
		do {
			connectionOptional = ConnectionPool.INSTANCE.obtainConnection();
		} while (connectionOptional.isEmpty());
		return connectionOptional.get();
	}
}