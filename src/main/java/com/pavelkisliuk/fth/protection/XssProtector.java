/*  By Pavel Kisliuk, 23.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.protection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	 * Replace dangerous symbols to comparable html special symbols.
	 * <p>
	 *
	 * @param unprotectData is {@code String} variable which can contain dangerous symbols.
	 * @return new safety {@code String}.
	 */
	public String protect(String unprotectData) {
		LOGGER.log(Level.DEBUG,
				"Start XssProtector -> protect(String).");
		if(unprotectData == null) {
			LOGGER.log(Level.ERROR,
					"null parameter in XssProtector -> protect(String)!!!");
			return "";
		}

		LOGGER.log(Level.INFO,
				"Replace dangerous symbols.");
		String protectData = unprotectData.replaceAll("\\\\u003c|<", "&lt;").
				replaceAll("\\\\u003e|>", "&gt;");
		LOGGER.log(Level.INFO,
				"Dangerous symbols replaced.");
		LOGGER.log(Level.DEBUG,
				"Finish XssProtector -> protect(String).");
		return protectData;
	}
}