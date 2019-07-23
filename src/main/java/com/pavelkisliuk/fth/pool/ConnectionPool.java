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
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@code ConnectionPool} class is thread-safety enum singleton class-wrapper
 * for {@code List} of {@code Connection}. Class realize system of obtaining connections to database
 * without closing the connection after using.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see ConnectionProxy
 * @since 12.0
 */
public enum ConnectionPool {
	/**
	 * Instance of singleton.
	 */
	INSTANCE(16);

	private final Logger LOGGER;

	/**
	 * Lock for multithreading realization.
	 */
	private final ReentrantLock LOCK;

	/**
	 * Path to database.
	 */
	private final String DATABASE_URL;

	/**
	 * Database login.
	 */
	private final String DATABASE_LOGIN;

	/**
	 * Database password.
	 */
	private final String DATABASE_PASSWORD;

	/**
	 * Time out for {@code Connection.isValid(int timeOut)}. Duration in seconds.
	 */
	private final int TIME_OUT;

	/**
	 * Default pool size.
	 */
	private int poolSize;

	/**
	 * Flag for point if pool create.
	 */
	private AtomicBoolean isCreated;

	/**
	 * Pool for connection receiving.
	 */
	private ArrayDeque<ConnectionProxy> connectionPool;

	/**
	 * {@code List} of connection, which in using.
	 */
	private HashSet<ConnectionProxy> usedConnectionGroup;

	/**
	 * Default constructor.
	 * <p>
	 *
	 * @param startPoolSize for default pool size.
	 */
	ConnectionPool(int startPoolSize) {
		LOGGER = LogManager.getLogger();
		LOCK = new ReentrantLock();
		DATABASE_URL = "jdbc:derby://localhost:1527/testdb";
//		DATABASE_URL =
//				"jdbc:derby:F:\\JavaSE\\Projects\\FTH\\src\\test\\resources\\database\\testdb";
		DATABASE_LOGIN = "inProjectWithoutLaba";
		DATABASE_PASSWORD = "vProektBezLabyi";
		TIME_OUT = 3;

		this.poolSize = startPoolSize;
		isCreated = new AtomicBoolean();
		try {
			createPool();
		} catch (ConnectionPoolException e) {
			LogManager.getLogger().log(Level.FATAL,
					"ConnectionPoolException in ConnectionPool constructor!!!");
			throw new ExceptionInInitializerError(
					"ConnectionPoolException in ConnectionPool constructor.");
		}
	}

	/**
	 * Return initial size of pool.
	 * <p>
	 *
	 * @return initial size of pool.
	 */
	public int getStartPoolSize() {
		return poolSize;
	}

	/**
	 * Return size of pool.
	 * <p>
	 *
	 * @return amount of connections in {@code connectionPool}.
	 */
	public int getPoolSize() {
		return connectionPool.size();
	}

	/**
	 * Return size of using connections.
	 * <p>
	 *
	 * @return amount of connections in {@code usedConnectionGroup}.
	 */
	public int getUsedConnectionSize() {
		return usedConnectionGroup.size();
	}

