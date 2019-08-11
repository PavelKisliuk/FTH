package com.pavelkisliuk.fth.model.exercise;

import com.pavelkisliuk.fth.model.FthData;

public interface ExerciseComponent extends FthData {
	void add(ExerciseComponent component);

	ExerciseComponent get(int index);

	int size();

	SetLeaf.Exercise getExercise();

	SetLeaf.Drill getDrill();
}