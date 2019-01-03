package com.example.gbkim.gubonny;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.gbkim.gubonny.Adapter.Adapter_Filter;
import com.example.gbkim.gubonny.VO.Info_Recent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FilterActivity extends AppCompatActivity {

    private RecyclerView rv_filter;
    private EditText et_filter;
    private LinearLayoutManager layoutManager;
    private Adapter_Filter adapter_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        rv_filter = findViewById(R.id.rv_filter);
        et_filter = findViewById(R.id.et_filter);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        rv_filter.setHasFixedSize(true);
        rv_filter.setLayoutManager(layoutManager);

        List<Info_Recent> potionList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            Info_Recent info_recent = new Info_Recent(i + "", "");
            potionList.add(info_recent);
        }

        for (int i = 0; i < 30; i++) {
            Info_Recent info_recent = new Info_Recent(i + "" + i, "");
            potionList.add(info_recent);
        }

        for (int i = 0; i < 30; i++) {
            Info_Recent info_recent = new Info_Recent(i + "" + i + "" + i, "");
            potionList.add(info_recent);
        }

        adapter_filter = new Adapter_Filter(this, potionList);
        rv_filter.setAdapter(adapter_filter);

        et_filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = et_filter.getText().toString().toLowerCase(Locale.getDefault());
                adapter_filter.filter(text);
            }
        });
    }
}
