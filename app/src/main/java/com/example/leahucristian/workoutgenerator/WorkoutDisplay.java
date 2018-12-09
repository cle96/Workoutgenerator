package com.example.leahucristian.workoutgenerator;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leahucristian.workoutgenerator.workout_engine.ExerciseStore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class WorkoutDisplay extends Fragment {

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static WorkoutDisplay newInstance() {
        return new WorkoutDisplay();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        View mview = inflater.inflate(R.layout.workouts_fragment, container, false);
        ListView listView = (ListView) mview.findViewById(R.id.workouts);
        listView.setClickable(true);

        int id = sharedPreferences.getInt("currentId", 0);
        String json = sharedPreferences.getString(String.valueOf(id), "");
        JSONObject jsonObject = new JSONObject();
        JSONArray workouts = new JSONArray();
        try {
            jsonObject = new JSONObject(json);
            String dates = jsonObject.getString("dates");
            workouts = jsonObject.getJSONArray("workouts");
            ArrayList<String> timestamps = new ArrayList<String>(Arrays.asList(dates.substring(1, dates.length() - 1).split(", ")));
            arrayList = timestamps;
        } catch (Exception e) {
            e.printStackTrace();
        }


        arrayAdapter = new ArrayAdapter<String>(
                mview.getContext(),
                android.R.layout.simple_list_item_1,
                arrayList);

        listView.setAdapter(arrayAdapter);

        JSONArray finalWorkouts = workouts;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Show input box
                showExerciseDescriptionBox(arrayList.get(position), position, mview.getContext(), finalWorkouts);
            }
        });
        return mview;


    }

    public void showExerciseDescriptionBox(String oldItem, int index, Context c, JSONArray jsonSlice) {
        final Dialog dialog = new Dialog(c);
        dialog.setTitle("Workout");
        dialog.setContentView(R.layout.workout_fragment);
        dialog.setCanceledOnTouchOutside(true);
        ListView listView = (ListView) dialog.findViewById(R.id.workout);

        ArrayList<String> myList = new ArrayList<>();
        try {
            myList.addAll(Arrays.asList(jsonSlice.getJSONObject(index).getString("printer").split("\n")));
        }catch (Exception e){

        }
        arrayAdapter = new ArrayAdapter<String>(
                dialog.getContext(),
                android.R.layout.simple_list_item_1,
                myList);

        listView.setAdapter(arrayAdapter);
        dialog.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);

        // TODO: Use the ViewModel
    }

}
