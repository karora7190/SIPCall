package com.example.saatvik.sipcall;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class HomeScreen extends TabActivity {

    public TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tabHost =(TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec dialPad = tabHost.newTabSpec("Dialpad");
        TabHost.TabSpec logs = tabHost.newTabSpec("logs");
        TabHost.TabSpec favorites = tabHost.newTabSpec("favorites");
        TabHost.TabSpec contacts = tabHost.newTabSpec("contacts");
        TabHost.TabSpec settings = tabHost.newTabSpec("settings");

        dialPad.setIndicator("DialPad");
        dialPad.setContent(new Intent(this, DialPad.class));
        logs.setIndicator("Call History");
        logs.setContent(new Intent(this, CallHistory.class));
        favorites.setIndicator("Favorites");
        favorites.setContent(new Intent(this, Favorites.class));
        contacts.setIndicator("Contacts");
        contacts.setContent(new Intent(this, Contacts.class));
        settings.setIndicator("Settings");
        settings.setContent(new Intent(this, Settings.class));
        tabHost.addTab(dialPad);
        tabHost.addTab(logs);
        tabHost.addTab(favorites);
        tabHost.addTab(contacts);
        tabHost.addTab(settings);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
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
