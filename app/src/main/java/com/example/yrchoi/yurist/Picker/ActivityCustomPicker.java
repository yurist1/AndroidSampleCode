package com.example.yrchoi.yurist.Picker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yrchoi.yurist.Popup.Popup_Picker;
import com.example.yrchoi.yurist.R;

public class ActivityCustomPicker extends AppCompatActivity implements View.OnClickListener {

    private Button btn_custom_picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_picker);

        btn_custom_picker = findViewById(R.id.btn_custom_picker);

        btn_custom_picker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_custom_picker:
                Popup_Picker popup_picker = new Popup_Picker();

        }
    }
}
