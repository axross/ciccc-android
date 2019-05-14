package app.axross.ciccc.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int team1Score;
    private int team2Score;
    private TextView team1ScoreTextView;
    private TextView team2ScoreTextView;
    ImageButton team1ScoreDecreaseButton;
    ImageButton team2ScoreDecreaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            team1Score = INITIAL_TEAM_SCORE;
            team2Score = INITIAL_TEAM_SCORE;
        } else {
            team1Score = savedInstanceState.getInt(STATE_KEY_TEAM_1_SCORE);
            team2Score = savedInstanceState.getInt(STATE_KEY_TEAM_2_SCORE);
        }

        team1ScoreTextView = findViewById(R.id.team_1_score);
        team2ScoreTextView = findViewById(R.id.team_2_score);
        team1ScoreDecreaseButton = findViewById(R.id.team_1_decrease_button);
        team2ScoreDecreaseButton = findViewById(R.id.team_2_decrease_button);
        ImageButton team1ScoreIncreaseButton = findViewById(R.id.team_1_increase_button);
        ImageButton team2ScoreIncreaseButton = findViewById(R.id.team_2_increase_button);

        team1ScoreIncreaseButton.setOnClickListener((View view) -> changeTeam1Score(1));
        team1ScoreDecreaseButton.setOnClickListener((View view) -> changeTeam1Score(-1));
        team2ScoreIncreaseButton.setOnClickListener((View view) -> changeTeam2Score(1));
        team2ScoreDecreaseButton.setOnClickListener((View view) -> changeTeam2Score(-1));

        team1ScoreTextView.setText(String.valueOf(team1Score));
        team2ScoreTextView.setText(String.valueOf(team2Score));
        onChangeTeam1Score(team1Score);
        onChangeTeam2Score(team2Score);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_KEY_TEAM_1_SCORE, team1Score);
        outState.putInt(STATE_KEY_TEAM_2_SCORE, team2Score);

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_item_day_mode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                recreate();

                return true;
            case R.id.main_menu_item_night_mode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                recreate();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();

        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.main_menu_item_day_mode).setVisible(true);
            menu.findItem(R.id.main_menu_item_night_mode).setVisible(false);
        } else{
            menu.findItem(R.id.main_menu_item_day_mode).setVisible(false);
            menu.findItem(R.id.main_menu_item_night_mode).setVisible(true);
        }

        return super.onCreateOptionsMenu(menu);
    }

    private void onChangeTeam1Score(int score) {
        team1ScoreDecreaseButton.setEnabled(score >= 1);
    }

    private void onChangeTeam2Score(int score) {
        team2ScoreDecreaseButton.setEnabled(score >= 1);
    }

    private void changeTeam1Score(int num) {
        team1Score += num;
        team1ScoreTextView.setText(String.valueOf(team1Score));

        onChangeTeam1Score(team1Score);
    }

    private void changeTeam2Score(int num) {
        team2Score += num;
        team2ScoreTextView.setText(String.valueOf(team2Score));

        onChangeTeam2Score(team2Score);
    }

    static final String STATE_KEY_TEAM_1_SCORE = "STATE_KEY_TEAM_1_SCORE";
    static final String STATE_KEY_TEAM_2_SCORE = "STATE_KEY_TEAM_2_SCORE";

    static final int INITIAL_TEAM_SCORE = 0;
}
