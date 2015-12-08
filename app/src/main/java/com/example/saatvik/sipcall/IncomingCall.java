package com.example.saatvik.sipcall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IncomingCall extends AppCompatActivity {

    public TextView displayName;
    public Button acceptCall;
    public Button rejectCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_call);
        displayName = (TextView)findViewById(R.id.textView10);
        acceptCall = (Button)findViewById(R.id.button15);
        rejectCall = (Button)findViewById(R.id.button16);
        acceptCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IncomingCallReciever.answerIncomingCall(v.getContext());
                IncomingCall.this.finish();
            }
        });
        rejectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IncomingCallReciever.rejectIncomingCall(v.getContext());
                IncomingCall.this.finish();
            }
        });
    }
}
