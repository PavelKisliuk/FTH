/*  By Pavel Kisliuk, 06.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.pool;

import java.util.Properties;

/**
 * The {@code PoolPropertyInitializer} class is storage of data for database initialization.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
class PoolPropertyInitializer {
	/**
	 * Path to database.
	 */
	private static final String DATABASE_URL = "jdbc:derby://localhost:1527/testdb";
//		DATABASE_URL =
//				"jdbc:derby:F:\\JavaSE\\Projects\\FTH\\src\\test\\resources\\database\\testdb";;

	/**
	 * Database login.
	 */
	private static final String DATABASE_LOGIN = "inProjectWithoutLaba";

	/**
	 * User key for properties.
	 */
	private static final String USER = "user";

	/**
	 * Database password.
	 */
	private static final String DATABASE_PASSWORD = "vProektBezLabyi";

	/**
	 * Password key for properties.
	 */
	private static final String PASSWORD = "password";

	/**
	 * Properties for database.
	 */
	private static final Properties DATA_BASE_PROPERTIES = new Properties();

	static {
		DATA_BASE_PROPERTIES.put(USER, DATABASE_LOGIN);
		DATA_BASE_PROPERTIES.put(PASSWORD, DATABASE_PASSWORD);
	}

	/**
	 * Database URL getter.
	 *
	 * @return URL of Database.
	 */
	static String getDatabaseUrl() {
		return DATABASE_URL;
	}

	/**
	 * Database properties getter.
	 *
	 * @return properties of Database.
	 */
	static Properties getDataBaseProperties() {
		return DATA_BASE_PROPERTIES;
	}
}