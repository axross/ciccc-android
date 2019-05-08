package app.axross.ciccc.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String orderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.fab).setOnClickListener((view) -> onFabClick(view));
        findViewById(R.id.donut).setOnClickListener((view) -> onDonutClick(view));
        findViewById(R.id.ice_cream).setOnClickListener((view) -> onIceCreamClick(view));
        findViewById(R.id.froyo).setOnClickListener((view) -> onFroyoClick(view));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_order:
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, orderMessage);
                startActivity(intent);

                return true;
            case R.id.action_status:
                displayToast(getString(R.string.action_status_message));
                return true;
            case R.id.action_favorites:
                displayToast(getString(R.string.action_favorites_message));
                return true;
            case R.id.action_contact:
                displayToast(getString(R.string.action_contact_message));
                return true;
            default:
                // Do nothing
        }

        return super.onOptionsItemSelected(item);
    }

    private void onFabClick(View view) {
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);

        intent.putExtra(EXTRA_MESSAGE, orderMessage);

        startActivity(intent);
    }

    private void onDonutClick(View view) {
        String message = getString(R.string.donut_order_message);

        orderMessage = message;
        displayToast(message);
    }

    private void onIceCreamClick(View view) {
        String message = getString(R.string.ice_cream_order_message);

        orderMessage = message;
        displayToast(message);
    }

    private void onFroyoClick(View view) {
        String message = getString(R.string.froyo_order_message);

        orderMessage = message;
        displayToast(message);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static final String EXTRA_MESSAGE = "com.example.android.droidcafe.extra.MESSAGE";
}
