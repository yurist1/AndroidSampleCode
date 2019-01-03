package com.example.gbkim.gubonny.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by neozen on 2018-04-04.
 */

public class KeyboardUtils {

    public static void showKeyboard (Activity activity, EditText editText) {
        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        final EditText target_edit = editText;
        editText.requestFocus();
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                inputMethodManager.showSoftInput(target_edit, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 30);

    }
}
