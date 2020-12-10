package com.ensim.snowtam.View.fragment;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

import com.ensim.snowtam.Model.FormattedNotam;
import com.ensim.snowtam.Model.RealtimeNotam;
import com.ensim.snowtam.R;

import org.jetbrains.annotations.NotNull;

/**
 * {@link RecyclerView.Adapter} that can display a Notam List
 */
public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<ResultsRecyclerViewAdapter.ViewHolder> {

    private final List<FormattedNotam> mValue;
    private final Context mContext;

    /**
     * Constructor
     * @param item
     * @param context
     */
    public ResultsRecyclerViewAdapter(List<FormattedNotam> item, Context context) {
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
            holder.mIdView.setText(mValue.get(position).getTitle());
            holder.mContentView.setText(mValue.get(position).getContent());
        } else {
            holder.mContentView.setText(mContext.getString(R.string.no_snowtam));
        }
    }

    /**
     * Return the number of items
     * If the list is null, return 1
     * @return
     */
    @Override
    public int getItemCount() {
        return (mValue != null ? mValue.size() : 1);
    }

    /**
     * fragment_results_item
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public List<FormattedNotam> mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            // The title of the line
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