package com.example.gbkim.gubonny.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gbkim.gubonny.FilterActivity;
import com.example.gbkim.gubonny.R;
import com.example.gbkim.gubonny.VO.Info_Recent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by gbkim on 2018-02-05.
 */

public class Adapter_Filter extends RecyclerView.Adapter<Adapter_Filter.ViewHolder> {

    private final FilterActivity activity;
    private final List<Info_Recent> items;
    private final ArrayList<Info_Recent> arr;

    public Adapter_Filter(FilterActivity activity, List<Info_Recent> potionList) {
        this.activity = activity;
        this.items = potionList;
        arr = new ArrayList<>();
        arr.addAll(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Info_Recent info_recent = items.get(position);

        holder.tv_address.setText(info_recent.getAddress());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void filter(String text) {
        text = text.toLowerCase(Locale.getDefault());
        items.clear();

        if (text.length() == 0) {
            items.addAll(arr);
        } else {
            for (Info_Recent recent : arr) {
                String name = recent.getAddress();

                if (name.toLowerCase().contains(text)) {
                    items.add(recent);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_address;

        public ViewHolder(View itemview) {
            super(itemview);

            tv_address = itemview.findViewById(R.id.tv_address);
        }
    }
}
