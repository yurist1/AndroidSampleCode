package com.example.yrchoi.yurist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.yrchoi.yurist.Receiver.NetworkUtil;

public class NetworkReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_receiver);

        NetworkUtil networkUtil = new NetworkUtil();

        String status = networkUtil.getConnectivityStatusString(getApplicationContext());

        Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
    }
}
