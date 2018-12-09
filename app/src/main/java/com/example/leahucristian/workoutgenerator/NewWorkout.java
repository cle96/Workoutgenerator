package com.example.leahucristian.workoutgenerator;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewWorkout extends Fragment {
    private WeeklyWorkout.Level lvl = WeeklyWorkout.Level.BEGINNER;

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

                AlertDialog dialog = getLevel(v.getContext(), days, calendar_view.getSelectedDates());
                dialog.show();
            }
        });

        return v;
    }


    public AlertDialog getLevel(Context c, DayOfWeek[] days,List<Date> dates) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(c);
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday" };

        String calendarDates = Arrays.toString(dates.stream().map((d) -> {Calendar cal = Calendar.getInstance(); cal.setTime(d); return strDays[cal.get(Calendar.DAY_OF_WEEK) - 1];}).toArray(String[]::new));
        alertDialog.setTitle("Level");
        alertDialog.setMessage("Select level");
        alertDialog.setNeutralButton("Beginner", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                WeeklyWorkout ww = new WeeklyWorkout(days,  WeeklyWorkout.Level.BEGINNER);
                JSONObject json = ww.toJSON();
                try {
                    json.put("dates", calendarDates);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int wwNo = sharedPreferences.getInt("currentId", 0) + 1;
                editor.putString(String.valueOf(wwNo), json.toString());
                editor.putInt("currentId", wwNo);
                editor.commit();
                dialog.dismiss();
            }
        });

        alertDialog.setNegativeButton("Intermediate", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                WeeklyWorkout ww = new WeeklyWorkout(days,  WeeklyWorkout.Level.INTERMEDIATE);

                JSONObject json = ww.toJSON();
                try {
                    json.put("dates", calendarDates);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int wwNo = sharedPreferences.getInt("currentId", 0) + 1;
                editor.putString(String.valueOf(wwNo), json.toString());
                editor.putInt("currentId", wwNo);
                editor.commit();
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Advanced", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                WeeklyWorkout ww = new WeeklyWorkout(days,  WeeklyWorkout.Level.ADVANCED);
                JSONObject json = ww.toJSON();
                try {
                    json.put("dates", calendarDates);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int wwNo = sharedPreferences.getInt("currentId", 0) + 1;
                editor.putString(String.valueOf(wwNo), json.toString());
                editor.putInt("currentId", wwNo);
                editor.commit();
                dialog.dismiss();
            }
        });

        return alertDialog.create();
    }
}
