package com.example.yrchoi.yurist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yrchoi.yurist.SlidingUpPanel.FragmentCalendar;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class SlidingUpPanelActivity extends AppCompatActivity {
    private static final String TAG = "DemoActivity";

    private SlidingUpPanelLayout mLayout;
    private static ListView lv;
    private static Calendar calendar;
    private CalendarItem[] days;
    private static TextView tv_month;
    private static TextView tv_title;
    private ViewPager viewPager;

    //callback practice
    private Callback callback;

    public interface Callback{
        public CalendarItem onCallback();
    }

    public void setOnCallback(Callback oCallback){
        this.callback = oCallback;
        //리스트 만들기
        Toast.makeText(getApplicationContext(), ""+callback.onCallback().text, Toast.LENGTH_SHORT).show();

        tv_title.setText(callback.onCallback().text + "일 환자 스케줄");
        ListViewAdapter listViewAdapter = new ListViewAdapter();
        lv.setAdapter(listViewAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_up_panel);

        calendar = Calendar.getInstance();
        tv_month = findViewById(R.id.tv_month);
        viewPager = findViewById(R.id.main_viewpager);
        lv = findViewById(R.id.list);
        tv_title = findViewById(R.id.tv_title);


        getMonthlyDate(0);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1000);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            float current_page = 1000;
            float change_page = 1000;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {

                    current_page = positionOffset;

                }
            }

            @Override
            public void onPageSelected(int position) {

                change_page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == 0) {
                    int move_n_page = (int)(change_page - current_page);
                    getMonthlyDate(move_n_page);
                    pagerAdapter.notifyDataSetChanged();
                    current_page = change_page;

                }
            }
        });




        List<String> your_array_list = Arrays.asList(
                "This",
                "Is",
                "An",
                "Example",
                "ListView",
                "That",
                "You",
                "Can",
                "Scroll",
                ".",
                "It",
                "Shows",
                "How",
                "Any",
                "Scrollable",
                "View",
                "Can",
                "Be",
                "Included",
                "As",
                "A",
                "Child",
                "Of",
                "SlidingUpPanelLayout"
        );

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list);

        lv.setAdapter(arrayAdapter);

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

//        TextView t = (TextView) findViewById(R.id.name);
//        t.setText(Html.fromHtml(getString(R.string.hello)));
//        Button f = (Button) findViewById(R.id.follow);
//        f.setText(Html.fromHtml(getString(R.string.follow)));
//        f.setMovementMethod(LinkMovementMethod.getInstance());
//        f.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("http://www.twitter.com/umanoapp"));
//                startActivity(i);
//            }
//        });
    }




    private void getMonthlyDate(int direction){

        calendar.set(Calendar.DAY_OF_MONTH ,1);

        calendar.add(Calendar.MONTH,direction);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH );
        int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int blank;
        int day_of_week = firstDayOfMonth;
        blank = firstDayOfMonth - 1;
        tv_month.setText(year+"년 "+(month+1)+"월");
        if ((lastDayOfMonth + blank) > 35)
            days = new CalendarItem[42];
        else
            days = new CalendarItem[35];

        for (int day = 1, position = blank; position < (lastDayOfMonth + blank); position++) {
            if (position < days.length) {
                if (day_of_week == 7) {
                    days[position] = new CalendarItem(year, month, day++, day_of_week);
                    day_of_week = 1;
                } else {
                    days[position] = new CalendarItem(year, month, day++, day_of_week++);
                }
            }
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mLayout != null) {
            if (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
                item.setTitle(R.string.action_show);
            } else {
                item.setTitle(R.string.action_hide);
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toggle: {
                if (mLayout != null) {
                    if (mLayout.getPanelState() != SlidingUpPanelLayout.PanelState.HIDDEN) {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                        item.setTitle(R.string.action_show);
                    } else {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mLayout != null) {
                    if (mLayout.getAnchorPoint() == 1.0f) {
                        mLayout.setAnchorPoint(0.7f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                        item.setTitle(R.string.action_anchor_disable);
                    } else {
                        mLayout.setAnchorPoint(1.0f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setTitle(R.string.action_anchor_enable);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    class ListViewAdapter extends BaseAdapter {

        List<String> your_array_list = Arrays.asList(
                "This",
                "This",
                "This",
                "This",
                "This",
                "This"
        );

        @Override
        public int getCount() {
            return your_array_list.size();
        }

        @Override
        public Object getItem(int i) {
            return your_array_list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item_swipe, null);
                TextView tv_gv_list_item = view.findViewById(R.id.textView);
                tv_gv_list_item.setText(your_array_list.get(position));
            }

            return view;
        }
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {


        @Override
        public int getItemPosition(@NonNull Object object) {
//            return super.getItemPosition(object);
            return POSITION_NONE;
        }

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int frag_position) {
            Fragment calendar = new FragmentCalendar().newInstance(days);

            return calendar;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

    }

    public static class CalendarItem {
        public int year;
        public int month;
        public int day;
        public int day_of_week;
        public String text;
        public long id;

        public CalendarItem(Calendar calendar) {
            this(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.DAY_OF_WEEK));
        }

        public CalendarItem(int year, int month, int day, int day_of_week) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.day_of_week = day_of_week;
            this.text = String.valueOf(day);
            this.id = Long.valueOf(year + "" + month + "" + day);
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof CalendarItem) {
                final CalendarItem item = (CalendarItem)o;
                return item.year == year && item.month == month && item.day == day;
            }
            return false;
        }
    }
}
