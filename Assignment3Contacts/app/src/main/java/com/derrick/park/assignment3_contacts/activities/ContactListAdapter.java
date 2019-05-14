package com.derrick.park.assignment3_contacts.activities;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder> {
    private SectionedContactList contactList;

    public ContactListAdapter(ContactList contactList) {
        this.contactList = new SectionedContactList(contactList);
    }

    public ContactListAdapter() {
        this.contactList = new SectionedContactList();
    }

    @Override
    public ContactListAdapter.ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);

        ContactListViewHolder viewHolder = new ContactListViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder, int position) {
        Object item = contactList.get(position);

        if (item.getClass().equals(Contact.class)) {
            holder.setContact((Contact) item);
        }

        if (item.getClass().equals(Character.class)) {
            holder.setContactSectionHeader((Character) item);
        }
    }

    @Override
    public int getItemCount() {
        return contactList.getSize();
    }

    public static class ContactListViewHolder extends RecyclerView.ViewHolder {
        private TextView sectionNameTextView;
        private TextView nameTextView;
        private TextView phoneNumberTextView;

        public ContactListViewHolder(View view) {
            super(view);

            sectionNameTextView = view.findViewById(R.id.contact_list_section_name);
            nameTextView = view.findViewById(R.id.contact_list_item_name);
            phoneNumberTextView = view.findViewById(R.id.contact_list_item_phone_number);
        }

        public void setContactSectionHeader(Character character) {
            sectionNameTextView.setText(character.toString());
            nameTextView.setText(null);
            phoneNumberTextView.setText(null);
        }

        public void setContact(Contact contact) {
            sectionNameTextView.setText(null);
            nameTextView.setText(contact.getFullName());
            phoneNumberTextView.setText(contact.getCell());
        }
    }

    public static class SectionedContactList {
        private ArrayList<ContactSection> sections;

        public SectionedContactList(ContactList contactList) {
            ArrayList<Contact> contacts = new ArrayList(contactList.getContacts());

            Collections.sort(contacts, new Comparator<Contact>() {
                @Override
                public int compare(Contact a, Contact b) {
                    return a.getFullName().compareTo(b.getFullName());
                }
            });

            HashMap<Character, ArrayList<Contact>> contactsByHeadCharacter = new HashMap<>();

            for (Contact contact: contacts) {
                char headCharacter = contact.getFullName().charAt(0);

                if (!contactsByHeadCharacter.containsKey(headCharacter)) {
                    contactsByHeadCharacter.put(headCharacter, new ArrayList<Contact>());
                }

                contactsByHeadCharacter.get(headCharacter).add(contact);
            }

            ArrayList<ContactSection> sections = new ArrayList<>();

            for (Map.Entry<Character, ArrayList<Contact>> entry: contactsByHeadCharacter.entrySet()) {
                sections.add(new ContactSection(entry.getKey(), entry.getValue()));
            }

            this.sections = sections;
        }

        public SectionedContactList() {
            this.sections = new ArrayList();
        }

        public int getSize() {
            int size = 0;

            for (ContactSection section: sections) {
                size += section.getSize();
            }

            return size;
        }

        public Object get(int index) {
            int i = 0;

            for (ContactSection section: sections) {
                for (int j = 0; j < section.getSize(); ++j) {
                    if (i == index) {
                        Log.d("BBBB", section.get(j).toString());

                        return section.get(j);
                    }

                    i += 1;
                }
            }

            return null;
        }
    }

    public static class ContactSection {
        private Character headCharacter;
        private ArrayList<Contact> contacts;

        public ContactSection(Character headCharacter, ArrayList<Contact> contacts) {
            this.headCharacter = headCharacter;
            this.contacts = contacts;
        }

        public Object get(int index) {
            if (index == 0) {
                return this.headCharacter;
            } else {
                return contacts.get(index - 1);
            }
        }

        public int getSize() {
            return 1 + contacts.size();
        }
    }
}