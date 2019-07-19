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
import java.util.concurrent.locks.ReentrantLock;

//boolean isValid(int timeout) throws SQLException;

/**
 * The {@code ConnectionPool} class is thread-safety enum singleton class-wrapper
 * for {@code List} of {@code Connection}. Class realize system of obtaining connections to database
 * without closing the connection after using.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public enum ConnectionPool {
	/**
	 * Instance of singleton.
	 */
	INSTANCE(16);

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Lock for multithreading realization.
	 */
	private static final ReentrantLock LOCK = new ReentrantLock();

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
		this.poolSize = startPoolSize;
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
	public Optional<Connection> obtainConnection() {
		LOGGER.log(Level.DEBUG, "Start ConnectionPool -> obtainConnection().");
		try {
			LOCK.lock();
			ConnectionProxy connection = connectionPool.poll(); //take first Connection, return null if deck empty
			//можно ли тут сделать connection.isValid(), и если Connection не Valid закрыть и пересоздать
			usedConnectionGroup.add(connection);
			LOGGER.log(Level.DEBUG, "Finish ConnectionPool -> obtainConnection().");
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
	 * @throws ConnectionPoolException if @param connection is {@code null} or closed.
	 */
	public void releaseConnection(Connection connection) throws ConnectionPoolException { //можно ли при null аргументе просто ничего не делать
		LOGGER.log(Level.DEBUG, "Start ConnectionPool -> releaseConnection().");
		if (connection == null ||
				connection.getClass() != ConnectionProxy.class) {
			throw new ConnectionPoolException(
					"Invalid argument in ConnectionPool -> releaseConnection().");
		}

		ConnectionProxy connectionProxy = (ConnectionProxy) connection;
		try {
			LOCK.lock();
			connectionPool.offer(connectionProxy);
			usedConnectionGroup.remove(connectionProxy);
			LOGGER.log(Level.DEBUG, "Connection released.");
		} finally {
			LOCK.unlock();
		}
		LOGGER.log(Level.DEBUG, "Finish ConnectionPool -> releaseConnection().");
	}

	/**
	 * Create pool of {@code Connection} and add it in {@code connectionPool}.
	 * <p>
	 *
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public void createPool() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG, "Start ConnectionPool -> createPool().");
		if (isCreated) {
			LOGGER.log(Level.INFO, "Pool is already created.");
			return;
		}
		connectionPool = new ArrayDeque<>();
		usedConnectionGroup = new HashSet<>();
		try {
			LOGGER.log(Level.INFO, "Start creation.");
			for (int i = 0; i < poolSize; i++) {
				connectionPool.add(new ConnectionProxy(
						DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD)));
			}
			LOGGER.log(Level.INFO, "Finish creation.");
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQL exception in ConnectionPool -> createPool().", e);
		}
		isCreated = true;
		LOGGER.log(Level.DEBUG, "Finish ConnectionPool -> createPool().");
	}

	/**
	 * Destroy pool of {@code Connection} and clear {@code connectionPool} and {@code usedConnectionGroup}.
	 * <p>
	 *
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public void destroyPool() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG, "Start ConnectionPool -> destroyPool().");
		if (!isCreated) {
			LOGGER.log(Level.INFO, "Pool is not created.");
			return;
		}
		try {
			LOCK.lock(); //нужно ли проверять, не лочил ли он что-либо? Если выдаётся соединение, то тут будет ждать, чтобы войти?
			LOGGER.log(Level.INFO, "Start destroying.");
			for (ConnectionProxy connection : connectionPool) {
				connection.closeProxy();
			}
			for (ConnectionProxy connection : usedConnectionGroup) {
				connection.closeProxy();
			}
			if(isClose()) {
				connectionPool.clear();
				usedConnectionGroup.clear();
				isCreated = false;
			} else {
				throw new ConnectionPoolException("Not closed connections in pool after " +
						"ConnectionPool -> destroyPool().");
			}
			LOGGER.log(Level.INFO, "Finish destroying.");
		} finally {
			LOCK.unlock();
		}
		LOGGER.log(Level.DEBUG, "Finish ConnectionPool -> destroyPool().");
	}

	/**
	 * Check if close all connections in {@code ConnectionPool}.
	 * <p>
	 *
	 * @return {@code true} if all connections close, otherwise return {@code false}.
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public boolean isClose() throws ConnectionPoolException {
		LOCK.lock();
		LOGGER.log(Level.DEBUG, "Start ConnectionPool -> isClose().");
		boolean flag = true;
		try {
			int i = 1;
			for (ConnectionProxy connection : connectionPool) {
				if (!connection.isClosed()) {
					LOGGER.log(Level.WARN, "Unclosed connection in connectionPool #" + i + ".");
					flag = false;
				}
				i++;
			}
			i = 0;
			for (ConnectionProxy connection : usedConnectionGroup) {
				if (!connection.isClosed()) {
					LOGGER.log(Level.WARN, "Unclosed connection in usedConnectionGroup #" + i + ".");
					flag = false;
				}
				i++;
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQL exception in ConnectionPool -> isClose().", e);
		} finally {
			LOCK.unlock();
		}
		LOGGER.log(Level.DEBUG, "Finish ConnectionPool -> isClose().");
		return flag;
	}

	/**
	 * Check if open all connections in {@code ConnectionPool}.
	 * <p>
	 *
	 * @return {@code true} if all connections open, otherwise return {@code false}.
	 * @throws ConnectionPoolException if {@code SQLException} occurred.
	 */
	public boolean isOpen() throws ConnectionPoolException {
		LOGGER.log(Level.DEBUG, "Start ConnectionPool -> isOpen().");
		int waitingTime = 30; //time in seconds
		boolean flag = true;
		try {
			LOCK.lock();
			int i = 1;
			for (ConnectionProxy connection : connectionPool) {
				if (!connection.isValid(waitingTime)) {
					LOGGER.log(Level.WARN, "Invalid connection in connectionPool #" + i + ".");
					flag = false;
				}
				i++;
			}
			i = 1;
			for (ConnectionProxy connection : usedConnectionGroup) {
				if (!connection.isValid(waitingTime)) {
					LOGGER.log(Level.WARN, "Invalid connection in connectionPool #" + i + ".");
					flag = false;
				}
				i++;
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQL exception in ConnectionPool -> isOpen().", e);
		} finally {
			LOCK.unlock();
		}
		LOGGER.log(Level.DEBUG, "Finish ConnectionPool -> isOpen().");
		return flag;
	}
}