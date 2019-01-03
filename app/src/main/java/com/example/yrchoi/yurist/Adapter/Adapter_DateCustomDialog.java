package com.example.gbkim.gubonny.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gbkim.gubonny.R;

import java.util.ArrayList;


public class Adapter_DateCustomDialog extends RecyclerView.Adapter<Adapter_DateCustomDialog.ViewHolder> {

    private final ArrayList<String> title;
    private final Context context;

    public Adapter_DateCustomDialog(Context context, ArrayList<String> inTitle) {
        this.context = context;
        this.title = inTitle;
    }

    // viewholder로 UI를 정의한다. view를 재사용 할 수 있게 해준다.
    @Override
    public Adapter_DateCustomDialog.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_vitalsign, parent, false);

        return new ViewHolder(view);
    }

    // 지정된 항목의 텍스트와 그래픽 데이터를 레이아웃의 뷰에 넣은 후 그 객체를 RecycleView에 반환
    @Override
    public void onBindViewHolder(Adapter_DateCustomDialog.ViewHolder holder, int position) {
        holder.tv_input_title.setText(title.get(position));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_bp;
        private final TextView tv_hp;
        private final TextView tv_mp;
        private final TextView tv_input_title;
        private final EditText edit_input;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_bp = itemView.findViewById(R.id.tv_bp);
            tv_hp = itemView.findViewById(R.id.tv_hp);
            tv_mp = itemView.findViewById(R.id.tv_mp);
            tv_input_title = itemView.findViewById(R.id.tv_input_title);
            edit_input = itemView.findViewById(R.id.edit_input);
        }
    }
}
