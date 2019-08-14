/*  By Pavel Kisliuk, 11.08.2019
 *  This is class for education and nothing rights don't reserved.
 */

package com.pavelkisliuk.fth.command.impl;

import com.pavelkisliuk.fth.exception.FthCommandException;
import com.pavelkisliuk.fth.exception.FthRepositoryException;
import com.pavelkisliuk.fth.model.FthBoolean;
import com.pavelkisliuk.fth.model.FthLong;
import com.pavelkisliuk.fth.repository.FthRepository;
import com.pavelkisliuk.fth.specifier.FthSelectSpecifier;
import com.pavelkisliuk.fth.specifier.select.ClientCorrespondWithTrainerSelectSpecifier;

/**
 * The {@code TrainerActionValidator} class for verification of trainer empowerment's
 * execute actions with concrete client.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class TrainerActionValidator {
	/**
	 * Verify belonging client to trainer.
	 * <p>
	 *
	 * @param trainerId is ID of trainer.
	 * @param clientId  is ID of client.
	 * @return {@code true} if client belong trainer, otherwise return {@code false}.
	 * @throws FthCommandException if {@code FthRepositoryException} occurred.
	 */
	boolean isClientOfTrainer(FthLong trainerId, FthLong clientId) throws FthCommandException {
		FthSelectSpecifier selectSpecifier = new ClientCorrespondWithTrainerSelectSpecifier(trainerId, clientId);
		try {
			FthBoolean bool = (FthBoolean) FthRepository.INSTANCE.query(selectSpecifier).get(0);
			return bool.get();
		} catch (FthRepositoryException e) {
			throw new FthCommandException(
					"FthControllerException in TrainerActionValidator -> execute(HttpServletRequest)", e);
		}
	}
}