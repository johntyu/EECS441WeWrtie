package edu.umich.imlc.collabrify.collabrify_dummy_app;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by renjay on 1/22/14.
 */
public class CTXEditText extends EditText {

    public CTXEditText(Context context) {
        super(context);
    }

    public CTXEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CTXEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }




    @Override
    public void setSelection(int index) {
        super.setSelection(index);
    }

    @Override
    public void onSelectionChanged(int start, int end) {
        if(end - start != 0) {
            setSelection(start);
        }
/*
        if(!selfEditOccurring && !userEditOccurring && undoStack != null) {
            sendCursorMsg(start);
            Log.d("INFO", "Cursor change: " + start);
        }
        userEditOccurring = false;
        */
    }

}
