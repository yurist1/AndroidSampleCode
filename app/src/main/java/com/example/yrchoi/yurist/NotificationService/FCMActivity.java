package com.example.yrchoi.yurist.NotificationService;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yrchoi.yurist.Common.HistoryBack;
import com.example.yrchoi.yurist.R;

import java.util.ArrayList;

public class FCMActivity extends AppCompatActivity {

    public static int BACK_TYPE_NORMAL = 0;

    public int FRAGMENT_MAIN = 3;
    public int FRAGMENT_ALARM = 38;

    private Fragment startFragment;
    private FragmentMain fragment_main;
    private LoginFragment loginFragment;

    private ArrayList<HistoryBack> listBack;

    private String TAG_FRAGMENT_ALARM = "fragment_alarm";
    private String TAG_FRAGMENT_MAIN = "fragment_main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcm);

        fragment_main = new FragmentMain();

        listBack = new ArrayList<>();

        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null && LoginFragment.login_check == loginFragment.LOGIN_OK) {
            Bundle extras = intent.getExtras();
            Log.i("Extras", extras.toString());

            startFragment = new AlarmFragment();
        } else {
            startFragment = new LoginFragment();
        }

        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.main_container, startFragment);
        transaction.commit();
    }

    public void onFragmentChanged(int index, Fragment target_fragment, int back_type) {
        if (index == FRAGMENT_ALARM) {
            setHistoryBack(index, target_fragment, TAG_FRAGMENT_ALARM, back_type);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, target_fragment, TAG_FRAGMENT_ALARM).addToBackStack(null).commit();
        } else if (index == FRAGMENT_MAIN) {
            setHistoryBack(index, target_fragment, TAG_FRAGMENT_MAIN, back_type);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, target_fragment, TAG_FRAGMENT_MAIN).addToBackStack(null).commit();
        }
    }

    private void setHistoryBack(int index, Fragment target_fragment, String frag_tag, int back_type) {
        HistoryBack hsBack = new HistoryBack();
        hsBack.setFragIndex(index);
        hsBack.setFragment(target_fragment);
        hsBack.setFragTag(frag_tag);

        if (listBack.size() > 1) {
            if (listBack.get(listBack.size() - 1).getFragIndex() != index) {
                if (back_type == BACK_TYPE_NORMAL) {
                    listBack.add(hsBack);
                }
            }

        } else {
            if (back_type == BACK_TYPE_NORMAL) {
                listBack.add(hsBack);
            }
        }
    }
}
