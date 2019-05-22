package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.napping);

                new SimpleAsyncTask(textView).execute();
            }
        });

        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString(TEXT_STATE, textView.getText().toString());
    }

    private static final String TEXT_STATE = "currentText";
}
