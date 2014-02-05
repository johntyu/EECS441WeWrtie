package com.eecs.collab;


import android.util.Log;

public class InputCommand {
    private CharSequence c;
    private int index;
    private int newLength;
    private int oldLength;
    private boolean userInputAction;

    public InputCommand(CharSequence c, int index, int oldLength, int newLength, boolean userInputAction, CTXEditText editText) {
        this.c = c;
        this.index = index;
        this.newLength = newLength;
        this.oldLength = oldLength;
        this.userInputAction = userInputAction;

        Log.d("CREATE", "InputCommand Create: New" + newLength + " Old" + oldLength);

        editText.sendInitInputMsg(this);
    }

    public void undo(CTXEditText editText) {
        editText.selfEditOccurring = true;
        String text = editText.getText().toString();
        String newText = "";
        if(isInsert()) {
            if(index != 0) {
                newText += text.subSequence(0, index);
            }
            if(index + newLength != text.length()) {
                newText += text.subSequence(index + newLength, text.length());
            }

            int newCursorPosition = editText.getSelectionStart();
            if(newCursorPosition > index) {
                newCursorPosition -=  newLength;
            }
            editText.setText(newText);
            editText.setSelection(newCursorPosition);
        } else {
            if(index != 0) {
                newText += text.subSequence(0, index);
            }
            newText += c.toString();
            if(index != text.length()) {
                newText += text.subSequence(index, text.length());
            }

            int newCursorPosition = editText.getSelectionStart();
            if(editText.getSelectionStart() > index) {
                newCursorPosition += + oldLength;
            }
            Log.d("UNDO", "Text Undo - New Cursor Position: " + newCursorPosition);
            editText.setText(newText);
            editText.setSelection(newCursorPosition);
        }
        editText.selfEditOccurring = false;
    }

    public void redo(CTXEditText editText) {
        editText.selfEditOccurring = true;
        String text = editText.getText().toString();
        String newText = "";
        if(isInsert()) {
            if(index != 0) {
                newText += text.subSequence(0, index);
            }
            newText += c.toString();
            if(index != text.length()) {
                newText += text.subSequence(index, text.length());
            }

            int newCursorPosition = editText.getSelectionStart();
            if(newCursorPosition > index) {
                newCursorPosition += editText.getSelectionStart() + newLength;
            }
            editText.setText(newText);
            editText.setSelection(newCursorPosition);
        } else {
            if(index != 0) {
                newText += text.subSequence(0, index);
            }
            if(index + oldLength != text.length()) {
                newText += text.subSequence(index + oldLength, text.length());
            }

            int newCursorPosition = editText.getSelectionStart();
            if(newCursorPosition > index) {
                newCursorPosition -= oldLength;
            }
            editText.setText(newText);
            editText.setSelection(newCursorPosition);
        }
        editText.selfEditOccurring = false;
    }

    public InputProto.InputAction getProto(boolean undo) {
        return InputProto.InputAction.newBuilder()
                .setC(c.toString())
                .setNewLength(newLength)
                .setOldLength(oldLength)
                .setInit(true)
                .setUndo(undo)
                .build();
    }

    public boolean isUserInputAction() {return userInputAction;}
    public boolean isInsert() {return (oldLength == 0);}
}