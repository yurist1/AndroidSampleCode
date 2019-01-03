package com.example.yrchoi.yurist;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MotionEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);

        final Map<Integer, TextView> tvMap = new HashMap<>();
        tvMap.put(0, (TextView) findViewById(R.id.tvOne));
        tvMap.put(1, (TextView) findViewById(R.id.tvTwo));
        tvMap.put(2, (TextView) findViewById(R.id.tvThree));
        tvMap.put(3, (TextView) findViewById(R.id.tvFour));

        ConstraintLayout layout = findViewById(R.id.layoutbg);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouch(event);
                return true;
            }

            private void handleTouch(MotionEvent motionEvent) {

                int pointerCount = motionEvent.getPointerCount();

                for (int i = 0; i < pointerCount; i++) {
                    int id = motionEvent.getPointerId(i);
                    String actionString = CMotionEvent.getActionString(motionEvent.getActionMasked());
                    String touchStatus = String.format(
                            "Action: %s Index: %d ID: %d X: %d Y: %d", actionString,
                            motionEvent.getActionIndex(), id, (int) motionEvent.getX(i), (int) motionEvent.getY(i));

                    try {
                        tvMap.get(id).setText(touchStatus);
                    } catch (Exception e) {
                    }
                }
            }
        });

    }
}
