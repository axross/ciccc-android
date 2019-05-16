package app.axross.ciccc.materialme;

import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Sport> sports;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        sports = new ArrayList<>();
        adapter = new SportAdapter(this, sports);
        recyclerView.setAdapter(adapter);

        int swipeDirections;

        if(gridColumnCount > 1){
            swipeDirections = 0;
        } else {
            swipeDirections = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP, swipeDirections) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                Collections.swap(sports, from, to);
                adapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                sports.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeData();
            }
        });

        initializeData();
    }

    private void initializeData() {
        String[] sportTitles = getResources().getStringArray(R.array.sports_titles);
        String[] sportInfos = getResources().getStringArray(R.array.sports_info);
        TypedArray sportImageIds = getResources().obtainTypedArray(R.array.sports_images);

        sports.clear();

        for (int i = 0; i < sportTitles.length; i++) {
            sports.add(new Sport(sportTitles[i], sportInfos[i], sportImageIds.getResourceId(i, 0)));
        }

        adapter.notifyDataSetChanged();
    }
}