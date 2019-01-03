package com.example.gbkim.gubonny;

import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gbkim on 2017-12-18.
 */

class CMotionEvent {
    static final Map<Integer, String> actionSctionEx = new HashMap<>();
    static {
        actionSctionEx.put(MotionEvent.ACTION_DOWN, "DOWN");
        actionSctionEx.put(MotionEvent.ACTION_UP, "UP");
        actionSctionEx.put(MotionEvent.ACTION_POINTER_DOWN, "PNTR DOWN");
        actionSctionEx.put(MotionEvent.ACTION_POINTER_UP, "PNTR UP");
        actionSctionEx.put(MotionEvent.ACTION_MOVE, "MOVE");
    }

    public static String getActionString(int actionMasked) {
        return actionSctionEx.get(actionMasked);
    }

}
