package com.pavelkisliuk.fth.model.exercise;

import java.util.ArrayList;

public class ExerciseUnitComposite implements ExerciseComponent {
	private ArrayList<ExerciseComponent> list;

	public void add(ExerciseComponent component) {
		list.add(component);
	}
}
