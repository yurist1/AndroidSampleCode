package com.example.yrchoi.yurist.NotificationService;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.yrchoi.yurist.R;


public class FragmentMain extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private View mainView;
    private ImageView bottom_alarm;
    private FCMActivity fcmActivity;
    private AlarmFragment fragmentSet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mainView = view;
        fragmentSet = new AlarmFragment();

        bottom_alarm = view.findViewById(R.id.image_main_bottom_alarm);

        bottom_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fcmActivity = (FCMActivity) getActivity();
                fcmActivity.onFragmentChanged(fcmActivity.FRAGMENT_ALARM, fragmentSet, FCMActivity.BACK_TYPE_NORMAL);

            }
        });

        getFCMExtras();

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


    }

    public void setFragmentSet(View.OnClickListener onClickListener) {
//        bottom_alarm = onClickListener;
    }

    private void getFCMExtras() {
        String extras = getActivity().getIntent().getStringExtra("KEY");
        fcmActivity = (FCMActivity) getActivity();

        if (extras != null) {
            fcmActivity.onFragmentChanged(fcmActivity.FRAGMENT_ALARM, fragmentSet, FCMActivity.BACK_TYPE_NORMAL);

            extras = null;
        }
    }
}
