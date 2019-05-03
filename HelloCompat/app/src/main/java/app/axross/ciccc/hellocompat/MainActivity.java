package app.axross.ciccc.hellocompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView helloTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTextView = findViewById(R.id.hello_textview);

        helloTextView.setOnClickListener((View view) -> onChangeColorButtonClick(view));

        if (savedInstanceState != null) {
            helloTextView.setTextColor(savedInstanceState.getInt("color"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("color", helloTextView.getCurrentTextColor());
    }

    void onChangeColorButtonClick(View view) {

    }
}
