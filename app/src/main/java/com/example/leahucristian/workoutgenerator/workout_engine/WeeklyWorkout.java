package com.example.leahucristian.workoutgenerator.workout_engine;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;

public class WeeklyWorkout {
	public enum Level {
		BEGINNER, INTERMEDIATE, ADVANCED
	}

	private Workout[] workouts;
	private Level level;

	public WeeklyWorkout(DayOfWeek[] days, Level level) {
		this.workouts = new Workout[days.length];
		this.level = level;
		Exercise[] strengthExercises = ExerciseStore.getStrengthExercises(days.length);
		for (int i = 0; i < days.length; i++) {
			this.workouts[i] = generateWorkout(days[i], strengthExercises[i], level, i);
		}

	}

	public Workout generateWorkout(DayOfWeek day, Exercise strength, Level level, int position){
		return new Workout(day, strength, level, position);
	}

	public Workout[] getWorkouts() {
		return workouts;
	}

	public void setWorkouts(Workout[] workouts) {
		this.workouts = workouts;
	}


	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("level", this.level);
			for(Workout w: this.workouts) {
				json.accumulate("workouts", w.toJSON());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
}
