package com.example.yrchoi.yurist.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yrchoi.yurist.R;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<String> SubjectNames;
    View view;


    public RecyclerViewAdapter(ArrayList<String> inSubjectName) {
        this.SubjectNames = inSubjectName;
    }

    // viewholder로 UI를 정의한다. view를 재사용 할 수 있게 해준다.
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview, parent, false);

        return new ViewHolder(view);
    }

    // 지정된 항목의 텍스트와 그래픽 데이터를 레이아웃의 뷰에 넣은 후 그 객체를 RecycleView에 반환
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.SubjectTv.setText(SubjectNames.get(position));
    }

    // 레이아웃 찾아와 변수 설정함. 'itemview.'으로 찾아오는게 중요
    @Override
    public int getItemCount() {
        return SubjectNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView SubjectTv;

        public ViewHolder(View itemview) {
            super(itemview);

            SubjectTv = itemview.findViewById(R.id.tvTitle);
        }
    }
}
