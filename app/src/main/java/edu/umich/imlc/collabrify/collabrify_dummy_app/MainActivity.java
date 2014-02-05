package edu.umich.imlc.collabrify.collabrify_dummy_app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.umich.imlc.android.common.Utils;
import edu.umich.imlc.collabrify.client.CollabrifyClient;
import edu.umich.imlc.collabrify.client.CollabrifyListener.CollabrifyBroadcastListener;
import edu.umich.imlc.collabrify.client.CollabrifyListener.CollabrifyCreateSessionListener;
import edu.umich.imlc.collabrify.client.CollabrifyListener.CollabrifyJoinSessionListener;
import edu.umich.imlc.collabrify.client.CollabrifyListener.CollabrifyLeaveSessionListener;
import edu.umich.imlc.collabrify.client.CollabrifyListener.CollabrifyListSessionsListener;
import edu.umich.imlc.collabrify.client.CollabrifyListener.CollabrifySessionListener;
import edu.umich.imlc.collabrify.client.CollabrifyParticipant;
import edu.umich.imlc.collabrify.client.CollabrifySession;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyUnrecoverableException;

public class MainActivity extends Activity implements
    CollabrifySessionListener, CollabrifyListSessionsListener,
    CollabrifyBroadcastListener, CollabrifyCreateSessionListener,
    CollabrifyJoinSessionListener, CollabrifyLeaveSessionListener
{
  private static String TAG = "dummy";

  private static final String GMAIL = "user@mail.com";
  private static final String DISPLAY_NAME = "CollabrifyGLY";
  private static final String ACCOUNT_GMAIL = "441winter2014@umich.edu";
  private static final String ACCESS_TOKEN = "338692774BBE";
    private static final String JOIN_THIS = "TRASHCAN12";

  private CollabrifyClient myClient;
  private CTXEditText broadcastText;
  private ArrayList<String> tags = new ArrayList<String>();
  private long sessionId;
  private int uid = 0;
  private String sessionName;
  private String password = "hello";

  // redundant but for the sake of readability
  private CollabrifySessionListener sessionListener = this;
  private CollabrifyListSessionsListener listSessionsListener = this;
  private CollabrifyBroadcastListener broadcastListener = this;
  private CollabrifyCreateSessionListener createSessionListener = this;
  private CollabrifyJoinSessionListener joinSessionListener = this;
  private CollabrifyLeaveSessionListener leaveSessionListener = this;

  @Override
  public void onReceiveEvent(long orderId, int submissionRegistrationId,
      String eventType, final byte[] data, long elapsed)
  {
    Utils.printMethodName(TAG);
    runOnUiThread(new Runnable()
    {

      @Override
      public void run()
      {
        Utils.printMethodName(TAG);
          InputCommand command = new InputCommand(data, uid);
          if(!command.isUserInputAction()) {
            command.redo(MainActivity.this, (CTXEditText) findViewById(R.id.BroadcastText));
            undoStack.adjustAllAfter(command.index, command.isInsert());
            redoStack.adjustAllAfter(command.index, command.isInsert());

            undoStack.push(command);
              updateUndoRedo();
          }
      }
    });
  }

  @Override
  public void onError(CollabrifyException e)
  {
    if(e instanceof CollabrifyUnrecoverableException)
    {
      //the client has been reset and we are no longer in a session
      onDisconnect();
    }
    Log.e(TAG, "error", e);
  }

  @Override
  public void onBroadcastDone(final byte[] event, long orderId, long srid){}

  @Override
  public void onSessionCreated(final CollabrifySession session)
  {

    sessionId = session.id();
    sessionName = session.name();
    runOnUiThread(new Runnable()
    {

      @Override
      public void run()
      {
        showToast("Session created: " +sessionName + ", id: " + session.id());

          Random rand = new Random();
          uid = rand.nextInt();

          CTXEditText editText = (CTXEditText) findViewById(R.id.BroadcastText);
          userEditOccurring = true;
          selfEditOccurring = true;
          editText.setText("");
          editText.setEnabled(true);
          userEditOccurring = false;
          selfEditOccurring = false;
      }
    });
  }

  @Override
  public void onSessionJoined(long maxOrderId, long baseFileSize)
  {
    sessionName = myClient.currentSession().name();
    sessionId = myClient.currentSession().id();
    runOnUiThread(new Runnable()
    {

      @Override
      public void run()
      {
          Random rand = new Random();
          uid = rand.nextInt();
          showToast("Session Joined");

          CTXEditText editText = (CTXEditText) findViewById(R.id.BroadcastText);
          userEditOccurring = true;
          selfEditOccurring = true;
          editText.setText("");
          editText.setEnabled(true);
          userEditOccurring = false;
          selfEditOccurring = false;
      }
    });
  }

  @Override
  public void onReceiveSessionList(List<CollabrifySession> sessionList)
  {
    if( sessionList.isEmpty() )
    {
      showToast("No session available");
      return;
    }
    displaySessionList(sessionList);
  }

  @Override
  public void onDisconnect()
  {
    runOnUiThread(new Runnable()
    {
      @Override
      public void run()
      {
        showToast("Left session");
          selfEditOccurring = true;
          userEditOccurring = true;
          lastCursorIndex = 0;
          lastText = "";
          undoStack.clear();
          redoStack.clear();
          CTXEditText editText = (CTXEditText) findViewById(R.id.BroadcastText);
          editText.setEnabled(false);
          editText.setText(R.string.startup_msg);
          selfEditOccurring = false;
          userEditOccurring = false;

      }
    });
  }


  public void doBroadcast(byte[] msg)
  {
    if( myClient != null && myClient.inSession() )
    {
      try
      {
        myClient.broadcast(msg, "lol", broadcastListener);
      }
      catch( CollabrifyException e )
      {
        onError(e);
      }
    }
  }

  public void doCreateSession()
  {
    try
    {
      Random rand = new Random();
      sessionName = "Test " + rand.nextInt(Integer.MAX_VALUE);
        sessionName = JOIN_THIS;
      myClient.createSession(sessionName, tags, password, 0,
          createSessionListener, sessionListener);
    }
    catch( CollabrifyException e )
    {
      onError(e);
    }
  }

  public void doJoinSession()
  {
    if( myClient.inSession() )
    {
      return;
    }
    try
    {
      myClient.requestSessionList(tags, listSessionsListener);
    }
    catch( Exception e )
    {
      Log.e(TAG, "error", e);
    }
  }

  public void doLeaveSession()
  {
    if( !myClient.inSession() )
    {
      return;
    }
    try
    {
      myClient.leaveSession(false, leaveSessionListener);
    }
    catch( CollabrifyException e )
    {
      onError(e);
    }
  }


  /*
   * (non-Javadoc)
   * 
   * @see android.app.Activity#onDestroy()
   */
  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    if( myClient != null && myClient.inSession() )
    {
      try
      {
        myClient.leaveSession(true, null);
      }
      catch( CollabrifyException e )
      {
        onError(e);
      }
    }
  }

  private void displaySessionList(final List<CollabrifySession> sessionList)
  {
    // Create a list of session names
    List<String> sessionNames = new ArrayList<String>();
    for( CollabrifySession s : sessionList )
    {
        if(s.name().compareTo(JOIN_THIS) == 0) {

            try
            {
                sessionId = s.id();
                myClient.joinSession(sessionId, password, joinSessionListener,
                        sessionListener);
            }
            catch( CollabrifyException e )
            {
                onError(e);
            }
            return;
        }
      sessionNames.add(s.name());
    }
/*
    // create a dialog to show the list of session names to the user
    final AlertDialog.Builder builder = new AlertDialog.Builder(
        MainActivity.this);
    builder.setTitle("Choose Session");
    builder.setItems(sessionNames.toArray(new String[sessionList.size()]),
        new DialogInterface.OnClickListener()
        {
          @Override
          public void onClick(DialogInterface dialog, int which)
          {
            // when the user chooses a session, join it
            try
            {
              sessionId = sessionList.get(which).id();
              myClient.joinSession(sessionId, password, joinSessionListener,
                  sessionListener);
            }
            catch( CollabrifyException e )
            {
              onError(e);
            }
          }
        });

    // display the dialog
    runOnUiThread(new Runnable()
    {
      @Override
      public void run()
      {
        builder.show();
      }
    });
    */
  }

  private void showToast(final String text)
  {
    runOnUiThread(new Runnable()
    {

      @Override
      public void run()
      {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onBaseFileUploadComplete(long baseFileSize)
  {
    // unused, this example does not use any base files
  }

  @Override
  public void onBaseFileReceived(File baseFile)
  {
    // unused, this example does not use any base files
  }

  @Override
  public void onParticipantJoined(CollabrifyParticipant p)
  {
    showToast(p.getDisplayName() + " joined");
  }

  @Override
  public void onParticipantLeft(CollabrifyParticipant p)
  {
    showToast(p.getDisplayName() + " left");
  }

  @Override
  public void onSessionEnd(long id)
  {
    // unused since we don't provide an interface to end the session
  }

  @Override
  public void onFurtherJoinsPrevented()
  {
    // unused since we don't provide an interface to prevent further joins
  }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        CTXEditText textField = (CTXEditText) findViewById(R.id.BroadcastText);
        menu_init(menu.findItem(R.id.action_undo), menu.findItem(R.id.action_redo));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CTXEditText textField = (CTXEditText) findViewById(R.id.BroadcastText);

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_undo) {
            undoActionInvoked();
            return true;
        } else if (id == R.id.action_redo) {
            redoActionInvoked();
            return true;
        } else if(id == R.id.action_create_session) {
            doCreateSession();
            return true;
        } else if(id == R.id.action_leave_session) {
            doLeaveSession();
            return true;
        } else if(id == R.id.action_join_session) {
            doJoinSession();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }








    public boolean selfEditOccurring =false;
    public boolean userEditOccurring = false;

    public int lastCursorIndex = 0;
    public String lastText = "";

    MenuItem menu_undo;
    MenuItem menu_redo;

    private CTXStack undoStack = new CTXStack();
    private CTXStack redoStack = new CTXStack();

    public void menu_init(MenuItem menu_undo, MenuItem menu_redo) {
        this.menu_undo = menu_undo;
        this.menu_redo = menu_redo;
    }


    //Called when user invokes the undo action
    public void undoActionInvoked() {
        InputCommand e;
        e = undoStack.pop();
        if(e != null) {
            e.undo(this, (CTXEditText) findViewById(R.id.BroadcastText));
            sendUndoMsg(e);
            redoStack.push(e);
        }

        updateUndoRedo();
    }

    //Called when user invokes the redo action
    public void redoActionInvoked() {
        InputCommand e;
        do {
            e = redoStack.pop();
            e.redo(this, (CTXEditText) findViewById(R.id.BroadcastText));
            sendRedoMsg(e);
            undoStack.push(e);
        } while (!e.isUserInputAction() && !redoStack.isEmpty());

        updateUndoRedo();
    }

    public void updateUndoRedo() {
        menu_undo.setEnabled(undoStack.getNumUserInputActions() != 0);
        menu_redo.setEnabled(redoStack.getNumUserInputActions() != 0);
    }


    /* Start Buffers Implementation */
    public void sendCursorMsg(int index) {
        CursorProto.CursorAction cursorAction = CursorProto.CursorAction.newBuilder()
                .setIndex(index)
                .setUid(uid)
                .build();
        doBroadcast(cursorAction.toByteArray());
        Log.d("BUFFER", cursorAction.toString());
    }

    public void sendInitInputMsg(InputCommand inputCommand) {
        InputProto.InputAction inputAction = inputCommand.getInitProto();
        doBroadcast(inputAction.toByteArray());
        Log.d("BUFFER", inputAction.toString());
    }

    public void sendUndoMsg(InputCommand c) {
        sendInputMsg(c, true);
    }

    public void sendRedoMsg(InputCommand c) {
        sendInputMsg(c, false);
    }

    public void sendInputMsg(InputCommand inputCommand, boolean isUndo) {
        InputProto.InputAction inputAction = inputCommand.getProto(isUndo);
        doBroadcast(inputAction.toByteArray());
        Log.d("BUFFER", inputAction.toString());
    }
    /* End Buffers Implementation */








    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastText = (CTXEditText) findViewById(R.id.BroadcastText);


        // Instantiate client object
        try
        {
            myClient = CollabrifyClient.newClient(this, GMAIL, DISPLAY_NAME,
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


        broadcastText.addTextChangedListener(new TextWatcher() {

            /* Start TextWatcher Implementation */
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!selfEditOccurring && undoStack != null) {
                    if(count != 0) {
                        Log.d("INFO", "INSERTED character: " + s.subSequence(start, start + count) + " (" + count + ")");
                        undoStack.push(new InputCommand(uid, s.subSequence(start, start + count), start, before, count, true, MainActivity.this, (CTXEditText) findViewById(R.id.BroadcastText)));
                    } else {
                        Log.d("INFO", "DELETED character: " + lastText.subSequence(start, start + before) + " (" + before + ")");
                        undoStack.push(new InputCommand(uid, lastText.subSequence(start, start + before), start, before, count, true, MainActivity.this, (CTXEditText) findViewById(R.id.BroadcastText)));
                    }

                    if(redoStack.getNumUserInputActions() > 0) {
                        redoStack.purgeUserInputActions();
                    }

                    userEditOccurring = true;
                    updateUndoRedo();
                }
                lastText = s.toString();
            }
    /* End TextWatcher Implementation */
        });
    }





}
