/*  By Pavel Kisliuk, 13.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.servlet;

/**
 * The {@code FthCommandFactory} interface is factory for creation command to obtaining data from client
 * and deciding how process it.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public interface FthCommandFactory {
	/**
	 * Return instance of {@code FthCommandFactory}.
	 * <p>
	 *
	 * @return instance of {@code FthCommandFactory}.
	 */
	FthCommandFactory create();
}