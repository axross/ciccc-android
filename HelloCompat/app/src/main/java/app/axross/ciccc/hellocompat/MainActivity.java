package app.axross.ciccc.hellocompat;

import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView helloTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTextView = findViewById(R.id.hello_textview);

        Button changeColorButton = findViewById(R.id.color_button);
        changeColorButton.setOnClickListener((View view) -> onChangeColorButtonClick());

        if (savedInstanceState != null) {
            helloTextView.setTextColor(savedInstanceState.getInt("text_color"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("text_color", helloTextView.getCurrentTextColor());
    }

    private void onChangeColorButtonClick() {
        Random random = new Random();

        int[] colors = getResources().getIntArray(R.array.text_colors);

        int color = colors[random.nextInt(colors.length)];

        helloTextView.setTextColor(color);
    }
}
