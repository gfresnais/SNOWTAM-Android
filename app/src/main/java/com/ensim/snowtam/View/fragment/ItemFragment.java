package com.ensim.snowtam.View.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ensim.snowtam.Model.RealtimeNotam;
import com.ensim.snowtam.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    private final List<String> mRtn;

    /**
     * Creates an ItemFragment with an argument
     */
    public ItemFragment(RealtimeNotam rtn) {
        mRtn = new ArrayList<>();
        String[] dummy = rtn.getAll().split("\n");
        for (String s : dummy) {
            // If it begins with a letter
            if (s.substring(0, 2).matches("[A-Z]{1}[)]{1}")) {
                // Adds the line to the List
                Collections.addAll(mRtn, s.split(" "));
            } else mRtn.add(s);
        }
    }

    public static ItemFragment newInstance(RealtimeNotam rtn) {
        ItemFragment fragment = new ItemFragment(rtn);
        // Bundle of arguments for fragment creation
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            // Sets the adapter with the Notams list
            recyclerView.setAdapter(new ResultsRecyclerViewAdapter(mRtn, context));
        }
        return view;
    }
}