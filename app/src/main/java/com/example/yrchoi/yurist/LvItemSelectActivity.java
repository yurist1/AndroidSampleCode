package com.example.gbkim.gubonny;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LvItemSelectActivity extends AppCompatActivity {

    private ListView list_view;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv_item_select);

        list_view = findViewById(R.id.listView);
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");

        adapter = new ArrayAdapter<>(this, R.layout.custom_listitem, list);
        list_view.setAdapter(adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
            }
        });

    }
}
