package app.axross.ciccc.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        ((TextView) findViewById(R.id.order_textview)).setText(message);

        ((RadioButton) findViewById(R.id.sameday)).setOnClickListener((view) -> onRadioButtonClick(view));
        ((RadioButton) findViewById(R.id.nextday)).setOnClickListener((view) -> onRadioButtonClick(view));
        ((RadioButton) findViewById(R.id.pickup)).setOnClickListener((view) -> onRadioButtonClick(view));

        ((RadioButton) findViewById(R.id.nextday)).setChecked(true);

        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    private void onRadioButtonClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.sameday:
                if (checked) {
                    displayToast(getString(R.string.same_day_messenger_service));
                }

                break;
            case R.id.nextday:
                if (checked) {
                    displayToast(getString(R.string.next_day_ground_delivery));
                }

                break;
            case R.id.pickup:
                if (checked) {
                    displayToast(getString(R.string.pick_up));
                }

                break;
            default:
                break;
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void displayToast(String message) {
        Toast.makeText(
            getApplicationContext(),
            message,
            Toast.LENGTH_SHORT
        ).show();
    }
}
