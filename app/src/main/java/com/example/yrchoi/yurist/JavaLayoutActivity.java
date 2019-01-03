package com.example.yrchoi.yurist;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class JavaLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_java_layout);
        // JavaCode 레이아웃 속성 Method
        setContentView(getGenerateLayout());

        // 버튼 클릭 이벤트
        btnClick();
    }

    private RelativeLayout getGenerateLayout() {
        Button button = new Button(this);
        button.setText("Press Me");
        button.setBackgroundColor(Color.YELLOW);
        button.setTransformationMethod(null);

        RelativeLayout myLayout = new RelativeLayout(this);
        myLayout.setBackgroundColor(Color.BLUE);

        EditText editText = new EditText(this);

        button.setId(R.id.btnId);
        editText.setId(R.id.etId);

        RelativeLayout.LayoutParams buttonParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
                        , RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams editextParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
                        , RelativeLayout.LayoutParams.WRAP_CONTENT);

        // EditText 속성
        editextParams.addRule(RelativeLayout.ABOVE, button.getId());
        editextParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        editextParams.setMargins(0, 0, 0, 80);

        Resources r = getResources();
        editText.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics()));

        // Button 속성
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.addRule(RelativeLayout.CENTER_VERTICAL);

        myLayout.addView(button, buttonParams);
        myLayout.addView(editText, editextParams);

        return myLayout;
    }

    private void btnClick() {
        findViewById(R.id.btnId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 버튼 클릭 시 text 초기화
                ((EditText)findViewById(R.id.etId)).setText("");

            }
        });
    }
}
