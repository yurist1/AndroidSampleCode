package com.example.yrchoi.yurist;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.yrchoi.yurist.Fragment.Fragment1;
import com.example.yrchoi.yurist.Fragment.Fragment2;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_left, btn_right;
    private final int f1 = 1;
    private final int f2 = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // 프레그먼트 전환 버튼
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);

        // 버튼 이벤트 리스너
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);

        // 임의로 액티비티 호출 시점에 어느 프레그먼트를 프레임레이아웃에 듸울 것인지 정함.
        callFragment(f1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                callFragment(f1);
                break;

            case R.id.btn_right:
                callFragment(f2);
                break;
        }
    }

    private void callFragment(int fragment_no) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment_no) {
            case 1:
                Fragment1 fragment1 = new Fragment1();
                transaction.replace(R.id.framelayout, fragment1);
                transaction.commit();

                break;

            case 2:
                Fragment2 fragment2 = new Fragment2();
                transaction.replace(R.id.framelayout, fragment2);
                transaction.commit();

                break;
        }

    }
}
