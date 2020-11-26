package com.ensim.snowtam.View.fragment;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.ensim.snowtam.Model.RealtimeNotam;
import com.ensim.snowtam.R;

import org.jetbrains.annotations.NotNull;

/**
 * {@link RecyclerView.Adapter} that can display a
 */
public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<ResultsRecyclerViewAdapter.ViewHolder> {

    private final RealtimeNotam mValue;
    private final Context mContext;

    public ResultsRecyclerViewAdapter(RealtimeNotam item, Context context) {
        mValue = item;
        mContext = context;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_results_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValue;
        if(holder.mItem != null) {
            holder.mIdView.setText(mValue.getId());
            holder.mContentView.setText(mValue.getAll());
        } else {
            holder.mContentView.setText(mContext.getString(R.string.no_snowtam));
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public RealtimeNotam mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            // Maybe for presentation purposes
            mIdView = (TextView) view.findViewById(R.id.item_number);
            // The content of the SNOWTAM messages
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}