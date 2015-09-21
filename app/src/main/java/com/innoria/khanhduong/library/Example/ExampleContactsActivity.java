package com.innoria.khanhduong.library.Example;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.innoria.khanhduong.library.Example.Adapter.ContactAdapter;
import com.innoria.khanhduong.library.R;
import com.innoria.khanhduong.library.Systems.Contacts.Contact;
import com.innoria.khanhduong.library.Utils.SystemUtils;

import java.util.List;

public class ExampleContactsActivity extends AppCompatActivity {
    private ListView listView;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_contacts);
        listView = (ListView) findViewById(R.id.list);

        contactList = SystemUtils.getContacts(getBaseContext());
        contactAdapter = new ContactAdapter(this, R.layout.example_item_contact, contactList);
        listView.setAdapter(contactAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_example_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
