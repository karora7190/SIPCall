package com.example.saatvik.sipcall;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.net.sip.SipProfile;
import android.util.Log;
import android.widget.Toast;

public class IncomingCallReciever extends BroadcastReceiver {
    public static SipAudioCall incomingCall=null;
    public static String username=null;
    public IncomingCallReciever() {
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        try{
            SipAudioCall.Listener listener=new SipAudioCall.Listener(){
                @Override
                public void onRinging(SipAudioCall call, SipProfile caller) {
                    try {
                        super.onRinging(call, caller);
                        Log.d("Inside on ringing", caller.getProfileName());
                        IncomingCallReciever.username = caller.getDisplayName();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onCallEnded(SipAudioCall call){
                    super.onCallEnded(call);
                    Log.d("on call Ended", "Thanks for calling");
                    Intent i=new Intent(context,CallAccepted.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    i.putExtra("Close",true);
                    context.startActivity(i);
                }
            };
            DialPad wtActivity = (DialPad) context;
            showIncomingCallGui(context);
            incomingCall = wtActivity.sipManager.takeAudioCall(intent, listener);
            wtActivity.sipCall = incomingCall;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showIncomingCallGui(Context context) {

        Intent incomingCall=new Intent(context,IncomingCall.class);
        context.startActivity(incomingCall);
    }

    @TargetApi(value = 12)
    public static void answerIncomingCall(Context context){

        try {
            SipProfile peerProfile=incomingCall.getPeerProfile();
            Toast.makeText(context, "Inside try for on ringing" + peerProfile.getDisplayName(), Toast.LENGTH_LONG).show();
            Toast.makeText(context, "Is call muted??????" + incomingCall.isMuted(), Toast.LENGTH_LONG).show();
            incomingCall.answerCall(30);
            incomingCall.startAudio();
            //incomingCall.setSpeakerMode(true);
            Intent i=new Intent(context,CallAccepted.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("username", peerProfile.getDisplayName());
            context.startActivity(i);
            if (incomingCall.isMuted()) {
                incomingCall.toggleMute();
            }
        }

        catch(Exception e){

            System.out.println(e.toString());
        }

    }

    public static void rejectIncomingCall(Context context) {

        try {
            if (incomingCall != null) {

                incomingCall.endCall();
                incomingCall.close();
                Intent intent=new Intent(context,HomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }


        } catch (Exception e) {

            System.out.println(e.toString());
        }
    }
}
