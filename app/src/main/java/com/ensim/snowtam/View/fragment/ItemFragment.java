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
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;

    private final RealtimeNotam mRtn;

    /**
     * Creates an ItemFragment with an argument
     */
    public ItemFragment(RealtimeNotam rtn) {
        mRtn = rtn;
    }

    public static ItemFragment newInstance(RealtimeNotam rtn) {
        ItemFragment fragment = new ItemFragment(rtn);
        // Bundle of arguments for fragment creation
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            // Sets the adapter with the Notams list
            recyclerView.setAdapter(new ResultsRecyclerViewAdapter(mRtn, context));
        }
        return view;
    }
}