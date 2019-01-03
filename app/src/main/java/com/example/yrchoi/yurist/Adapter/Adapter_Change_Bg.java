package com.example.gbkim.gubonny.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gbkim.gubonny.Listener.Listener_Change_Bg;
import com.example.gbkim.gubonny.R;

import java.util.List;

/**
 * Created by gbkim on 2018-01-23.
 */

public class Adapter_Change_Bg extends RecyclerView.Adapter<Adapter_Change_Bg.ViewHolder> {

    private final List<String> mItems;
    private Listener_Change_Bg clickListener = null;

    public Adapter_Change_Bg(List<String> mItems) {
        this.mItems = mItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_change_bg_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mTextView = itemView.findViewById(R.id.list_item);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getAdapterPosition());
            }
        }
    }

    public void setClickListener(Listener_Change_Bg clickListener) {
        this.clickListener = clickListener;
    }
}
