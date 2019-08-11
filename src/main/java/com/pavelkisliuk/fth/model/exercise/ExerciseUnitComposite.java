package com.pavelkisliuk.fth.model.exercise;

import java.util.ArrayList;

public class ExerciseUnitComposite implements ExerciseComponent {
	private ArrayList<ExerciseComponent> list = new ArrayList<>();

	@Override
	public void add(ExerciseComponent component) {
		list.add(component);
	}

	@Override
	public ExerciseComponent get(int index) {
		return list.get(index);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public SetLeaf.Exercise getExercise() {
		return list.get(0).getExercise();
	}

	@Override
	public SetLeaf.Drill getDrill() {
		return list.get(0).getDrill();
	}
}