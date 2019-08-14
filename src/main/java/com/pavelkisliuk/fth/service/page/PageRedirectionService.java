/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.service.page;

import com.pavelkisliuk.fth.command.FthServletCommand;
import com.pavelkisliuk.fth.exception.FthServiceException;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.model.FthString;
import com.pavelkisliuk.fth.service.FthService;
import com.pavelkisliuk.fth.servlet.PageType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code PageRedirectionService} class is {@code FthService} realization for
 * designation page for redirection user.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class PageRedirectionService implements FthService<FthString> {
	private static final Logger LOGGER = LogManager.getLogger();

	private FthLong id;
	private FthString role;

	public PageRedirectionService(FthLong id, FthString role) {
		this.id = id;
		this.role = role;
	}

	/**
	 * Retrieve page for redirection as parameter and return it as JSON string.
	 * <p>
	 *
	 * @param page is name of request page.
	 * @return redirection page as JSON string.
	 * @throws FthServiceException if {@param role} null; {@code IllegalArgumentException} occurred.
	 */
	@Override
	public String serve(FthString page) throws FthServiceException {
		LOGGER.log(Level.DEBUG,
				"Start PageRedirectionService -> serve(FthString).");
		if (page == null) {
			throw new FthServiceException(
					"null parameter in PageRedirectionService -> serve(FthString)");
		}

		Map<String, String> responseJson = new HashMap<>();
		if (id == null) {
			if (page.get().equals(PageType.CLIENT_AUTH.get()) ||
					page.get().equals(PageType.TRAINER_AUTH.get()) ||
					page.get().equals(PageType.TRAINER_REG.get())) {
				responseJson.put(KEY_DATA, "");
			} else {
				responseJson.put(KEY_REDIRECT, PageType.INDEX.get());
			}
		} else {
			switch (role.get()) {
				case FthServletCommand.TRAINER_ROLE:
					if (!page.get().equals(PageType.TRAINER_PAGE.get())) {
						responseJson.put(KEY_REDIRECT, PageType.TRAINER_PAGE.get());
					} else {
						responseJson.put(KEY_DATA, "");
					}
					break;
				case FthServletCommand.CLIENT_ROLE:
					if (!page.get().equals(PageType.CLIENT_PAGE.get())) {
						responseJson.put(KEY_REDIRECT, PageType.CLIENT_PAGE.get());
					} else {
						responseJson.put(KEY_DATA, "");
					}
					break;
				default:
					throw new FthServiceException(
							"Incorrect role in PageRedirectionService -> serve(FthString).");
			}
		}
		LOGGER.log(Level.INFO,
				"Put page to responseJson.");
		LOGGER.log(Level.DEBUG,
				"Finish PageRedirectionService -> serve(FthString).");
		return GSON.toJson(responseJson);
	}
}