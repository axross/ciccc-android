package app.axross.ciccc.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int team1Score = 0;
    private int team2Score = 0;
    private TextView team1ScoreTextView;
    private TextView team2ScoreTextView;
    ImageButton team1ScoreDecreaseButton;
    ImageButton team2ScoreDecreaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
