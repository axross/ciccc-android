package com.derrick.park.artistsfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class ArtistDetailActivity extends AppCompatActivity {
    private RecyclerView mTrackRecyclerView;
    private TrackAdapter mTrackAdapter;
    private EditText mNameEditText;
    private SeekBar mRatingSeekBar;

    private String artistId;
    private ArrayList<Track> mTrackArrayList;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListenerRegistration mListenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);

        Intent intent = getIntent();
        artistId = intent.getStringExtra("artistId");

        mNameEditText = findViewById(R.id.nameEditText);
        mRatingSeekBar = findViewById(R.id.ratingSeekBar);
        mTrackRecyclerView = findViewById(R.id.artistsRecyclerView);
        mTrackRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("AAAAA", artistId);

        mListenerRegistration = db.collection("artists").document(artistId).collection("tracks")
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                mTrackArrayList = (ArrayList<Track>) queryDocumentSnapshots.toObjects(Track.class);
                mTrackAdapter = new TrackAdapter(getApplicationContext(), mTrackArrayList);
                mTrackRecyclerView.setAdapter(mTrackAdapter);
                }
            });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // detach listener
        mListenerRegistration.remove();
    }

    public void addTrack(View view) {
        final String name = mNameEditText.getText().toString().trim(); // get rid of whitespaces
        int rating = mRatingSeekBar.getProgress();
        if (!TextUtils.isEmpty(name)) {
            // if name is not empty
            Track track = new Track(name, rating);
            mTrackArrayList.add(track);
            // 1. get the database instance
            // 2. set the collection (path)
            db.collection("artists").document(artistId).collection("tracks")
                    .add(track) // generates id string automatically
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            // when added successfully
                            Snackbar.make(findViewById(R.id.coordinatorLayout), name + " successfully added!", Snackbar.LENGTH_LONG)
                                    .show();
                            // after adding an artist
                            mNameEditText.setText("");
                            mNameEditText.clearFocus();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // when failed
                        }
                    });
//            mTrackAdapter.notifyDataSetChanged(); // refresh recyclerView
        } else {
            // if name is empty
            Snackbar.make(findViewById(R.id.coordinatorLayout), "Please set the track name!", Snackbar.LENGTH_LONG)
                    .show();
        }

    }
}
