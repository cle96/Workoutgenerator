package com.example.leahucristian.workoutgenerator.workout_engine;

import android.content.SharedPreferences;
import android.support.v7.recyclerview.extensions.ListAdapter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.leahucristian.workoutgenerator.workout_engine.Exercise.Muscle;
import com.example.leahucristian.workoutgenerator.workout_engine.Exercise.Classification;

public class ExerciseStore {
    private static List<Exercise> exercises;

    public ExerciseStore() {
        this.exercises = new ArrayList<Exercise>();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public static void initStore() {
        exercises = new ArrayList<>();
        exercises.addAll(Arrays.asList(
                new Exercise(true, "Deadlifts", Classification.STRENGTH, Muscle.BACK, Muscle.LEGS, null),
                new Exercise(false, "Chest bar push", Classification.STRENGTH, Muscle.CHEST, Muscle.TRICEPS,
                        new Muscle[]{Muscle.UPPER_SHOULDER, Muscle.LOWER_CHEST}),
                new Exercise(false, "Pull-ups", Classification.STRENGTH, Muscle.BACK, Muscle.BICEPS,
                        new Muscle[]{Muscle.UPPER_SHOULDER}),
                new Exercise(true, "Squats", Classification.STRENGTH, Muscle.LEGS, Muscle.CORE, null),
                new Exercise(true, "Sumo deadlifts", Classification.STRENGTH, Muscle.BACK, Muscle.LEGS, null),
                new Exercise(true, "Clean", Classification.STRENGTH, Muscle.SHOULDER, Muscle.TRAPS, null),
                new Exercise(true, "Clean and Jerk", Classification.STRENGTH, Muscle.SHOULDER, Muscle.LEGS, null),
                new Exercise(true, "Squat Clean", Classification.STRENGTH, Muscle.LEGS, Muscle.SHOULDER, null),
                new Exercise(true, "Overhead Squat", Classification.STRENGTH, Muscle.LEGS, Muscle.SHOULDER, null),
                new Exercise(true, "Push Press", Classification.STRENGTH, Muscle.SHOULDER, Muscle.CORE, null),
                new Exercise(true, "Snatch dumbell", Classification.STRENGTH, Muscle.SHOULDER, Muscle.LEGS, null),
                new Exercise(false, "Traps large grip", Classification.INTERVAL, Muscle.TRAPS, Muscle.CORE, null),
                new Exercise(false, "Wondering with weights", Classification.INTERVAL, Muscle.TRAPS, Muscle.CORE, null),
                new Exercise(false, "Lateral shoulder lifts", Classification.ISOLATE, Muscle.TRAPS, Muscle.SHOULDER,
                        null),
                new Exercise(false, "Lifting disk behind head", Classification.INTERVAL, Muscle.TRAPS, Muscle.SHOULDER,
                        null),
                new Exercise(false, "Chin-ups", Classification.STRENGTH, Muscle.BACK, Muscle.BICEPS,
                        new Muscle[]{Muscle.UPPER_SHOULDER}),
                new Exercise(false, "Lats weight pull", Classification.ISOLATE, Muscle.UPPER_BACK, Muscle.UPPER_ABS,
                        null),
                new Exercise(false, "Bar pull", Classification.STRENGTH, Muscle.BACK, Muscle.BICEPS, null),
                new Exercise(false, "Pulling yourself to bar", Classification.STRENGTH, Muscle.UPPER_BACK,
                        Muscle.LOWER_SHOULDER, new Muscle[]{Muscle.BICEPS, Muscle.CORE}),
                new Exercise(false, "Incline chest push", Classification.ISOLATE, Muscle.CHEST, Muscle.UPPER_SHOULDER,
                        new Muscle[]{Muscle.TRICEPS}),
                new Exercise(false, "Raising dumbell over head from bench", Classification.ISOLATE, Muscle.UPPER_BACK,
                        Muscle.UPPER_CHEST, null),
                new Exercise(false, "Dips", Classification.STRENGTH, Muscle.TRICEPS, Muscle.LOWER_CHEST, null),
                new Exercise(false, "Push ups", Classification.INTERVAL, Muscle.CHEST, Muscle.TRICEPS,
                        new Muscle[]{Muscle.CORE}),
                new Exercise(false, "Push ups on weigths", Classification.INTERVAL, Muscle.CHEST, Muscle.UPPER_CHEST,
                        null),
                new Exercise(false, "Normal flies", Classification.ISOLATE, Muscle.CHEST, null, null),
                new Exercise(false, "Front dumbell raises", Classification.ISOLATE, Muscle.SHOULDER, Muscle.TRAPS,
                        null),
                new Exercise(false, "Dumbell raises", Classification.STRENGTH, Muscle.SHOULDER, Muscle.TRICEPS, null),
                new Exercise(false, "Outside flies", Classification.ISOLATE, Muscle.LOWER_SHOULDER, Muscle.UPPER_BACK,
                        new Muscle[]{Muscle.CORE}),
                new Exercise(false, "Biceps curls", Classification.STRENGTH, Muscle.BICEPS, Muscle.FOREARM, null),
                new Exercise(false, "Biceps hammers", Classification.ISOLATE, Muscle.BICEPS, Muscle.FOREARM, null),
                new Exercise(false, "Triceps dips on two benches", Classification.ISOLATE, Muscle.TRICEPS, null, null),
                new Exercise(false, "Dumbell pushes close grip", Classification.ISOLATE, Muscle.TRICEPS, Muscle.CHEST,
                        null),
                new Exercise(false, "Incline abs", Classification.ISOLATE, Muscle.ABS, Muscle.LOWER_BACK, null),
                new Exercise(false, "Planking", Classification.STRENGTH, Muscle.ABS, Muscle.LOWER_BACK, null),
                new Exercise(false, "Abs from holding position", Classification.STRENGTH, Muscle.ABS, Muscle.UPPER_BACK,
                        null),
                new Exercise(false, "Split Squat with dumbells", Classification.ISOLATE, Muscle.HAMSTRINGS,
                        Muscle.QUADS, null),
                new Exercise(false, "Split Squat with disk", Classification.ISOLATE, Muscle.HAMSTRINGS, Muscle.QUADS,
                        null),
                new Exercise(false, "Wide squats", Classification.ISOLATE, Muscle.LEGS, Muscle.CORE, null),
                new Exercise(false, "Calves raises", Classification.ISOLATE, Muscle.CALVES, Muscle.CORE, null)));

    }

    public void cleanExercises() {
        this.exercises = new ArrayList<>();
    }

    public static Exercise[] getStrengthExercises(int amount) {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("Deadlifts", "Chest bar push", "Pull-ups", "Squats", "Sumo deadlifts"));

        if (amount == 1) {
            return (Exercise[]) exercises.stream().filter((e) -> list.get(0).equals(e.getName())).toArray(Exercise[]::new);
        }

        return exercises.stream().filter((e) -> list.subList(1, amount + 1).contains(e.getName())).toArray(Exercise[]::new);
    }

