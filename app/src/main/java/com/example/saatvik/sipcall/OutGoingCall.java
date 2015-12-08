package com.example.saatvik.sipcall;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class OutGoingCall extends Activity {

    public TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outgoing_call);
        username=(TextView)findViewById(R.id.textView14);
        username.setText(getIntent().getExtras().getString("profile"));

    }

}
