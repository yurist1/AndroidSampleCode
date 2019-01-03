package com.example.yrchoi.yurist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.yrchoi.yurist.Adapter.Adapter_Change_Bg;
import com.example.yrchoi.yurist.Listener.Listener_Change_Bg;

import java.util.ArrayList;

public class ChangeBgActivity extends AppCompatActivity implements Listener_Change_Bg {

    private RecyclerView recyclerview;
    private Adapter_Change_Bg adapter;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bg);

        recyclerview = findViewById(R.id.rv_change_Bg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

        items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add("TextView_" + i);
        }

        adapter = new Adapter_Change_Bg(items);
        recyclerview.setAdapter(adapter);

        adapter.setClickListener(this);
    }

    @Override
    public void itemClicked(View view, int position) {
        Toast.makeText(getApplicationContext(), items.get(position), Toast.LENGTH_SHORT).show();
    }
}
