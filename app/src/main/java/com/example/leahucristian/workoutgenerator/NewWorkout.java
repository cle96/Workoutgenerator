package com.example.leahucristian.workoutgenerator;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.leahucristian.workoutgenerator.workout_engine.WeeklyWorkout;
import com.example.leahucristian.workoutgenerator.workout_engine.Workout;
import com.squareup.timessquare.CalendarPickerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewWorkout extends Fragment {


    public NewWorkout() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_workout, container, false);

        // calendarPicker actions
        final CalendarPickerView calendar_view = (CalendarPickerView) v.findViewById(R.id.calendar_view);
        Calendar today = Calendar.getInstance();
        Calendar nextWeek = Calendar.getInstance();
        nextWeek.add(Calendar.DAY_OF_WEEK, 7);

        calendar_view.init(today.getTime(), nextWeek.getTime())
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);

        // button actions
        Button btn_show_dates = (Button) v.findViewById(R.id.btn_create_workout);
        btn_show_dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayOfWeek[] days = new DayOfWeek[calendar_view.getSelectedDates().size()];
                for (int i = 0; i < calendar_view.getSelectedDates().size(); i++) {
                    int day = calendar_view.getSelectedDates().get(i).getDay();
                    days[i] = DayOfWeek.of(day == 0 ? 7 : day);
                }

                WeeklyWorkout ww = new WeeklyWorkout(days, WeeklyWorkout.Level.ADVANCED);
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                JSONObject json = ww.toJSON();
                try {
                    json.put("dates", calendar_view.getSelectedDates().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int wwNo = sharedPreferences.getInt("currentId", 0) + 1;
                editor.putString(String.valueOf(wwNo), json.toString());
                editor.putInt("currentId", wwNo);
                editor.commit();
            }
        });

        return v;
    }


}
