package com.example.yrchoi.yurist.Popup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.yrchoi.yurist.Picker.NumberPickerActivity;
import com.example.yrchoi.yurist.R;

/**
 * Created by neozen on 2018-04-26.
 */

public class Popup_Picker {

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    private TextView mBtnCancel;
    private TextView mBtnOk;

    private int mYear;
    private int mMonth;
    private int mDay;

    private int mHourOfDay;
    private int mMinute;

    private boolean mIgnoreEvent = false;
    private int TIME_PICKER_INTERVAL = 5;
    private PopupWindow m_PopupWindow;

    public void createPopupWindow(final Context context, View.OnClickListener leftListener, View.OnClickListener rightListener, String date, String time) {

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        ((Activity) context).getWindow().setAttributes(lpWindow);

        View popUpView  = LayoutInflater.from(context).inflate(R.layout.popup_date_time_dialog, null);

        ((Activity) context).setContentView(popUpView);

        m_PopupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        m_PopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        m_PopupWindow.showAtLocation(popUpView, Gravity.CENTER, 0, 0);

        mDatePicker = popUpView.findViewById(R.id.date_picker);
        mTimePicker = popUpView.findViewById(R.id.time_picker);
        mBtnCancel = popUpView.findViewById(R.id.btn_cancel);
        mBtnOk = popUpView.findViewById(R.id.btn_ok);

        mDatePicker.init(Integer.parseInt(date.replace("-", "").substring(0, 4)), Integer.parseInt(date.replace("-", "").substring(4, 6)) - 1, Integer.parseInt(date.replace("-", "").substring(6, 8)), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {

            }
        });

        mTimePicker.setIs24HourView(true);
        mTimePicker.setCurrentHour(Integer.parseInt(time.substring(0, 2)));
        mTimePicker.setCurrentMinute(Integer.parseInt(time.substring(3, 5)));
        mTimePicker.clearFocus();

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                    /* 2018.04.26 - gbkim
                    * 분 5분 단위로 변경
                    * 참조 : https://stackoverflow.com/questions/2580216/android-timepicker-minutes-to-15
                    *        첫 번째 답변 */

                if (mIgnoreEvent)
                    return;
                if (minute % TIME_PICKER_INTERVAL != 0) {
                    int minuteFloor = minute - (minute % TIME_PICKER_INTERVAL);
                    minute = minuteFloor + (minute == minuteFloor + 1 ? TIME_PICKER_INTERVAL : 0);
                    if (minute == 60)
                        minute = 0;
                    mIgnoreEvent = true;
                    timePicker.setCurrentMinute(minute);
                    mIgnoreEvent = false;
                }
                    /*================================================================================*/
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 취소
                m_PopupWindow.dismiss();
            }
        });
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 날짜 저장
                mYear = mDatePicker.getYear();
                mMonth = mDatePicker.getMonth();
                mDay = mDatePicker.getDayOfMonth();

                // 시간 저장
                mHourOfDay = mTimePicker.getCurrentHour();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mMinute = mTimePicker.getMinute();
                } else {
                    mMinute = mTimePicker.getCurrentMinute();
                }
                Log.d("GBKIM", "Timepicker min : " + mMinute);

                Intent intent = new Intent(context, NumberPickerActivity.class);
                context.startActivity(intent);

                NumberPickerActivity.UpdateDateTime(true);

                m_PopupWindow.dismiss();
            }
        });
    }
}

