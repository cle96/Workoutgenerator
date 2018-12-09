package com.example.leahucristian.workoutgenerator.workout_engine;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.leahucristian.workoutgenerator.MainActivity;

import java.util.Map;
import java.util.TreeMap;

public class ExerciseHelper {
    public static Map<String, String> getExercisesDescription(){
        Map<String, String> exercises = new TreeMap<>();
        exercises.put("Deadlifts",
                "https://www.youtube.com/watch?v=op9kVnSso6Q");
        exercises.put("Chest bar push",
                "https://www.youtube.com/watch?v=clBGsv573K4");
        exercises.put("Pull-ups",
                "https://www.youtube.com/watch?v=aAggnpPyR6E");
        exercises.put("Squats",
                "https://www.youtube.com/watch?v=ultWZbUMPL8");
        exercises.put("Sumo deadlifts",
                "https://www.youtube.com/watch?v=wQHSYDSgDn8");
        exercises.put("Clean",
                "https://www.youtube.com/watch?v=EKRiW9Yt3Ps");
        exercises.put("Clean and Jerk",
                "https://www.youtube.com/watch?v=8miqQQJEsO0");
        exercises.put("Squat Clean",
                "https://www.youtube.com/watch?v=g9Y8QwctmhU");
        exercises.put("Overhead Squat",
                "https://www.youtube.com/watch?v=RD_vUnqwqqI");
        exercises.put("Push Press",
                "https://www.youtube.com/watch?v=X6-DMh-t4nQ");
        exercises.put("Snatch dumbell",
                "https://www.youtube.com/watch?v=9520DJiFmvE");
        exercises.put("Chin-ups",
                "https://www.youtube.com/watch?v=mTRT9O5r8Wg");
        exercises.put("Bar pull",
                "https://www.youtube.com/watch?v=X6-DMh-t4nQ");
        exercises.put("Pulling yourself to bar",
                "https://www.youtube.com/watch?v=Sqbt2watwns");
        exercises.put("Dips",
                "https://www.youtube.com/watch?v=eERwCQHZqfA");
        exercises.put("Push ups",
                "https://www.youtube.com/watch?v=_l3ySVKYVJ8");
        exercises.put("More information",
                "https://www.crossfit.com/exercisedemos/");

        return exercises;
    }

    public static String getMax(String ex, WorkoutRoutine.Type type){
        int amount = MainActivity.preferences.getInt(ex,0);
        if(type == WorkoutRoutine.Type.KILLER){
            amount = amount - (amount / 5);
        }

        if(type == WorkoutRoutine.Type.PYRAMID){
            amount = amount - (amount / 4);
        }

        if(type == WorkoutRoutine.Type.TABATA){
            amount = amount - (amount / 3);
        }

        return amount + "";
    }
}
