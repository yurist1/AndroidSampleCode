package com.example.gbkim.gubonny;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateCustomDialogActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private Button btn_date;
    private Button btn_time;
    private Button btn_bp;
    private Button btn_hp;
    private Button btn_mp;
    private Button btn_save;
    private TextView tv_bp;
    private TextView tv_hp;
    private TextView tv_mp;
    private int btn_position = 0;
    private int changBtnPosition = 0;
    private ArrayList<String> default_scale;
    private ArrayList<String> title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_custom_dialog);

        btn_date = findViewById(R.id.btn_date);
        btn_time = findViewById(R.id.btn_time);
        btn_bp = findViewById(R.id.btn_bp);
        btn_hp = findViewById(R.id.btn_hp);
        btn_mp = findViewById(R.id.btn_mp);
        btn_save = findViewById(R.id.btn_save);

        tv_bp = findViewById(R.id.tv_bp);
        tv_hp = findViewById(R.id.tv_hp);
        tv_mp = findViewById(R.id.tv_mp);

        getDate(); // DB에서 가져올 값

        setDate(); // 기본 데이터 넣기

        event(); // 각종 이벤트
    }

    // DB에서 가져올 값
    private void getDate() {
        String default_bp = "20";
        String default_hp = "50";
        String default_mp = "3";

        default_scale = new ArrayList<>();
        default_scale.add(default_bp);
        default_scale.add(default_hp);
        default_scale.add(default_mp);

    }

    // 기본 데이터 넣기
    private void setDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String getDate = sdf.format(date);

        sdf = new SimpleDateFormat("HH:mm:ss");

        String getTime = sdf.format(date);

        btn_bp.setText(default_scale.get(0).toString());
        btn_hp.setText(default_scale.get(1).toString());
        btn_mp.setText(default_scale.get(2).toString());

        btn_date.setText(getDate);
        btn_time.setText(getTime);
    }

    private void event() {
        final Calendar cal = Calendar.getInstance();
        title = new ArrayList<>();

        title.add("BP");
        title.add("HP");
        title.add("MP");

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(DateCustomDialogActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%d-%d-%d", year, month + 1, date);

                        btn_date.setText(msg);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                dpd.show();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpd = new TimePickerDialog(DateCustomDialogActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        String msg = String.format("%d:%d", hour, min);
                        btn_time.setText(msg);
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tpd.show();
            }
        });

        btn_bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_position = 0;

                show(btn_bp, btn_position, "20", "BP");
            }
        });

        btn_hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_position = 1;

                show(btn_hp, btn_position, btn_hp.getText().toString(), title.get(btn_position).toString());
            }
        });

        btn_mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_position = 2;

                show(btn_mp, btn_position, btn_mp.getText().toString(), title.get(btn_position).toString());
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_bp.setText(btn_bp.getText());
                tv_hp.setText(btn_hp.getText());
                tv_mp.setText(btn_mp.getText());
            }
        });
    }

    // Dialog 보여주기
    private void show(final Button btn, final int btn_position, String scale, String inTitle) {
        final Dialog dialog = new Dialog(DateCustomDialogActivity.this);

        dialog.setTitle("title");
        dialog.setContentView(R.layout.number_dialog);

        final NumberPicker numberPicker = dialog.findViewById(R.id.numberPicker1);
        Button btn_set = dialog.findViewById(R.id.btn_set);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        TextView tv_title = dialog.findViewById(R.id.tv_clicked);

        tv_title.setText(inTitle);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);
        numberPicker.setValue(Integer.parseInt(scale));
        numberPicker.setWrapSelectorWheel(true);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                btn.setText(String.valueOf(numberPicker.getValue()));
            }
        });

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setText(String.valueOf(numberPicker.getValue()));
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

        ImageView iv_left = dialog.findViewById(R.id.iv_left);
        ImageView iv_right = dialog.findViewById(R.id.iv_right);

        if (btn_position == 0) {
            iv_left.setVisibility(View.GONE);
        } else if (btn_position == 2) {
            iv_right.setVisibility(View.GONE);
        }

        changBtnPosition = btn_position;

        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changBtnPosition--;

                if (btn_position == 1) {
                    show(btn_bp, changBtnPosition, btn_bp.getText().toString(), title.get(changBtnPosition));
                } else if (btn_position == 2) {
                    show(btn_hp, changBtnPosition, btn_hp.getText().toString(), title.get(changBtnPosition));
                }
                dialog.dismiss();
            }
        });

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changBtnPosition++;

                if (btn_position == 0) {
                    show(btn_hp, changBtnPosition, btn_hp.getText().toString(), title.get(changBtnPosition));
                } else if (btn_position == 1) {
                    show(btn_mp, changBtnPosition, btn_mp.getText().toString(), title.get(changBtnPosition));
                }
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

    }
}
