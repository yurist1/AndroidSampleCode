package com.example.yrchoi.yurist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yrchoi.yurist.Adapter.RecyclerViewAdapter;
import com.example.yrchoi.yurist.Listener.RecyclerItemClickListener;

import java.util.ArrayList;

public class CardViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager RecyclerViewLayoutManager; // 수직/수평 스크롤 리스트
    private ArrayList<String> SubjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

         /*
         * fbc -> () 에서 ctrl + shift + space -> R.id. 완성 -> 빨간줄에서 ctrl + alt + F : 전역단축키
         */
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);

        addItems(); // 카드뷰에 데이터 넣기

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(SubjectName);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView tv_cardItem = view.findViewById(R.id.tvTitle);
                tv_cardItem.setText("눌렀쯤");
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        }

    private void addItems() {
        // Adding items to RecyclerView
        SubjectName = new ArrayList<>();

        SubjectName.add("사과");
        SubjectName.add("배");
        SubjectName.add("딸기");
        SubjectName.add("바나나");
        SubjectName.add("유자");
        SubjectName.add("사과");
        SubjectName.add("배");
        SubjectName.add("딸기");
        SubjectName.add("바나나");
        SubjectName.add("유자");
        SubjectName.add("사과");
        SubjectName.add("배");
        SubjectName.add("딸기");
        SubjectName.add("바나나");
        SubjectName.add("유자");
        SubjectName.add("사과");
        SubjectName.add("배");
        SubjectName.add("딸기");
        SubjectName.add("바나나");
        SubjectName.add("유자");
        SubjectName.add("사과");
        SubjectName.add("배");
        SubjectName.add("딸기");
        SubjectName.add("바나나");
        SubjectName.add("유자");

    }
}
