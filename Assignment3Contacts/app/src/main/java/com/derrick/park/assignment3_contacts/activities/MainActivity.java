package com.derrick.park.assignment3_contacts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;
import com.derrick.park.assignment3_contacts.network.ContactClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView contactListRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ContactList contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contactListRecyclerView = findViewById(R.id.contact_list);
        contactListRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        contactListRecyclerView.setLayoutManager(layoutManager);
        contactListRecyclerView.setAdapter(new ContactListAdapter());

        Call<ContactList> call = ContactClient.getContacts(100);
        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {
                    contactList = response.body();

                    contactListRecyclerView.setLayoutManager(layoutManager);
                    contactListRecyclerView.setAdapter(new ContactListAdapter(contactList));
                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                // Error Handling

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu_item:
                startActivityForResult(new Intent(this, ContactAddActivity.class), INTENT_REQUEST_ADD_CONTACT);

                return true;
        }

        return false;
    }

    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_REQUEST_ADD_CONTACT) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra(ContactAddActivity.EXTRA_NAME_NAME);
                String phoneNumber = data.getStringExtra(ContactAddActivity.EXTRA_NAME_PHONE_NUMBER);

                ContactList newContactList = contactList.copyWithNewContact(new Contact(name, phoneNumber));
                contactListRecyclerView.setAdapter(new ContactListAdapter(newContactList));
            }
        }
    }

    public static int INTENT_REQUEST_ADD_CONTACT = 1;
}
