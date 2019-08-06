/*  By Pavel Kisliuk, 23.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.protection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * The {@code XssProtector} is special class for protection client from XSS attack.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class XssProtector {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Pattern for < symbol or it's UTF-8 representation.
	 */
	private static final Pattern OPEN_GUILLEMET = Pattern.compile("\\\\u003c|<");

	/**
	 * < as html symbol
	 */
	private static final String OPEN_GUILLEMET_HTML_SYMBOL = "&lt;";

	/**
	 * Pattern for > symbol or it's UTF-8 representation.
	 */
	private static final Pattern CLOSE_GUILLEMET = Pattern.compile("\\\\u003e|>");

	/**
	 * > as html symbol
	 */
	private static final String CLOSE_GUILLEMET_HTML_SYMBOL = "&gt;";

	/**
	 * Replace dangerous symbols to comparable html special symbols.
	 * <p>
	 *
	 * @param unprotectData is {@code String} variable which can contain dangerous symbols.
	 * @return new safety {@code String}.
	 */
	public String protect(String unprotectData) {
		LOGGER.log(Level.DEBUG,
				"Start XssProtector -> protect(String).");
		if (unprotectData == null) {
			LOGGER.log(Level.ERROR,
					"null parameter in XssProtector -> protect(String)!!!");
			return "";
		}

		LOGGER.log(Level.INFO,
				"Replace dangerous symbols.");
		String protectData = unprotectData.replaceAll(OPEN_GUILLEMET.pattern(), OPEN_GUILLEMET_HTML_SYMBOL).
				replaceAll(CLOSE_GUILLEMET.pattern(), CLOSE_GUILLEMET_HTML_SYMBOL);
		LOGGER.log(Level.INFO,
				"Dangerous symbols replaced.");
		LOGGER.log(Level.DEBUG,
				"Finish XssProtector -> protect(String).");
		return protectData;
	}
}