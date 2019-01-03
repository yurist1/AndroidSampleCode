package com.example.gbkim.gubonny.GridView;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.gbkim.gubonny.R;

public class ActivityGridView extends AppCompatActivity {

    private GridView gv_main;
    private TextView tv_gv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gv_main = findViewById(R.id.gv_main);
        tv_gv_main = findViewById(R.id.tv_gv_main);

        String tv[] = {"A", "B", "C", "D", "E", "F"};

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.gvi_tv, tv);
        gv_main.setAdapter(adapter); // 커스텀 어댑터를 GridView 에 적용

        // GridView 아이템을 클릭하면 상단 텍스트뷰에 position 출력
        // JAVA8 에 등장한 lambda expression 으로 구현했습니다. 코드가 많이 간결해지네요
        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                tv_gv_main.setText("Position : " + position);
            }
        });
    }

    private class CustomAdapter extends BaseAdapter{
        Context context;
        int layout;
        String arr_TextView[];
        LayoutInflater inflater;

        public CustomAdapter(Context context, int layout, String[] arr_TextView) {
            this.context = context;
            this.layout = layout;
            this.arr_TextView = arr_TextView;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return arr_TextView.length;
        }

        @Override
        public Object getItem(int position) {
            return arr_TextView[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(layout, null);
                TextView tv_gv_list_item = convertView.findViewById(R.id.tv_gv_list_item);
                tv_gv_list_item.setText(arr_TextView[position]);
            }

            return convertView;
        }
    }
}
