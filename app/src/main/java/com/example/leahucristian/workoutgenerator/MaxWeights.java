package com.example.leahucristian.workoutgenerator;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */

public class MaxWeights extends Fragment {


    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public MaxWeights() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_max_weights, container, false);
        // Inflate the layout for this fragment
        ListView listView = (ListView) mview.findViewById(R.id.weight);
        listView.setClickable(true);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        arrayList = formatList();
        arrayAdapter = new ArrayAdapter<String>(
                mview.getContext(),
                android.R.layout.simple_list_item_1,
                arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Show input box
                showInputBox(arrayList.get(position), position, mview.getContext());
            }
        });
        return mview;
    }

    public void showInputBox(String oldItem, final int index, Context c) {
        final Dialog dialog = new Dialog(c);
        String exName = oldItem.split(" # ")[0];
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.input_weights);
        TextView txtMessage = (TextView) dialog.findViewById(R.id.txtmessage);
        txtMessage.setText("Update item");
        txtMessage.setTextColor(Color.parseColor("#ff2222"));
        final EditText editText = (EditText) dialog.findViewById(R.id.txtinput);
        editText.setText(sharedPreferences.getInt(exName,0)+"");
        Button bt = (Button) dialog.findViewById(R.id.btdone);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt(exName, Integer.parseInt(editText.getText().toString()));
                editor.commit();
                arrayList.set(index, formatList().get(index));
                arrayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public ArrayList<String> formatList() {

        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(ExerciseStore.getExercisesNames()));
        for(int i=0; i<list.size(); i++){
            String val = list.get(i);
            list.set(i, val + " # " + sharedPreferences.getInt(val,0));
        }
        return list;
    }




}