    public static Exercise[] getKillerExercises(int position) {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("Squats", "Chest bar push", "Bar pull"));

        List<String> list2 = new ArrayList<>();
        list2.addAll(Arrays.asList("Squat Clean", "Incline chest push", "Lats weight pull"));

        List<String> list3 = new ArrayList<>();
        list3.addAll(Arrays.asList("Snatch dumbell", "Overhead press", "Pulling yourself to bar"));

        List<String> list4 = new ArrayList<>();
        list4.addAll(Arrays.asList("Clean", "Chest bar push", "Pull-ups"));

        List<String> list5 = new ArrayList<>();
        list5.addAll(Arrays.asList("Snatch", "Deadlifts", "Bar pull"));

        List<List<String>> killerExs = new ArrayList<List<String>>();
        killerExs.addAll(Arrays.asList(list, list2, list3, list4, list5));

        return exercises.stream().filter((e) -> killerExs.get(position).contains(e.getName())).toArray(Exercise[]::new);
    }

    public static Exercise[] getRandomPyramidExercises(WeeklyWorkout.Level level) {
        int amount = 5 + WeeklyWorkout.Level.valueOf(level.toString()).ordinal();
        List<Exercise> finalList = new ArrayList<>();
        List<Exercise> listWithStrength =  exercises.stream().filter((e) -> e.isFullBody()).collect(Collectors.toList());
        List<Exercise> restOfExercises =  exercises.stream().filter((e) -> !e.isFullBody()).collect(Collectors.toList());
        Collections.shuffle(listWithStrength);
        Collections.shuffle(restOfExercises);
        switch (amount) {
            case 5:
                finalList.add(listWithStrength.get(0));
                finalList.addAll(restOfExercises.subList(0, 2));
                finalList.add(listWithStrength.get(1));

                break;

            case 6:
                finalList.add(listWithStrength.get(0));
                finalList.add(restOfExercises.get(0));
                finalList.add(listWithStrength.get(1));
                finalList.add(restOfExercises.get(2));
                finalList.add(listWithStrength.get(2));
                break;

            case 7:
                finalList.add(listWithStrength.get(0));
                finalList.add(restOfExercises.get(0));
                finalList.add(listWithStrength.get(1));
                finalList.add(restOfExercises.get(2));
                finalList.add(listWithStrength.get(2));
                finalList.add(listWithStrength.get(3));
                break;

            default:
                break;
        }

        return Stream
                .concat(finalList.subList(0, amount -1).stream(),
                        finalList.subList(0, amount -2).stream().sorted(Collections.reverseOrder((o1, o2) -> -1)))
                .toArray(Exercise[]::new);
    }

    public static Exercise[] getRandomTabataExercises() {
        List<Exercise> list = new ArrayList<Exercise>();
        list.addAll(exercises);
        Collections.shuffle(list);
        return list.subList(0, 10).stream().toArray(Exercise[]::new);
    }

    public static String[] getExercisesNames() {
		return exercises.stream().map((e) -> {return e.getName();}).toArray(String[]::new);
    }
}
