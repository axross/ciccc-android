package com.derrick.park.artistsfirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
    private ArrayList<Track> mTrackArrayList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TrackAdapter(Context context, ArrayList<Track> artistArrayList) {
        mTrackArrayList = artistArrayList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate layout for viewHolder
        View itemView = mLayoutInflater.inflate(R.layout.artistlist_item, viewGroup, false);

        return new TrackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder artistViewHolder, int i) {
        artistViewHolder.bind(mTrackArrayList.get(i));
    }

    @Override
    public int getItemCount() {
        // # of rows
        return mTrackArrayList.size();
    }

    class TrackViewHolder extends RecyclerView.ViewHolder {
        private TextView mArtistTextView;
        private TextView mRatingTextView;
        private TextView mAddedDateTextView;

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            mArtistTextView = itemView.findViewById(R.id.artistTextView);
            mRatingTextView = itemView.findViewById(R.id.genreTextView);
            mAddedDateTextView = itemView.findViewById(R.id.addedDateTextView);
        }

        public void bind(Track track) {
            mArtistTextView.setText(track.getName());
            mRatingTextView.setText(String.valueOf(track.getRating()));
            mAddedDateTextView.setText(track.getAddedDate().toDate().toString());
        }
    }
}
