package com.example.gbkim.gubonny.CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class NotoButtonRegular extends AppCompatButton {

    public NotoButtonRegular(Context context) {
        super(context);
        setType(context);
    }

    public NotoButtonRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setType(context);
    }

    public NotoButtonRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setType(context);
    }

    private void setType(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansKR-Regular-Hestia.otf"));
    }
}
