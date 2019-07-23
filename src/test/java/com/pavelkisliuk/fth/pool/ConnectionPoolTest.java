package com.pavelkisliuk.fth.pool;

import com.pavelkisliuk.fth.exception.ConnectionPoolException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ConnectionPoolTest {
	private ConnectionPool pool = ConnectionPool.INSTANCE;
	private Connection connection;

	@Test
	void createPool() throws ConnectionPoolException {
		assertTrue(pool.isOpen());
	}

	@Test
	void obtainConnection() throws ConnectionPoolException {
		connection = pool.obtainConnection().get();
		boolean actual = pool.getPoolSize() == (pool.getStartPoolSize() - 1) &&
				pool.getUsedConnectionSize() == 1;
		assertTrue(actual);
	}

	@Test
	void releaseConnection() throws ConnectionPoolException {
		pool.releaseConnection(connection);
		boolean actual = pool.getPoolSize() == pool.getStartPoolSize() &&
				pool.getUsedConnectionSize() == 0;
		pool.destroyPool();
		assertTrue(actual);
	}

	@Test
	void destroyPool() throws ConnectionPoolException {
		pool.destroyPool();
		assertTrue(pool.getPoolSize() == 0 &&
				pool.getUsedConnectionSize() == 0);
	}
}