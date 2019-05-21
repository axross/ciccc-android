package com.derrick.park.artistsfirebase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private ArrayList<Artist> mArtistArrayList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener onItemClickListener;

    public ArtistAdapter(Context context, ArrayList<Artist> artistArrayList, OnItemClickListener onItemClickListener) {
        mArtistArrayList = artistArrayList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate layout for viewHolder
        View itemView = mLayoutInflater.inflate(R.layout.artistlist_item, viewGroup, false);

        return new ArtistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder artistViewHolder, int i) {
        artistViewHolder.bind(mArtistArrayList.get(i));
    }

    @Override
    public int getItemCount() {
        // # of rows
        return mArtistArrayList.size();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {
        public View root;
        private TextView mArtistTextView;
        private TextView mGenreTextView;
        private TextView mAddedDateTextView;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            mArtistTextView = itemView.findViewById(R.id.artistTextView);
            mGenreTextView = itemView.findViewById(R.id.genreTextView);
            mAddedDateTextView = itemView.findViewById(R.id.addedDateTextView);
        }

        public void bind(final Artist artist) {
            mArtistTextView.setText(artist.getName());
            mGenreTextView.setText(artist.getGenre());
            mAddedDateTextView.setText(artist.getAddedDate().toDate().toString());

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(v, artist);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, Artist artist);
    }
}
