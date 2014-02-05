package edu.umich.imlc.collabrify.collabrify_dummy_app;

import java.util.Iterator;
import java.util.Vector;

public class CTXStack extends Vector<InputCommand> {
    private int numUserInputActions;

    public CTXStack() {
        super();
        numUserInputActions = 0;
    }

    public void push(InputCommand e) {
        if(e.isUserInputAction()) {
            numUserInputActions++;
        }
        add(e);
    }

    public InputCommand pop() {
        int i = super.size();
        do {
            --i;
        } while (i >= 0 && !elementAt(i).isUserInputAction());

        if(i < 0) {
            return null;
        }

        InputCommand e = remove(i);
        numUserInputActions--;
        return e;
    }

    //Must be used only for redoStack otherwise terrible things may happen
    public void purgeUserInputActions() {
        Iterator<InputCommand> iter = this.iterator();
        while(iter.hasNext()) {
            if(iter.next().isUserInputAction()) {
                iter.remove();
            }
        }
        numUserInputActions = 0;
    }

    public void adjustAllAfter(int index, boolean isInsert) {
        int diff = -1;
        if(isInsert) diff = 1;

        for(InputCommand e : this) {
            if(e.index >= index) {
                e.index += diff;
            }
        }
    }

    public int getNumUserInputActions() {
        return numUserInputActions;
    }
}