package com.example.yrchoi.yurist.CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class NotoButtonBold extends AppCompatButton {

    public NotoButtonBold(Context context) {
        super(context);
        setType(context);
    }

    public NotoButtonBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setType(context);
    }

    public NotoButtonBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setType(context);
    }

    private void setType(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansKR-Bold-Hestia.otf"));
    }
}
