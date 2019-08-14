package com.pavelkisliuk.fth.repository;

import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.model.FthUserMainData;
import com.pavelkisliuk.fth.pool.ConnectionPool;
import com.pavelkisliuk.fth.specifier.FthDeleteSpecifier;
import com.pavelkisliuk.fth.specifier.FthInsertSpecifier;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.delete.RemoveConfirmedUser;
import com.pavelkisliuk.fth.specifier.insert.AuthenticateDataInsertSpecifier;
import com.pavelkisliuk.fth.specifier.insert.InitializeClientInsertSpecifier;
import com.pavelkisliuk.fth.specifier.insert.NewUserInsertSpecifier;
import com.pavelkisliuk.fth.specifier.select.RegistrationDataSelectSpecifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

class NewUserInsert implements FthSpecialTransactionOperator<FthString> {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String CLIENT_TABLE_NAME = "ClientPersonalData";

	private FthString tableName;

	NewUserInsert(FthString tableName) {
		this.tableName = tableName;
	}

	@Override
	public void insert(FthString registrationKey, Connection connection) throws FthRepositoryException {
		try {
			connection.setAutoCommit(false);
			FthSelectSpecifier selectSpecifier = new RegistrationDataSelectSpecifier(registrationKey);
			FthUserMainData userMainData = (FthUserMainData) FthRepository.INSTANCE.query(selectSpecifier).get(0);
			NewUserInsertSpecifier insertSpecifier = new NewUserInsertSpecifier(tableName, userMainData);
			long id = add(insertSpecifier, connection);
			FthLong returnedId = new FthLong(id);
			boolean flag = true;
			if (CLIENT_TABLE_NAME.equals(tableName.get())) {
				InitializeClientInsertSpecifier initializeClientInsertSpecifier =
						new InitializeClientInsertSpecifier(returnedId);
				add(initializeClientInsertSpecifier, connection);
				flag = false;
			}
			AuthenticateDataInsertSpecifier authenticateDataInsertSpecifier =
					new AuthenticateDataInsertSpecifier(registrationKey, returnedId, new FthBoolean(flag));
			add(authenticateDataInsertSpecifier, connection);
			FthDeleteSpecifier deleteSpecifier = new RemoveConfirmedUser(registrationKey);
			remove(deleteSpecifier, connection);

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				// FIXME: 06.08.2019 отправить письмо админу
				throw new FthRepositoryException(
						"SQLException in NewUserInsert -> insert(List<TransactionDescriber>).", ex);
			}
			throw new FthRepositoryException(
					"SQLException in NewUserInsert -> insert(List<TransactionDescriber>).", e);
		} finally {
			ConnectionPool.INSTANCE.releaseConnection(connection);
		}
	}

	/**
	 * Insert registration data into database.
	 * <p>
	 *
	 * @param specifier  is {@code FthInsertSpecifier} realization for data insertion.
	 * @param connection is connection to database.
	 * @return id of inserted user.
	 * @throws FthRepositoryException if {@code SQLException} occurred.
	 */
	private long add(NewUserInsertSpecifier specifier, Connection connection)
			throws FthRepositoryException {
		try (PreparedStatement statement =
					 connection.prepareStatement(specifier.deriveSequelRequest(), Statement.RETURN_GENERATED_KEYS)) {
			specifier.insert(statement);
			return getLastGeneratedKey(statement);
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in NewUserInsert -> add(DrillGroupFromTrainerInsertSpecifier, Connection).", e);
		}
	}

	private void add(FthInsertSpecifier specifier, Connection connection)
			throws FthRepositoryException {
		try (PreparedStatement statement =
					 connection.prepareStatement(specifier.deriveSequelRequest())) {
			specifier.insert(statement);
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in NewUserInsert -> add(SetGroupFromTrainerInsertSpecifier, Connection).", e);
		}
	}

	private void remove(FthDeleteSpecifier specifier, Connection connection) throws FthRepositoryException {
		try (PreparedStatement statement =
					 connection.prepareStatement(specifier.deriveSequelRequest())) {
			specifier.delete(statement);
		} catch (SQLException e) {
			throw new FthRepositoryException(
					"SQLException in NewUserInsert -> remove(FthDeleteSpecifier, Connection).", e);
		}
	}
}
