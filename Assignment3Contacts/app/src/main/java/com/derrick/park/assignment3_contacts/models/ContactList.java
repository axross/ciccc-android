package com.derrick.park.assignment3_contacts.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactList {
    public ContactList(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @SerializedName("results")
    @Expose
    private ArrayList<Contact> contacts;

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public ContactList copyWithNewContact(Contact contact) {
        ArrayList<Contact> contacts = new ArrayList(this.contacts);
        contacts.add(contact);

        return new ContactList(contacts);
    }
}
