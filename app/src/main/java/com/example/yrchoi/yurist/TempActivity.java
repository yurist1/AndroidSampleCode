package com.example.yrchoi.yurist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class TempActivity extends AppCompatActivity {

    private Button btn_bp;
    private Button btn_hp;
    private Button btn_mp;
    private TextView tv_bp;
    private TextView tv_hp;
    private TextView tv_mp;
    private int btn_position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        btn_bp = findViewById(R.id.btn_bp);
        btn_hp = findViewById(R.id.btn_hp);
        btn_mp = findViewById(R.id.btn_mp);
        tv_bp = findViewById(R.id.tv_bp);
        tv_hp = findViewById(R.id.tv_hp);
        tv_mp = findViewById(R.id.tv_mp);

        evet();
    }

    private void evet() {
        btn_bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_position = 0;

                numPicker(btn_bp, btn_position, "20", "BP");
            }
        });

        tv_mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_mp.setBackgroundColor(R.drawable.select_textview_ch_bg);

            }
        });
    }

    private void numPicker(final Button btn, int btn_position, String scale, String title) {
        Dialog dialog = new Dialog(TempActivity.this);

        dialog.setTitle("Title");
        dialog.setContentView(R.layout.number_dialog);

        final NumberPicker numberPicker = dialog.findViewById(R.id.numberPicker1);
        Button btn_set = dialog.findViewById(R.id.btn_set);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);
        numberPicker.setValue(Integer.parseInt(scale));
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                btn.setText(String.valueOf(numberPicker.getValue()));
            }
        });

        dialog.show();
    }
}
