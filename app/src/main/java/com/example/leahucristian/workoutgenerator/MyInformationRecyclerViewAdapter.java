package com.example.leahucristian.workoutgenerator;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leahucristian.workoutgenerator.InformationFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MyInformationRecyclerViewAdapter extends RecyclerView.Adapter<MyInformationRecyclerViewAdapter.ViewHolder> {

    private Map<String, String> exercises = new TreeMap<>();
    private final OnListFragmentInteractionListener mListener;

    public MyInformationRecyclerViewAdapter(Map<String, String> exercises, OnListFragmentInteractionListener listener) {
        this.exercises = exercises;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_information, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String k = new ArrayList<>(exercises.keySet()).get(position);
        holder.mItem = k;
        holder.mIdView.setText(k);
        SpannableStringBuilder ssb = new SpannableStringBuilder(exercises.get(k));
        holder.mContentView.setText(ssb, TextView.BufferType.SPANNABLE);
        Linkify.addLinks(holder.mContentView, Linkify.WEB_URLS);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
