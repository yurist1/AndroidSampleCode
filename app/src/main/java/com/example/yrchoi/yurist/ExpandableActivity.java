package com.example.yrchoi.yurist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yrchoi.yurist.Adapter.Adapter_Expandable;
import com.example.yrchoi.yurist.VO.VO_MobileOS;
import com.example.yrchoi.yurist.VO.VO_Phone;

import java.util.ArrayList;

public class ExpandableActivity extends AppCompatActivity {

    private RecyclerView recycerView;
    private ArrayList<VO_MobileOS> mobileOSes;
    private Adapter_Expandable adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        recycerView = findViewById(R.id.recyclerView);
        mobileOSes = new ArrayList<>();
        
        setData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycerView.setLayoutManager(layoutManager);

        adapter = new Adapter_Expandable(this, mobileOSes);
        recycerView.setAdapter(adapter);

    }

    private void setData() {

        ArrayList<VO_Phone> iphones = new ArrayList<>();

        iphones.add(new VO_Phone("iphone 4"));
        iphones.add(new VO_Phone("iphone 5"));
        iphones.add(new VO_Phone("iphone 6"));
        iphones.add(new VO_Phone("iphone 7"));
        iphones.add(new VO_Phone("iphone 8"));
        iphones.add(new VO_Phone("iphone X"));

        ArrayList<VO_Phone> nexus = new ArrayList<>();

        nexus.add(new VO_Phone("Nexus 1"));
        nexus.add(new VO_Phone("Nexus 2"));
        nexus.add(new VO_Phone("Nexus 3"));
        nexus.add(new VO_Phone("Nexus 4"));
        nexus.add(new VO_Phone("Nexus 5"));
        nexus.add(new VO_Phone("Nexus 6"));

        ArrayList<VO_Phone> windowPhones = new ArrayList<>();

        windowPhones.add(new VO_Phone("Nokia Lumia 800"));
        windowPhones.add(new VO_Phone("Nokia Lumia 710"));
        windowPhones.add(new VO_Phone("Nokia Lumia 900"));
        windowPhones.add(new VO_Phone("Nokia Lumia 610"));
        windowPhones.add(new VO_Phone("Nokia Lumia 510"));
        windowPhones.add(new VO_Phone("Nokia Lumia 820"));
        windowPhones.add(new VO_Phone("Nokia Lumia 920"));

        mobileOSes.add(new VO_MobileOS("IOS", iphones));
        mobileOSes.add(new VO_MobileOS("Android", nexus));

    }
}
