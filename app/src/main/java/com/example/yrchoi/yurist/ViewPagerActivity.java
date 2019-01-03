package com.example.gbkim.gubonny;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.gbkim.gubonny.Adapter.Adapter_ViewPager;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    private int dotscount;
    private ImageView[] dots;
    private LinearLayout slider;
    private ArrayList<String[]> arrResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        init();

    }


    private void init() {

        String[] arrString1 = {"서울", "홍길동", "1", "M", "21", "N", "N", "N", "N", "N"};

        arrResult.add(arrString1);

        ViewPager viewPager = findViewById(R.id.viewpager_my_patient);
        Adapter_ViewPager adapterViewPager = new Adapter_ViewPager(getLayoutInflater(), arrResult);

        slider = findViewById(R.id.slider);

        viewPager.setAdapter(adapterViewPager);

        dotscount = adapterViewPager.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            slider.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
