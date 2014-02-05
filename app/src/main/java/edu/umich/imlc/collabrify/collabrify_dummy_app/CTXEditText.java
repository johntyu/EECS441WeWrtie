package com.eecs.collab;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.content.Context;
import android.util.AttributeSet;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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

/**
 * Created by renjay on 1/22/14.
 */
public class CTXEditText extends EditText implements TextWatcher {
    public boolean selfEditOccurring =false;
    public boolean userEditOccurring = false;

    public int lastCursorIndex = 0;
    public String lastText;

    MenuItem menu_undo;
    MenuItem menu_redo;

    private CTXStack undoStack = new CTXStack();
    private CTXStack redoStack = new CTXStack();

    public CTXEditText(Context context) {
        super(context);
    }

    public CTXEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CTXEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void menu_init(MenuItem menu_undo, MenuItem menu_redo) {
        this.menu_undo = menu_undo;
        this.menu_redo = menu_redo;
    }

    //Called when user invokes the undo action
    public void undoActionInvoked() {
        InputCommand e;
        do {
            e = undoStack.pop();
            e.undo(this);
            sendUndoMsg(e);
            redoStack.push(e);
        } while(!e.isUserInputAction() && !undoStack.isEmpty());

        updateUndoRedo();
    }

    //Called when user invokes the redo action
    public void redoActionInvoked() {
        InputCommand e;
        do {
            e = redoStack.pop();
            e.redo(this);
            sendRedoMsg(e);
            undoStack.push(e);
        } while (!e.isUserInputAction() && !redoStack.isEmpty());

        updateUndoRedo();
    }

    public void updateUndoRedo() {
        menu_undo.setEnabled(undoStack.getNumUserInputActions() != 0);
        menu_redo.setEnabled(redoStack.getNumUserInputActions() != 0);
    }

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
                undoStack.push(new InputCommand(s.subSequence(start, start + count), start, before, count, true, this));
            } else {
                Log.d("INFO", "DELETED character: " + lastText.subSequence(start, start + before) + " (" + before + ")");
                undoStack.push(new InputCommand(lastText.subSequence(start, start + before), start, before, count, true, this));
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


    /* Start Buffers Implementation */
    public void sendCursorMsg(int index) {
        CursorProto.CursorAction cursorAction = CursorProto.CursorAction.newBuilder()
                .setIndex(index)
                .build();
        Log.d("BUFFER", cursorAction.toString());
    }

    public void sendInitInputMsg(InputCommand inputCommand) {
        InputProto.InputAction inputAction = inputCommand.getProto(false);
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
        Log.d("BUFFER", inputAction.toString());
    }
    /* End Buffers Implementation */


    @Override
    public void setSelection(int index) {
        super.setSelection(index);
        lastCursorIndex = index;
    }

    @Override
    public void onSelectionChanged(int start, int end) {
        if(end - start != 0) {
            setSelection(start);
        }

        if(!selfEditOccurring && !userEditOccurring && undoStack != null) {
            sendCursorMsg(start);
            Log.d("INFO", "Cursor change: " + start);
        }
        lastCursorIndex = start;
        userEditOccurring = false;
    }

}
