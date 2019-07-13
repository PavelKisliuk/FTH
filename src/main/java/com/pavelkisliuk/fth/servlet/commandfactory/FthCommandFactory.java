/*  By Pavel Kisliuk, 13.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.servlet.commandfactory;

/**
 * The {@code FthCommandFactory} interface is factory for creation command to obtaining data from client
 * and deciding how process it.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
interface FthCommandFactory {
	/**
	 * Return instance of {@code FthServletCommand}.
	 * <p>
	 *
	 * @return instance of {@code FthServletCommand}.
	 */
	FthServletCommand create();
}