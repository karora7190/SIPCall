package com.example.saatvik.sipcall;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contacts extends AppCompatActivity {

    public ListView showContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        showContacts = (ListView)findViewById(R.id.listView);
        String [] from = new String[]{"name","number"};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,fetchContacts(),android.R.layout.simple_list_item_2,from,new int[]{android.R.id.text1,android.R.id.text2});
        showContacts.setAdapter(simpleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
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
    public List fetchContacts(){
        String phoneNumber = null;
        List finalContacts = new ArrayList();

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID= ContactsContract.Contacts._ID;
        String DisplayName=ContactsContract.Contacts.DISPLAY_NAME;
        String HasPhoneNumber = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PHONE_CONTENT_URI=ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String phone_contact_id=ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String Number = ContactsContract.CommonDataKinds.Phone.NUMBER;
        ContentResolver contentResolver=getContentResolver();
        Cursor contacts = contentResolver.query(CONTENT_URI,null,null,null,null);
        if(contacts.getCount()>0){
            contacts.moveToFirst();
            while(!contacts.isAfterLast()) {
                String contact_id = contacts.getString(contacts.getColumnIndex(_ID));
                String name = contacts.getString(contacts.getColumnIndex(DisplayName));
                int hasNumber = Integer.parseInt(contacts.getString(contacts.getColumnIndex(HasPhoneNumber)));
                Map<String,String> allContacts = new HashMap<>();
                if (hasNumber > 0) {
                    allContacts.put("name", name);
                    Cursor phoneCursor = contentResolver.query(PHONE_CONTENT_URI, null, phone_contact_id + "=?", new String[]{contact_id}, null);
                    phoneCursor.moveToFirst();
                    while (!phoneCursor.isAfterLast()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(Number));
                        allContacts.put("number", phoneNumber);
                        phoneCursor.moveToNext();
                    }
                    finalContacts.add(allContacts);
                    phoneCursor.close();
                }
                contacts.moveToNext();
            }
            contacts.close();
        }

        return finalContacts;
    }
}
