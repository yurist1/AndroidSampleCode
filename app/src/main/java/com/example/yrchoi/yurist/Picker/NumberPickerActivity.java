package com.example.gbkim.gubonny.Picker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.gbkim.gubonny.Popup.Popup_Picker;
import com.example.gbkim.gubonny.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NumberPickerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_picker;
    private static Button btn_picker_date;
    private static Button btn_picker_time;

    private CustomDialog m_custdialog;

    private View.OnClickListener leftListener = null;
    private View.OnClickListener rightListener = null;

    private static int m_Year;
    private static int m_Month;
    private static int m_Day;

    private static int m_Hour;
    private static int m_Minute;
    private String getDate;
    private String getTime;

    private boolean mIgnoreEvent = false;
    private int TIME_PICKER_INTERVAL = 5;
    private Popup_Picker popupPicker;
    private NumberPicker np_picker_year;
    private NumberPicker np_picker_month;
    private NumberPicker np_picker_day;
    private NumberPicker np_picker_hour;
    private NumberPicker np_picker_min;
    private TimePicker tp_picker;
    private DatePicker dp_picker;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);

        btn_picker = findViewById(R.id.btn_picker);
        btn_picker_date = findViewById(R.id.btn_picker_date);
        btn_picker_time = findViewById(R.id.btn_picker_time);
//        np_picker_year = findViewById(R.id.np_picker_year);
//        np_picker_month = findViewById(R.id.np_picker_month);
//        np_picker_day = findViewById(R.id.np_picker_day);
//        np_picker_hour = findViewById(R.id.np_picker_hour);
//        np_picker_min = findViewById(R.id.np_picker_min);

//        calendar_today_date();

//        btn_picker_date.setText(getDate);
//        btn_picker_time.setText(getTime);
//
//        // 시간 선택 다이얼로그
//        final TimePickerDialog.OnTimeSetListener mTimeSetLisner = new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
//                m_Hour = hourOfDay;
//                m_Minute = minute;
//
//                UpdateDateTime(true);
//            }
//        };
//
//        np_picker_year.setMaxValue(2020);
//        np_picker_year.setMinValue(2017);

        btn_picker_date.setOnClickListener(this);
        btn_picker_time.setOnClickListener(this);
        btn_picker.setOnClickListener(this);


