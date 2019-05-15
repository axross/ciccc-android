package app.axross.ciccc.materialme;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Sport> sports;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sports = new ArrayList<>();
        adapter = new SportAdapter(this, sports);
        recyclerView.setAdapter(adapter);

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