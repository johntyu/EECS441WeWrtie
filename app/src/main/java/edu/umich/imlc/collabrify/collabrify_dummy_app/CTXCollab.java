package com.eecs.collab;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import edu.umich.imlc.collabrify.client.CollabrifyClient;
import edu.umich.imlc.collabrify.client.CollabrifyListener;
import edu.umich.imlc.collabrify.client.CollabrifyParticipant;
import edu.umich.imlc.collabrify.client.CollabrifySession;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;

/**
 * Created by renjay on 2/5/14.
 */
public class CTXCollab implements
        CollabrifyListener.CollabrifySessionListener, CollabrifyListener.CollabrifyListSessionsListener,
        CollabrifyListener.CollabrifyBroadcastListener, CollabrifyListener.CollabrifyCreateSessionListener,
        CollabrifyListener.CollabrifyJoinSessionListener, CollabrifyListener.CollabrifyLeaveSessionListener {

    private CollabrifyClient myClient;
    private static String TAG = "dummy";
    private ArrayList<String> tags = new ArrayList<String>();

    private static final String GMAIL = "user email";
    private static final String DISPLAY_NAME = "user display name";
    private static final String ACCOUNT_GMAIL = "441winter2014@umich.edu";
    private static final String ACCESS_TOKEN = "338692774BBE";
    private String sessionName;
    private String password = "password";

    // redundant but for the sake of readability
    private CollabrifyListener.CollabrifySessionListener sessionListener = this;
    private CollabrifyListener.CollabrifyListSessionsListener listSessionsListener = this;
    private CollabrifyListener.CollabrifyBroadcastListener broadcastListener = this;
    private CollabrifyListener.CollabrifyCreateSessionListener createSessionListener = this;
    private CollabrifyListener.CollabrifyJoinSessionListener joinSessionListener = this;
    private CollabrifyListener.CollabrifyLeaveSessionListener leaveSessionListener = this;

    public CTXCollab(Context context) {
        // Instantiate client object
        try
        {
            myClient = CollabrifyClient.newClient(context, GMAIL, DISPLAY_NAME,
                    ACCOUNT_GMAIL, ACCESS_TOKEN, false);
        }
        catch( InterruptedException e )
        {
            Log.e(TAG, "error", e);
        }
        catch( ExecutionException e )
        {
            Log.e(TAG, "error", e);
        }

        tags.add("sample");
    }


    public void doCreateSession(Context context)
    {
        try
        {
            Random rand = new Random();
            sessionName = "Test " + rand.nextInt(Integer.MAX_VALUE);
            myClient.createSession(sessionName, tags, password, 0,
                    createSessionListener, sessionListener);

        }
        catch( CollabrifyException e )
        {
            onError(e);
            Toast.makeText(context, "Failed to create", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(context, sessionName, Toast.LENGTH_LONG).show();
    }


    /*Start Collabrify Implementation*/
    /*Start CollabrifyLeaveSessionListener*/
    @Override
    public void onDisconnect() {}
    /*End CollabrifyLeaveSessionListener*/

    /*Start CollabrifyJoinSessionListener*/
    @Override
    public void onSessionJoined(long maxOrderId, long baseFileSize) {}
    /*End CollabrifyJoinSessionListener*/

    /*Start CollabrifyCreateSessionListener Implementation*/
    @Override
    public void onSessionCreated(CollabrifySession session) {}
    /*End CollabrifyCreateSessionListener Implementation*/

    /*Start CollabrifyBroadcastListener Implementation*/
    @Override
    public void onBroadcastDone(byte[] event, long orderId, long srid) {}
    /*End CollabrifyBroadcastListener Implementation*/

    /*Start CollabrifyListSessionsListener Implementation*/
    @Override
    public void onReceiveSessionList(List<CollabrifySession> sessionList) {}
    /*End CollabrifyListSessionsListener Implementation*/

    /*Start CollabrifySessionListener Implementation*/
    @Override
    public void onBaseFileReceived(File baseFile) {}

    @Override
    public void onBaseFileUploadComplete(long baseFileSize) {}

    @Override
    public void onFurtherJoinsPrevented() {}

    @Override
    public void onParticipantJoined(CollabrifyParticipant p) {}

    @Override
    public void onParticipantLeft(CollabrifyParticipant p) {}

    @Override
    public void onReceiveEvent(long orderId, int submissionRegistrationId, String eventType, byte[] data, long elapsed) {}

    @Override
    public void onSessionEnd(long id) {}
    /*End CollabrifySessionListener Implementation*/

    @Override
    public void onError(CollabrifyException e) {}

    /*End Collabrify Implementation*/


}