	/**
	 * Return {@code Connection} from {@code connectionPool}.
	 * <p>
	 *
	 * @return {@code Optional} of {@code Connection}. If connection is available in {@code connectionPool}
	 * return this connection, otherwise return {@code null}.
	 */
	public Optional<Connection> obtainConnection() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG,
				"Start ConnectionPool -> obtainConnection().");
		try {
			LOCK.lock();
			ConnectionProxy connection = connectionPool.poll(); //take first Connection and remove it, return null if deck empty
			if (connection != null) {
				connection = validate(connection);
				if(!usedConnectionGroup.add(connection)) {
					LOGGER.log(Level.WARN,
							"Connection already became in usedConnectionGroup!");
				}
				LOGGER.log(Level.INFO,
						"Connection obtained.");
			}
			LOGGER.log(Level.DEBUG,
					"Finish ConnectionPool -> obtainConnection().");
			return Optional.ofNullable(connection);
		} finally {
			LOCK.unlock();
		}
	}

	/**
	 * Give back connection to {@code connectionPool}.
	 * <p>
	 *
	 * @param connection is returning connection.
	 */
	public void releaseConnection(Connection connection) {
		LOGGER.log(Level.DEBUG,
				"Start ConnectionPool -> releaseConnection(Connection).");
		if (connection == null ||
				connection.getClass() != ConnectionProxy.class) {
			LOGGER.log(Level.WARN,
					"Incorrect connection retrieved!");
		}

		ConnectionProxy connectionProxy = (ConnectionProxy) connection;
		try {
			LOCK.lock();
			connectionPool.offer(connectionProxy);
			usedConnectionGroup.remove(connectionProxy);
			LOGGER.log(Level.DEBUG,
					"Connection released.");
		} finally {
			LOCK.unlock();
		}
		LOGGER.log(Level.DEBUG,
				"Finish ConnectionPool -> releaseConnection(Connection).");
	}

	/**
	 * Create pool of {@code Connection} and add it in {@code connectionPool}.
	 * <p>
	 *
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	private void createPool() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG,
				"Start ConnectionPool -> createPool().");
		if (isCreated.get()) {
			LOGGER.log(Level.INFO,
					"Pool is already created.");
			return;
		}

		connectionPool = new ArrayDeque<>();
		usedConnectionGroup = new HashSet<>();
		try {
			LOGGER.log(Level.INFO,
					"Start creation.");
			DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
			for (int i = 0; i < poolSize; i++) {
				connectionPool.add(new ConnectionProxy(
						DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD)));
			}
			LOGGER.log(Level.INFO,
					"Finish creation.");
		} catch (SQLException e) {
			throw new ConnectionPoolException(
					"SQL exception in ConnectionPool -> createPool().", e);
		}
		isCreated.set(true);
		LOGGER.log(Level.DEBUG,
				"Finish ConnectionPool -> createPool().");
	}

	/**
	 * Destroy pool of {@code Connection} and clear {@code connectionPool} and {@code usedConnectionGroup}.
	 * <p>
	 *
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public void destroyPool() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG,
				"Start ConnectionPool -> destroyPool().");
		if (!isCreated.get()) {
			LOGGER.log(Level.INFO,
					"Pool is not created.");
			return;
		}

		try {
			LOGGER.log(Level.INFO,
					"Start destroying.");
			LOCK.lock();
			for (ConnectionProxy connection : connectionPool) {
				connection.closeProxy();
			}
			for (ConnectionProxy connection : usedConnectionGroup) {
				connection.closeProxy();
			}
			if (isClose()) {
				connectionPool.clear();
				usedConnectionGroup.clear();
				isCreated.set(false);
				LOGGER.log(Level.INFO,
						"Finish destroying.");
			} else {
				throw new ConnectionPoolException(
						"Not closed connections in pool after ConnectionPool -> destroyPool().");
			}
		} finally {
			LOCK.unlock();
		}
		LOGGER.log(Level.DEBUG,
				"Finish ConnectionPool -> destroyPool().");
	}

	/**
	 * Check if closed all connections in {@code ConnectionPool}.
	 * <p>
	 *
	 * @return {@code true} if all connections close, otherwise return {@code false}.
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public boolean isClose() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG,
				"Start ConnectionPool -> isClose().");
		boolean flag = true;
		try {
			LOCK.lock();
			int i = 0;
			for (ConnectionProxy connection : connectionPool) {
				if (!connection.isClosed()) {
					LOGGER.log(Level.WARN,
							"Unclosed connection in connectionPool #" + i + "(count from 0)!");
					flag = false;
				}
				i++;
			}
			i = 0;
			for (ConnectionProxy connection : usedConnectionGroup) {
				if (!connection.isClosed()) {
					LOGGER.log(Level.WARN,
							"Unclosed connection in usedConnectionGroup #" + i + "(count from 0)!");
					flag = false;
				}
				i++;
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException(
					"SQL exception in ConnectionPool -> isClose().", e);
		} finally {
			LOCK.unlock();
		}
		LOGGER.log(Level.DEBUG,
				"Finish ConnectionPool -> isClose().");
		return flag;
	}

	/**
	 * Check if opened all connections in {@code ConnectionPool}.
	 * <p>
	 *
	 * @return {@code true} if all connections open, otherwise return {@code false}.
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public boolean isOpen() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG,
				"Start ConnectionPool -> isOpen().");
		boolean flag = true;
		try {
			LOCK.lock();
			int i = 0;
			for (ConnectionProxy connection : connectionPool) {
				if (!connection.isValid(TIME_OUT)) {
					LOGGER.log(Level.WARN,
							"Invalid connection in connectionPool #" + i + "(count from 0).");
					flag = false;
				}
				i++;
			}
			i = 0;
			for (ConnectionProxy connection : usedConnectionGroup) {
				if (!connection.isValid(TIME_OUT)) {
					LOGGER.log(Level.WARN,
							"Invalid connection in connectionPool #" + i + "(count from 0).");
					flag = false;
				}
				i++;
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException(
					"SQL exception in ConnectionPool -> isOpen().", e);
		} finally {
			LOCK.unlock();
		}
		LOGGER.log(Level.DEBUG,
				"Finish ConnectionPool -> isOpen().");
		return flag;
	}

	/**
	 * Check {@code ConnectionProxy} for validity. Return {@param connectionProxy} if connection valid,
	 * otherwise return new {@code ConnectionProxy}.
	 * <p>
	 *
	 * @param connectionProxy for validation.
	 * @return valid {@code ConnectionProxy}.
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	private ConnectionProxy validate(ConnectionProxy connectionProxy) throws ConnectionPoolException {
		try {
			return connectionProxy.isValid(TIME_OUT) ? connectionProxy : new ConnectionProxy(
					DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD));
		} catch (SQLException e) {
			throw new ConnectionPoolException(
					"SQL exception in ConnectionPool -> validate(ConnectionProxy).", e);
		}
	}
}