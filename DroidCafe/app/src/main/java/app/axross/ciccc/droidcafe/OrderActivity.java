package app.axross.ciccc.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {
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

    public void displayToast(String message) {
        Toast.makeText(
            getApplicationContext(),
            message,
            Toast.LENGTH_SHORT
        ).show();
    }
}
