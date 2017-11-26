package me.varunon9.fakelock;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import me.varunon9.fakelock.MainFragment.OnListFragmentInteractionListener;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<ApplicationInfo> mValues;
    private final OnListFragmentInteractionListener mListener;
    private PackageManager packageManager;

    public MyItemRecyclerViewAdapter(List<ApplicationInfo> items,
                                     OnListFragmentInteractionListener listener,
                                     PackageManager packageManager) {
        mValues = items;
        mListener = listener;
        this.packageManager = packageManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.appPackageNameTextView.setText(mValues.get(position).packageName);
        holder.appNameTextView.setText(mValues.get(position).loadLabel(packageManager));
        holder.appIconImageView.setImageDrawable(mValues.get(position).loadIcon(packageManager));

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
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView appNameTextView;
        public final TextView appPackageNameTextView;
        public final ImageView appIconImageView;
        public final ToggleButton hideAppToggleButton;
        public ApplicationInfo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            appNameTextView = (TextView) view.findViewById(R.id.appNameTextView);
            appPackageNameTextView = (TextView) view.findViewById(R.id.appPackageNameTextView);
            appIconImageView = (ImageView) view.findViewById(R.id.appIconImageView);
            hideAppToggleButton = (ToggleButton) view.findViewById(R.id.hideAppToggleButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + appNameTextView.getText() + "'";
        }
    }
}
