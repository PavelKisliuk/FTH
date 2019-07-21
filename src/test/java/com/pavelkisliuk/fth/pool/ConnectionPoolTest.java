package com.pavelkisliuk.fth.pool;

import com.pavelkisliuk.fth.exception.ConnectionPoolException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {
	private ConnectionPool pool = ConnectionPool.INSTANCE;

	@BeforeEach
	void setUp() throws ConnectionPoolException {
		pool.createPool();
	}

	@AfterEach
	void tearDown() throws ConnectionPoolException {
		pool.destroyPool();
	}

	@Test
	void obtainConnection() {
		pool.obtainConnection();
		boolean actual = pool.getPoolSize() == (pool.getStartPoolSize() - 1) &&
				pool.getUsedConnectionSize() == 1;
		assertTrue(actual);
	}

	@Test
	void releaseConnection() throws ConnectionPoolException {
		Connection connection = pool.obtainConnection().orElse(null);
		pool.releaseConnection(connection);
		boolean actual = pool.getPoolSize() == pool.getStartPoolSize() &&
				pool.getUsedConnectionSize() == 0;
		assertTrue(actual);
	}

	@Test
	void createPool() throws ConnectionPoolException {
		assertTrue(pool.isOpen());
	}

	@Test
	void destroyPool() throws ConnectionPoolException {
		pool.obtainConnection();
		pool.obtainConnection();
		pool.obtainConnection();
		pool.releaseConnection(pool.obtainConnection().get());
		pool.destroyPool();
		assertTrue(pool.getPoolSize() == 0 &&
				pool.getUsedConnectionSize() == 0);
	}
}