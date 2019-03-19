package com.example.yrchoi.yurist.SlidingUpPanel;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.example.yrchoi.yurist.R;
import com.example.yrchoi.yurist.SlidingUpPanelActivity;
import java.util.Calendar;


public class FragmentCalendar extends Fragment {
    private static SlidingUpPanelActivity.CalendarItem[] days_of_month;
    //선택 된것에 대한 날짜
    private static SlidingUpPanelActivity.CalendarItem selected_date = new SlidingUpPanelActivity.CalendarItem(Calendar.getInstance());;
    private static GridViewAdatper gridadatper;
    private GridView grid;

    public FragmentCalendar newInstance(SlidingUpPanelActivity.CalendarItem[] days) {
        this.days_of_month = days;
        return new FragmentCalendar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_calendar,null);

        grid = view.findViewById(R.id.grid_calendar);
         gridadatper = new GridViewAdatper();


        grid.setAdapter(gridadatper);
        //어느날을 클릭했을 경우
        grid.setOnItemClickListener(new DayItemClickListener());




        return view;
    }

    class GridViewAdatper extends BaseAdapter{



        @Override
        public int getCount() {
            return days_of_month.length;
        }

        @Override
        public Object getItem(int i) {
            return days_of_month[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_one_day, null);

            final TextView textView_date = view.findViewById(R.id.date);
            final TextView textView_am = view.findViewById(R.id.txt_patient_am);
            final TextView textView_pm = view.findViewById(R.id.txt_patient_pm);

            SlidingUpPanelActivity.CalendarItem currentItem = days_of_month[i];

            try{
                textView_date.setText(""+days_of_month[i].day);
                if(days_of_month[i].day % 17 == 1){
                    textView_am.setText("오전: 1명");
                } else if(days_of_month[i].day % 17 == 10){
                    textView_pm.setText("오후: 3명");
                }
            } catch (Exception e){
                textView_date.setText("");
            }

            //색칠
            if(currentItem == null){

            } else {
                if((String.valueOf(currentItem.day)).equals(String.valueOf(selected_date.day))){
                    view.setBackgroundColor(Color.parseColor("#29a2f9"));
                    textView_am.setTextColor(Color.WHITE);
                    textView_pm.setTextColor(Color.WHITE);
                    textView_date.setTextColor(Color.WHITE);
                } else {
                    view.setBackgroundColor(Color.WHITE);
                    textView_am.setTextColor(Color.BLACK);
                    textView_pm.setTextColor(Color.BLACK);
                    textView_date.setTextColor(Color.BLACK);
                }
            }




            return view;
        }

        public final void setSelected(int year, int month, int day, String text) {
            selected_date.year = year;
            selected_date.month = month;
            selected_date.day = day;
            selected_date.text = text;
        }
    }

    private final class DayItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.d("choi",""+i);
            SlidingUpPanelActivity.CalendarItem item = (SlidingUpPanelActivity.CalendarItem) ((GridView) adapterView).getAdapter().getItem(i);
            gridadatper.setSelected(item.year, item.month, item.day, item.text);

            gridadatper = new GridViewAdatper();
            grid.setAdapter(gridadatper);
            gridadatper.notifyDataSetChanged();

            //리스트 바꾸기 위해 정보 fragment에 전달
            ((SlidingUpPanelActivity)getActivity()).setOnCallback(new SlidingUpPanelActivity.Callback() {
                @Override
                public SlidingUpPanelActivity.CalendarItem onCallback() {
                    SlidingUpPanelActivity.CalendarItem day = item;

                    return day;
                }
            });

        }
    }

}
