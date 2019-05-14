package com.derrick.park.assignment3_contacts.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.derrick.park.assignment3_contacts.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactAddActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText phoneNumberEditText;
    private MenuItem okMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameEditText = findViewById(R.id.name_edit_text);
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);


//        okMenuItem = findViewById(R.id.ok_menu_item);


        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                setOkMenuItemEnabled(isValidName(s.toString()) && isValidPhoneNumber(phoneNumberEditText.getText().toString()));
            }
        });

        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                setOkMenuItemEnabled(isValidName(nameEditText.getText().toString()) && isValidPhoneNumber(s.toString()));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_add, menu);

        okMenuItem = menu.findItem(R.id.ok_menu_item);
        okMenuItem.setEnabled(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ok_menu_item:
                Intent intent = new Intent();

                intent.putExtra(EXTRA_NAME_NAME, nameEditText.getText().toString());
                intent.putExtra(EXTRA_NAME_PHONE_NUMBER, phoneNumberEditText.getText().toString());

                setResult(RESULT_OK, intent);

                finish();

                return true;
        }

        return false;
    }

    private void setOkMenuItemEnabled(boolean enabled) {
        okMenuItem.setEnabled(enabled);
    }

    private static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[A-Za-z]+ [A-Za-z]+$");
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^[0-9]{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public static String EXTRA_NAME_NAME = "com.derrick.park.assignment3_contacts.ContactAddActivity.NAME";
    public static String EXTRA_NAME_PHONE_NUMBER = "com.derrick.park.assignment3_contacts.ContactAddActivity.PHONE_NUMBER";
}
