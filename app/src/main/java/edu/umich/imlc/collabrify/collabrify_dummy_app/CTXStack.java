package com.eecs.collab;

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
        InputCommand e = remove(super.size() - 1);
        if(e.isUserInputAction()) {
            numUserInputActions--;
        }
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

    public int getNumUserInputActions() {
        return numUserInputActions;
    }
}