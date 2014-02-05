package edu.umich.imlc.collabrify.collabrify_dummy_app;


import android.util.Log;

import com.google.protobuf.InvalidProtocolBufferException;

public class InputCommand {
    private CharSequence c;
    public int index;
    private boolean isInsert;
    private boolean userInputAction;
    private int uid;

    public InputCommand(int uid, CharSequence c, int index, int oldLength, int newLength, boolean userInputAction, MainActivity activity, CTXEditText editText) {
        this.c = c;
        this.index = index;
        this.isInsert = (newLength != 0);
        this.userInputAction = userInputAction;
        this.uid = uid;

        Log.d("CREATE", "InputCommand Create: New" + newLength + " Old" + oldLength);

        activity.sendInitInputMsg(this);
    }

    public InputCommand(byte[] stream, int uid) {
        try {
            InputProto.InputAction inputAction = InputProto.InputAction.parseFrom(stream);
            Log.d("RECEIVE", inputAction.toString());
            this.c = inputAction.getC();
            this.index = inputAction.getIndex();
            this.isInsert = inputAction.getIsInsert();
            this.uid = inputAction.getUid();
            this.userInputAction = (inputAction.getUid() == uid);
        } catch(InvalidProtocolBufferException e) {
            //some fail code
        }
    }

    public void undo(MainActivity activity, CTXEditText editText) {
        activity.selfEditOccurring = true;
        String text = editText.getText().toString();
        String newText = "";
        int currentSelection = editText.getSelectionStart();
        if(isInsert()) {
            if(index != 0) {
                newText += text.subSequence(0, index);
            }
            if(index + 1 != text.length()) {
                newText += text.subSequence(index + 1, text.length());
            }

            editText.setText(newText);
            if(userInputAction) {
                editText.setSelection(index);
            } else if(currentSelection >= index) {
                editText.setSelection(currentSelection - 1);
            } else {
                editText.setSelection(currentSelection);
            }
        } else {
            if(index != 0) {
                newText += text.subSequence(0, index);
            }
            newText += c.toString();
            if(index != text.length()) {
                newText += text.subSequence(index, text.length());
            }

            editText.setText(newText);
            if(userInputAction) {
                editText.setSelection(index+1);
            } else if(currentSelection >= index) {
                editText.setSelection(currentSelection+1);
            } else {
                editText.setSelection(currentSelection);
            }
        }
        activity.selfEditOccurring = false;
    }

    public void redo(MainActivity activity, CTXEditText editText) {
        activity.selfEditOccurring = true;
        String text = editText.getText().toString();
        String newText = "";
        int currentSelection = editText.getSelectionStart();
        if(isInsert()) {
            if(index != 0) {
                newText += text.subSequence(0, index);
            }
            newText += c.toString();
            if(index != text.length()) {
                newText += text.subSequence(index, text.length());
            }

            editText.setText(newText);
            if(userInputAction) {
                editText.setSelection(index+1);
            } else if(currentSelection >= index) {
                editText.setSelection(currentSelection+1);
            } else {
                editText.setSelection(currentSelection);
            }
        } else {
            if(index != 0) {
                newText += text.subSequence(0, index);
            }
            if(index + 1 != text.length()) {
                newText += text.subSequence(index + 1, text.length());
            }

            editText.setText(newText);
            if(userInputAction) {
                editText.setSelection(index+1);
            } else if(currentSelection >= index) {
                editText.setSelection(currentSelection-1);
            } else {
                editText.setSelection(currentSelection);
            }
        }
        activity.selfEditOccurring = false;
    }

    public InputProto.InputAction getInitProto() {
        return InputProto.InputAction.newBuilder()
                .setIndex(index)
                .setC(c.toString())
                .setIsInsert(isInsert)
                .setUid(uid)
                .build();
    }

    public InputProto.InputAction getProto(boolean undo) {
        return InputProto.InputAction.newBuilder()
                .setIndex(index)
                .setC(c.toString())
                .setIsInsert(isInsert ^ undo)
                .setUid(uid)
                .build();
    }

    public boolean isUserInputAction() {return userInputAction;}
    public boolean isInsert() {return isInsert;}
}