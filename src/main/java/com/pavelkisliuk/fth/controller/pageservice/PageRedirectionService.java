/*  By Pavel Kisliuk, 07.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.controller.pageservice;

import com.pavelkisliuk.fth.controller.FthService;
import com.pavelkisliuk.fth.exception.FthControllerException;
import com.pavelkisliuk.fth.model.FthString;
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

	/**
	 * Retrieve page for redirection as parameter and return it as JSON string.
	 * <p>
	 *
	 * @param redirectionPage is name of redirection page.
	 * @return redirection page as JSON string.
	 * @throws FthControllerException if {@param redirectionPage} null; {@code IllegalArgumentException} occurred.
	 */
	@Override
	public String serve(FthString redirectionPage) throws FthControllerException {
		LOGGER.log(Level.DEBUG,
				"Start PageRedirectionService -> serve(FthString).");
		if (redirectionPage == null) {
			throw new FthControllerException(
					"null parameter in PageRedirectionService -> serve(FthString)");
		}

		Map<String, String> responseJson = new HashMap<>();
		try {
			responseJson.put(KEY_REDIRECT, PageType.valueOf(redirectionPage.get()).get());
			LOGGER.log(Level.INFO,
					"Put page to responseJson.");
		} catch (IllegalArgumentException e) {
			throw new FthControllerException(
					"Incorrect PageType in PageRedirectionService -> serve(FthString)");
		}
		// FIXME: 06.08.2019 убрать
		//return new Gson().toJson(responseJson);
		LOGGER.log(Level.DEBUG,
				"Finish PageRedirectionService -> serve(FthString).");
		return GSON.toJson(responseJson);
	}
}