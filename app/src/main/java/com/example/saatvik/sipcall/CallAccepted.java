package com.example.saatvik.sipcall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class CallAccepted extends AppCompatActivity {

    private TextView callerName;
    private TextView callTime;
    private ImageView mic;
    private ImageView dialPad;
    private ImageButton end_call;
    Integer hh=0;
    Integer mm=0;
    Integer ss=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_accepted);
        callerName=(TextView)findViewById(R.id.calling_user);
        callTime=(TextView)findViewById(R.id.calling_time);
        end_call=(ImageButton)findViewById(R.id.imageButton2);
        /*Timer timer=new Timer();
        TimerTask callTimeTask=new TimerTask() {
            @Override
            public void run() {
                if(ss>=60) {
                    mm++;
                }
                else if(mm>=60){
                    hh++;
                }
                else{
                    ss++;
                }
                callTime.setText(hh+":"+mm+":"+ss);
            }
        };
        timer.schedule(callTimeTask,0,1000);*/
        callerName.setText(getIntent().getExtras().getString("username"));
        end_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (IncomingCallReciever.incomingCall != null) {
                        IncomingCallReciever.incomingCall.endCall();
                        IncomingCallReciever.incomingCall.close();
                        finish();
                    }
                    if(DialPad.sipCall!=null){
                        DialPad.sipCall.endCall();
                        DialPad.sipCall.close();
                        finish();
                    }


                } catch (Exception e) {

                    System.out.println(e.toString());
                }
            }
        });

    }
    @Override
    protected void onNewIntent(Intent intent){
        if(intent.getExtras().getBoolean("Close")){
            this.finish();
        }
    }
}
