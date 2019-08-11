package com.pavelkisliuk.fth.model.exercise;

public class SetLeaf implements ExerciseComponent {
	private int setNumber;
	private int necessaryReps;
	private double weightTool;
	private int restTime;
	private Drill drill;
	private Exercise exercise;

	public static class Drill {
		private long drillBaseId;
		private long muscleGroupId;
		private int drillOrder;

		public long getDrillBaseId() {
			return drillBaseId;
		}

		public void setDrillBaseId(long drillBaseId) {
			this.drillBaseId = drillBaseId;
		}

		public long getMuscleGroupId() {
			return muscleGroupId;
		}

		public void setMuscleGroupId(long muscleGroupId) {
			this.muscleGroupId = muscleGroupId;
		}

		public int getDrillOrder() {
			return drillOrder;
		}

		public void setDrillOrder(int drillOrder) {
			this.drillOrder = drillOrder;
		}
	}

	public static class Exercise {
		private int exerciseNumber;
		private long clientId;

		public int getExerciseNumber() {
			return exerciseNumber;
		}

		public void setExerciseNumber(int exerciseNumber) {
			this.exerciseNumber = exerciseNumber;
		}

		public long getClientId() {
			return clientId;
		}

		public void setClientId(long clientId) {
			this.clientId = clientId;
		}
	}

	public int getNecessaryReps() {
		return necessaryReps;
	}

	public void setNecessaryReps(int necessaryReps) {
		this.necessaryReps = necessaryReps;
	}

	public double getWeightTool() {
		return weightTool;
	}

	public void setWeightTool(double weightTool) {
		this.weightTool = weightTool;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
	}

	public Drill getDrill() {
		return drill;
	}

	public void setDrill(Drill drill) {
		this.drill = drill;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public int getSetNumber() {
		return setNumber;
	}

	public void setSetNumber(int setNumber) {
		this.setNumber = setNumber;
	}

	@Override
	public void add(ExerciseComponent component) {
		throw new UnsupportedOperationException("");
	}

	@Override
	public ExerciseComponent get(int index) {
		throw new UnsupportedOperationException("");
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException("");
	}
}