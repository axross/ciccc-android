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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order) {
            return true;
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
        displayToast(message, view);
    }

    private void onIceCreamClick(View view) {
        String message = getString(R.string.ice_cream_order_message);

        orderMessage = message;
        displayToast(message, view);
    }

    private void onFroyoClick(View view) {
        String message = getString(R.string.froyo_order_message);

        orderMessage = message;
        displayToast(message, view);
    }

    public void displayToast(String message, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static final String EXTRA_MESSAGE = "com.example.android.droidcafe.extra.MESSAGE";
}
