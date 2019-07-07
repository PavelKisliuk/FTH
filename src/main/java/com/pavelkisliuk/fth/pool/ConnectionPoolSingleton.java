/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.pool;

import com.pavelkisliuk.fth.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@code ConnectionPoolSingleton} class is thread-safety enum singleton class-wrapper
 * for {@code List} of {@code Connection}. Class realize system of obtaining connections to database
 * without closing the connection after using.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public enum ConnectionPoolSingleton {
	/**
	 * Instance of singleton.
	 */
	INSTANCE(10);

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Lock for multithreading realization.
	 */
	private static final ReentrantLock LOCK = new ReentrantLock(); //может ли быть static?

	/**
	 * Path to database.
	 */
	private static final String DATABASE_URL =
			"jdbc:derby:F:\\JavaSE\\Projects\\FTH\\src\\test\\resources\\database\\testdb";

	/**
	 * Database login.
	 */
	private static final String DATABASE_LOGIN = "inProjectWithoutLaba";

	/**
	 * Database password.
	 */
	private static final String DATABASE_PASSWORD = "vProektBezLabyi";

	/**
	 * Default pool size.
	 */
	private int poolSize;

	/**
	 * Flag for point if pool create.
	 */
	private boolean isCreated;

	/**
	 * Pool for connection receiving.
	 */
	private List<Connection> connectionPool;

	/**
	 * {@code List} of connection, which in using.
	 */
	private List<Connection> usedConnectionGroup;

	/**
	 * Default constructor.
	 * @param startPoolSize for default pool size.
	 */
	ConnectionPoolSingleton(int startPoolSize) {
		this.poolSize = startPoolSize;
	}

	/**
	 * Return size of pool.
	 * @return amount of connections in {@code connectionPool}.
	 */
	public int getPoolSize() {
		return connectionPool.size();
	}

	/**
	 * Return size of using connections.
	 * @return amount of connections in {@code usedConnectionGroup}.
	 */
	public int getUsedConnectionSize() {
		return usedConnectionGroup.size();
	}

	/**
	 * Check if close all connections in {@code ConnectionPoolSingleton}.
	 * @return {@code true} if all connections close, otherwise return {@code false}.
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public boolean isClose() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG, "Start ConnectionPoolSingleton -> isClose().");
		boolean flag = true;
		try {
			int i = 0;
			for (Connection connection : connectionPool) {
				if (!connection.isClosed()) {
					LOGGER.log(Level.WARN, "Unclosed connection in connectionPool #" + i + ".");
					flag = false;
				}
			}
			for (Connection connection : usedConnectionGroup) {
				if (!connection.isClosed()) {
					LOGGER.log(Level.WARN, "Unclosed connection in usedConnectionGroup #" + i + ".");
					flag = false;
				}
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQL exception in ConnectionPoolSingleton -> isClose().", e);
		}
		LOGGER.log(Level.DEBUG, "Finish ConnectionPoolSingleton -> isClose().");
		return flag;
	}

	/**
	 * Check if open all connections in {@code ConnectionPoolSingleton}.
	 * @return {@code true} if all connections open, otherwise return {@code false}.
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public boolean isOpen() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG, "Start ConnectionPoolSingleton -> isOpen().");
		boolean flag = true;
		try {
			int i = 0;
			for (Connection connection : connectionPool) {
				if (connection.isClosed()) {
					LOGGER.log(Level.WARN, "Closed connection in connectionPool #" + i + ".");
					flag = false;
				}
			}
			for (Connection connection : usedConnectionGroup) {
				if (connection.isClosed()) {
					LOGGER.log(Level.WARN, "Closed connection in usedConnectionGroup #" + i + ".");
					flag = false;
				}
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQL exception in ConnectionPoolSingleton -> isOpen().", e);
		}
		LOGGER.log(Level.DEBUG, "Finish ConnectionPoolSingleton -> isOpen().");
		return flag;
	}

	/**
	 * Return {@code Connection} from connectionPool.
	 * @return {@code Optional} of {@code Connection}. If connection is available in {@code connectionPool}
	 * return this connection, otherwise return {@code null}.
	 */
	public Optional<Connection> obtainConnection() {
		LOGGER.log(Level.DEBUG, "Start ConnectionPoolSingleton -> obtainConnection().");
		try {
			LOCK.lock();
			Connection connection = null;
			if (!connectionPool.isEmpty()) {
				connection = connectionPool.remove(connectionPool.size() - 1);
				usedConnectionGroup.add(connection);
				LOGGER.log(Level.DEBUG, "Connection acquired. Return acquired connection.");
			} else {
				LOGGER.log(Level.INFO, "Connection not acquired. Return null.");
			}
			LOGGER.log(Level.DEBUG, "Finish ConnectionPoolSingleton -> obtainConnection().");
			return Optional.ofNullable(connection);
		} finally {
			LOCK.unlock();
		}
	}

	/**
	 * Give back connection to {@code connectionPool}.
	 * @param connection is returning connection.
	 * @throws ConnectionPoolException if @param connection is {@code null} or closed.
	 */
	public void releaseConnection(Connection connection) throws ConnectionPoolException { //можно ли при null аргументе просто ничего не делать
		LOGGER.log(Level.DEBUG, "Start ConnectionPoolSingleton -> releaseConnection().");
		try {
			if (connection == null ||
					connection.isClosed()) {
				throw new ConnectionPoolException(
						"Invalid argument in ConnectionPoolSingleton -> releaseConnection().");
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQL exception in ConnectionPoolSingleton -> releaseConnection().", e);
		}
		if (usedConnectionGroup.contains(connection)) { //надо ли здесь лочить?
			connectionPool.add(connection);
			usedConnectionGroup.remove(connection);
			LOGGER.log(Level.DEBUG, "Connection released.");
		} else {
			LOGGER.log(Level.WARN, "Acquired not this pool connection.");
		}
		LOGGER.log(Level.DEBUG, "Finish ConnectionPoolSingleton -> releaseConnection().");
	}

	/**
	 * Create pool of {@code Connection} and add it in {@code connectionPool}.
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public void createPool() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG, "Start ConnectionPoolSingleton -> createPool().");
		if (isCreated) {
			LOGGER.log(Level.INFO, "Pool is already created.");
			return;
		}
		connectionPool = new ArrayList<>();
		usedConnectionGroup = new ArrayList<>();
		try {
			LOGGER.log(Level.DEBUG, "Start creation.");
			for (int i = 0; i < poolSize; i++) {
				connectionPool.add(DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD));
			}
			LOGGER.log(Level.DEBUG, "Finish creation.");
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQL exception in ConnectionPoolSingleton -> createPool().", e);
		}
		isCreated = true;
		LOGGER.log(Level.DEBUG, "Finish ConnectionPoolSingleton -> createPool().");
	}

	/**
	 * Destroy pool of {@code Connection} and clear {@code connectionPool} and {@code usedConnectionGroup}.
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public void destroyPool() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG, "Start ConnectionPoolSingleton -> destroyPool().");
		if (!isCreated) {
			LOGGER.log(Level.INFO, "Pool is not created.");
			return;
		}
		try {
			LOCK.lock(); //нужно ли проверять, не лочил ли он что-либо? Если выдаётся соединение, то тут будет ждать, чтобы войти?
			LOGGER.log(Level.DEBUG, "Start destroying.");
			for (Connection connection : connectionPool) {
				connection.close();
			}
			for (Connection connection : usedConnectionGroup) {
				connection.close();
			}
			LOGGER.log(Level.DEBUG, "Finish destroying.");
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQL exception in ConnectionPoolSingleton -> destroyPool().", e);
		} finally {
			LOCK.unlock();
		}
		connectionPool.clear();
		usedConnectionGroup.clear();
		isCreated = false;
		LOGGER.log(Level.DEBUG, "Finish ConnectionPoolSingleton -> destroyPool().");
	}
}