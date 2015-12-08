package com.example.saatvik.sipcall;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.sip.SipAudioCall;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialPad extends AppCompatActivity {

    public Button one;
    public Button two;
    public Button three;
    public Button four;
    public Button five;
    public Button six;
    public Button seven;
    public Button eight;
    public Button nine;
    public Button zero;
    public Button star;
    public Button pound;
    public Button call;
    public EditText contact;
    public Button clear;
    public SharedPreferences preferences;
    String userName;
    String password;
    String domain;
    public static SipManager sipManager;
    public static SipProfile sipProfile;
    public static SipAudioCall sipCall;
    public static IncomingCallReciever callReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial_pad);
        contact=(EditText)findViewById(R.id.editText);
        contact.setText("");
        one=(Button)findViewById(R.id.button2);
        two=(Button)findViewById(R.id.button13);
        three=(Button)findViewById(R.id.button4);
        four=(Button)findViewById(R.id.button5);
        five=(Button)findViewById(R.id.button6);
        six=(Button)findViewById(R.id.button7);
        seven=(Button)findViewById(R.id.button8);
        eight=(Button)findViewById(R.id.button9);
        nine=(Button)findViewById(R.id.button10);
        zero=(Button)findViewById(R.id.button11);
        star=(Button)findViewById(R.id.button12);
        pound=(Button)findViewById(R.id.button14);
        clear=(Button)findViewById(R.id.button15);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.SipDemo.INCOMING_CALL");
        callReceiver = new IncomingCallReciever();
        this.registerReceiver(callReceiver, filter);
        call=(Button)findViewById(R.id.button14);
    }
    @Override
    public void onStart()
    {
        super.onStart();


    }
    @TargetApi(value = 12)
    @Override
    public void onResume(){
        super.onResume();
        try {
            preferences = PreferenceManager.getDefaultSharedPreferences(this);
            userName = preferences.getString("example_text", "username");
            password = preferences.getString("example_password", "password");
            domain = preferences.getString("example_domain", "domain");
            if (userName.equals("username") || password.equals("password") || domain.equals("domain")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Enter Settings");
                builder.setMessage("Please configure your account settings");
                builder.setPositiveButton("Go To Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            } else {
                try {
                    if (sipManager == null) {
                        sipManager = SipManager.newInstance(getBaseContext());
                    }
                    SipProfile.Builder builder = new SipProfile.Builder(userName, domain);
                    builder.setPassword(password);
                    sipProfile = builder.build();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!sipManager.isRegistered(sipProfile.getUriString())) {
                registerSIPUser(sipProfile);
            }
            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + one.getText().toString());
                }
            });
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + two.getText().toString());
                }
            });
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + three.getText().toString());
                }
            });
            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + four.getText().toString());
                }
            });
            five.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + five.getText().toString());
                }
            });
            six.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + six.getText().toString());
                }
            });
            seven.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + seven.getText().toString());
                }
            });
            eight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + eight.getText().toString());
                }
            });
            nine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + nine.getText().toString());
                }
            });
            zero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + zero.getText().toString());
                }
            });
            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + star.getText().toString());
                }
            });
            pound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contact.setText(contact.getText() + "" + pound.getText().toString());
                }
            });
            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String con_text = contact.getText().toString();
                    if (con_text.length() > 0) {
                        contact.setText(con_text.substring(0, con_text.length() - 1));
                    } else {
                        Toast.makeText(getBaseContext(), "Nothing to Clear.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            final SipAudioCall.Listener myListener = new SipAudioCall.Listener() {
                @Override
                public void onRinging(SipAudioCall call, SipProfile caller) {
                    try {
                        Intent intent1 = new Intent(getBaseContext(), IncomingCall.class);
                        startActivity(intent1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        SipAudioCall.Listener listener = new SipAudioCall.Listener() {
                            // Much of the client's interaction with the SIP Stack will
                            // happen via listeners.  Even making an outgoing call, don't
                            // forget to set up a listener to set things up once the call is established.
                            @Override
                            public void onCallEstablished(SipAudioCall call) {
                                call.startAudio();
                                call.setSpeakerMode(true);
                                if(call.isMuted()) {
                                    call.toggleMute();
                                }
                                SipProfile peerProfile=call.getPeerProfile();
                                Intent intent=new Intent(DialPad.this,CallAccepted.class);
                                intent.putExtra("username", peerProfile.getDisplayName());
                                startActivity(intent);
                                //updateStatus(call);
                                Log.d("onCallEstablished", "Your Call has been connected");
                            }

                            @Override
                            public void onCallEnded(SipAudioCall call) {
                                //updateStatus("Ready.");
                                Log.d("onCallEnded", "Your Call has Ended in UserAccount Class");
                                Intent intent=new Intent(DialPad.this,CallAccepted.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                intent.putExtra("Close",true);
                                startActivity(intent);
                            }
                        };
                        sipCall = sipManager.makeAudioCall(sipProfile.getUriString(), contact.getText()+"@merit.s.zswitch.net", listener, 30);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dial_pad, menu);
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
    public void registerSIPUser(SipProfile sipProfile) {
        try {
            System.out.println(sipProfile.getUriString());
            System.out.println(password);
            Intent i = new Intent();
            i.setAction("android.SipDemo.INCOMING_CALL");
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, Intent.FILL_IN_DATA);
            sipManager.open(sipProfile, pi, null);
            //sipManager.register(sipProfile, 10000, listener);
            SipRegistrationListener listener = new SipRegistrationListener() {
                @Override
                public void onRegistering(String localProfileUri) {
                    Log.d("Registering", "Registering.......");
                }

                @Override
                public void onRegistrationDone(String localProfileUri, long expiryTime) {
                    Log.d("Registered", "Registration success.......");
                }

                @Override
                public void onRegistrationFailed(String localProfileUri, int errorCode, String errorMessage) {
                    Log.d("Registeration failed", "Sorry.........." + errorCode + " " + errorMessage);
                }
            };
            sipManager.setRegistrationListener(sipProfile.getUriString(), listener);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
