package com.example.yrchoi.yurist.CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class NotoTextViewRegular extends AppCompatTextView {

    public NotoTextViewRegular(Context context) {
        super(context);
        setType(context);
    }

    public NotoTextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setType(context);
    }

    public NotoTextViewRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setType(context);
    }

    private void setType(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansKR-Regular-Hestia.otf"));
    }
}
