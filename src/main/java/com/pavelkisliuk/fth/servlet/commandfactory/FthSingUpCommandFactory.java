/*  By Pavel Kisliuk, 13.07.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.servlet.commandfactory;

/**
 * The {@code FthSingUpCommandFactory} class is realization of {@code FthCommandFactory} for
 * {@code FthSingUpCommand} creation.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class FthSingUpCommandFactory implements FthCommandFactory {
	/**
	 * Return instance of {@code FthSingUpCommand}.
	 * <p>
	 *
	 * @return instance of {@code FthSingUpCommand}.
	 */
	@Override
	public FthSingUpCommand create() {
		return new FthSingUpCommand();
	}
}