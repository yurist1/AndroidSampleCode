package com.example.yrchoi.yurist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ArrayList<String> listItem = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listItem);

        // ListView 어댑터 설정
        ((ListView)findViewById(R.id.listView)).setAdapter(adapter);

        // 버튼 이벤트
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etMsg = findViewById(R.id.etMsg);
                listItem.add(etMsg.getText().toString());
                etMsg.setText("");
                adapter.notifyDataSetChanged(); // 어댑터 갱신
            }
        });

    }
}
