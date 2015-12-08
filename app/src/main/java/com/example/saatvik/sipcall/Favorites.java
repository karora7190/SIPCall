package com.example.saatvik.sipcall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    public static ArrayList contacts;
    public ListView allFavorites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        allFavorites = (ListView)findViewById(R.id.listView2);
        contacts=new ArrayList();
        if(contacts.size()==0) {
            contacts.add("No Favorite Contacts Found....");
        }
        ArrayAdapter adapter=new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,contacts);
        allFavorites.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
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
    @Override
    public void onResume(){
        super.onResume();
        if(contacts.size()>1) {
            contacts.remove("No Favorite Contacts Found....");
        }

    }
}
