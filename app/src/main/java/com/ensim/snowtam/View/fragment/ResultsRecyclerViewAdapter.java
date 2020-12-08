package com.ensim.snowtam.View.fragment;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

import com.ensim.snowtam.Model.RealtimeNotam;
import com.ensim.snowtam.R;

import org.jetbrains.annotations.NotNull;

/**
 * {@link RecyclerView.Adapter} that can display a
 */
public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<ResultsRecyclerViewAdapter.ViewHolder> {

    private final List<String> mValue;
    private final Context mContext;

    /**
     * Constructor
     * @param item
     * @param context
     */
    public ResultsRecyclerViewAdapter(List<String> item, Context context) {
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
            // Detect first word and use it at
            String first = mValue.get(position).substring(0, mValue.get(position).indexOf(")") + 1);
            String str = mValue.get(position).substring(first.length());
            holder.mIdView.setText(first);
            holder.mContentView.setText(str);
        } else {
            holder.mContentView.setText(mContext.getString(R.string.no_snowtam));
        }
    }

    /**
     * Always returns 1
     * @return
     */
    @Override
    public int getItemCount() {
        return mValue.size();
    }

    /**
     * fragment_results_item
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public List<String> mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            // Maybe for presentation purposes
            mIdView = view.findViewById(R.id.item_number);
            // The content of the SNOWTAM messages
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}