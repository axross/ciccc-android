package app.axross.ciccc.dialogforalert;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alertButton = findViewById(R.id.alert_button);
        alertButton.setOnClickListener((view) -> onAlertButtonClick(view));
    }

    private void onAlertButtonClick(View view) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);

        alertBuilder.setTitle(getString(R.string.alert_title));
        alertBuilder.setMessage(getString(R.string.alert_message));

        alertBuilder.setPositiveButton("OK", (DialogInterface dialog, int which) -> {
            Toast.makeText(
                getApplicationContext(),
                "Pressed OK",
                Toast.LENGTH_SHORT
            ).show();
        });

        alertBuilder.setNegativeButton("Cancel", (DialogInterface dialog, int which) -> {
            Toast.makeText(
                getApplicationContext(),
                "Pressed Cancel",
                Toast.LENGTH_SHORT
            ).show();
        });

        alertBuilder.show();
    }
}
