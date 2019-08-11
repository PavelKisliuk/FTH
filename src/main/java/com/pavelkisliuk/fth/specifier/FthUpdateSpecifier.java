package com.pavelkisliuk.fth.specifier;

import com.pavelkisliuk.fth.exception.FthRepositoryException;

import java.sql.PreparedStatement;

public interface FthUpdateSpecifier extends FthSpecifier {
	void update(PreparedStatement statement) throws FthRepositoryException;
}
