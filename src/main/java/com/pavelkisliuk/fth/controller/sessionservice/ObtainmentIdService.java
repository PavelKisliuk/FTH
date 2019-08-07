/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.controller.sessionservice;

import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthAuthenticationData;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.select.IdByEmailSpecifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code ObtainmentIdService} class is {@code FthService} realization for
 * obtainment id of user from database.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class ObtainmentIdService implements FthService<FthAuthenticationData> {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Retrieve id of user from database by e-mail and return it as string.
	 * <p>
	 *
	 * @param authenticationData is e-mail processing.
	 * @return id of user.
	 * @throws FthControllerException if {@param authenticationData} null; {@code FthRepositoryException} occurred.
	 */
	@Override
	public String serve(FthAuthenticationData authenticationData) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start ObtainmentIdService -> serve(FthAuthenticationData).");
		if (authenticationData == null) {
			throw new FthControllerException(
					"null parameter in ObtainmentIdService -> serve(FthAuthenticationData)");
		}

		try {
			String id = FthRepository.INSTANCE.query(new IdByEmailSpecifier(
					new FthString(authenticationData.getEmail()))).get(0).toString();
			LOGGER.log(Level.INFO, "ID = " + id + " obtained.");
			LOGGER.log(Level.DEBUG,
					"Finish ObtainmentIdService -> serve(FthAuthenticationData).");
			return id;
		} catch (FthRepositoryException e) {
			throw new FthControllerException(
					"FthRepositoryException in ObtainmentIdService -> serve(FthData).", e);
		}
	}
}