package com.example.yrchoi.yurist;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SwipeListViewActivity extends AppCompatActivity{
    private boolean flag;
    private ArrayList mArrayList;
    private SwipeMenuListView mListView;
    private ListDataAdapter mListDataAdapter;
//    private ListDataAdapter mListDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_listview);
        mArrayList = new ArrayList();
        flag = true;

        //안읽은 알람만 골라 담기
        prepareAlarmListData();

        //init 실행할지 말지 여부 판단
        if(flag==true){
            initControls();
        }
    }

    public int prepareAlarmListData(){
        mArrayList.add("Message");
        mArrayList.add("Message1");
        mArrayList.add("Message2");
        mArrayList.add("Message3");
    return mArrayList.size();
    }


    private void initControls() {

        mListView=(SwipeMenuListView)findViewById(R.id.swipe_listView);
        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        mListDataAdapter=new ListDataAdapter();
        mListView.setAdapter(mListDataAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
                SwipeMenuItem goodItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                goodItem.setBackground(new ColorDrawable(Color.rgb(102,102,102)));
                // set item width
                goodItem.setWidth(dp2px(74));
                // set a icon
//                goodItem.setIcon(R.drawable.ic_action_good);
                goodItem.setTitle("CHECK");
                goodItem.setTitleColor(Color.rgb(255,255,255));
                goodItem.setTitleSize(dp2px(5));
                // add to menu
                menu.addMenuItem(goodItem);

            }
        };

        // set creator
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();


                        break;

                }
                return true;
            }
        });

        //mListView

        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {

            }

            @Override
            public void onMenuClose(int position) {

            }
        });

        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });


    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    class ListDataAdapter extends BaseAdapter {

        ViewHolder holder;
        public int getListCount() {
            return mArrayList.size();
        }

        @Override
        public int getCount() {
            return mArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){

                holder=new ViewHolder();

                convertView=getLayoutInflater().inflate(R.layout.list_item_swipe,null);

                holder.mTextview=(TextView)convertView.findViewById(R.id.textView);

                convertView.setTag(holder);
            }else {

                holder= (ViewHolder) convertView.getTag();
            }

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
            Date now = new Date();
            String strDate = sdfDate.format(now);

            holder.mTextview.setText(mArrayList.get(position).toString());

            return convertView;
        }

        class ViewHolder {

            TextView mTextview;

        }
    }

}