//
//        // 시간선택 버튼 클릭
//        btn_picker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mIgnoreEvent = true;
//
////                new TimePickerDialog(getApplicationContext(), 2, mTimeSetLisner, m_Hour, m_Minute, false).show();
//                popupPicker = new Popup_Picker();
//                popupPicker.createPopupWindow(getApplicationContext(), leftListener, rightListener, btn_picker_date.getText().toString(), btn_picker_time.getText().toString());
////
////                m_custdialog = new CustomDialog(getApplicationContext(), leftListener, rightListener, btn_picker_date.getText().toString(), btn_picker_time.getText().toString());
////                m_custdialog.show();
//            }
//        });
//
//        // 날짜&시간 다이얼로그 닫기
//        leftListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 취소
//                m_custdialog.dismiss();
//            }
//        };
//
//        // 날짜&시간 변경.
//        rightListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 날짜 저장
//                m_custdialog.mDatePicker.clearFocus();
//                m_Year = m_custdialog.mDatePicker.getYear();
//                m_Month = m_custdialog.mDatePicker.getMonth();
//                m_Day = m_custdialog.mDatePicker.getDayOfMonth();
//
//                // 시간 저장
//                m_custdialog.mTimePicker.clearFocus();
//                m_Hour = m_custdialog.mTimePicker.getCurrentHour();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    m_Minute = m_custdialog.mTimePicker.getMinute();
//                } else {
//                    m_Minute = m_custdialog.mTimePicker.getCurrentMinute();
//                }
//
//                UpdateDateTime(true);
//
//                m_custdialog.dismiss();
//            }
//        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_picker:
                break;

            case R.id.btn_picker_date:
                // Process to get Current Date
                c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Display Selected date in EditText
                        btn_picker_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);

                dpd.show();
                break;

            case R.id.btn_picker_time:
                // Process to get Current Time
                c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Display Selected time in EditText
                        btn_picker_time.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);

                tpd.show();
                break;


        }

    }

    private void calendar_today_date() {
        Calendar cal = new GregorianCalendar();
        m_Year = cal.get(Calendar.YEAR);
        m_Month = cal.get(Calendar.MONTH);
        m_Day = cal.get(Calendar.DAY_OF_MONTH);

        m_Hour = cal.get(Calendar.HOUR_OF_DAY);
        m_Minute = cal.get(Calendar.MINUTE);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat date_now = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time_now = new SimpleDateFormat("HH:mm");
        getDate = date_now.format(date);
        getTime = time_now.format(date);
    }

    public static void UpdateDateTime(boolean time_flag) {
        btn_picker_date.setText(String.format("%d-%02d-%02d", m_Year, m_Month + 1, m_Day));
        if (time_flag)
            btn_picker_time.setText(String.format("%02d:%02d", m_Hour, m_Minute));
        else
            btn_picker_time.setText(String.format("%02d", m_Hour) + ":00");
    }

    private class CustomDialog extends Dialog {
        private DatePicker mDatePicker;
        private TimePicker mTimePicker;
        private TextView mBtnCancel;
        private TextView mBtnOk;

        private String mDate;
        private String mTime;

        private int mYear;
        private int mMonth;
        private int mDay;

        private int mHourOfDay;
        private int mMinute;

        private View.OnClickListener mLeftClickListener;
        private View.OnClickListener mRightClickListener;


        public CustomDialog(Context context, View.OnClickListener leftListener, View.OnClickListener rightListener, String date, String time) {
            super(context, android.R.style.Theme_Translucent_NoTitleBar);

            this.mDate = date;
            this.mTime = time;
            this.mLeftClickListener = leftListener;
            this.mRightClickListener = rightListener;

            mIgnoreEvent = false;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
            lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            lpWindow.dimAmount = 0.8f;
            getWindow().setAttributes(lpWindow);

            setContentView(R.layout.popup_date_time_dialog);

            mDatePicker = findViewById(R.id.date_picker);
            mTimePicker = findViewById(R.id.time_picker);
            mBtnCancel = findViewById(R.id.btn_cancel);
            mBtnOk = findViewById(R.id.btn_ok);

            mDatePicker.init(Integer.parseInt(mDate.replace("-", "").substring(0, 4)), Integer.parseInt(mDate.replace("-", "").substring(4, 6)) - 1, Integer.parseInt(mDate.replace("-", "").substring(6, 8)), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int day) {

                }
            });

            int hour = Integer.parseInt(mTime.substring(0, 2));
            int minute = Integer.parseInt(mTime.substring(3, 5));

            mTimePicker.setIs24HourView(true);
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);


            mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                    /* 2018.04.26 - gbkim
                    * 분 5분 단위로 변경
                    * 참조 : https://stackoverflow.com/questions/2580216/android-timepicker-minutes-to-15
                    *        첫 번째 답변 */
                    int before_minute = minute;
                    int after_minute = 0;
                    if (mIgnoreEvent) // 확인 버튼 눌렀을 때
                        return;
                    if (minute % TIME_PICKER_INTERVAL != 0) { // +/- 버튼 눌렀을 때
                        int minuteFloor = minute - (minute % TIME_PICKER_INTERVAL);
                        minute = minuteFloor + (minute == minuteFloor + 1 ? TIME_PICKER_INTERVAL : 0);
                        if (minute == 60) {
                            after_minute = minute;
                            minute = 0;
                        }
                        mIgnoreEvent = true;
                        timePicker.setMinute(minute);
                        // junhlee 2018-05-08 "+"를 클릭하여 55분에서 00분이 되었을 때, 시간이 변하게 수정.
                        if (minute == 0 && before_minute < after_minute)
                            timePicker.setHour(hour + 1);
                        mIgnoreEvent = false;
                    }
                    /*================================================================================*/
                }
            });

            mBtnCancel.setOnClickListener(mLeftClickListener);
            mBtnOk.setOnClickListener(mRightClickListener);
        }
    }
}
