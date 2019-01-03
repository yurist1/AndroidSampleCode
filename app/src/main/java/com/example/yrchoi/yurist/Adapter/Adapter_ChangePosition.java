package com.example.gbkim.gubonny.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gbkim.gubonny.R;

/**
 * Created by gbkim on 2018-01-08.
 */

public class Adapter_ChangePosition extends RecyclerView.Adapter<Adapter_ChangePosition.ViewHolder> {

    View view;

    @Override
    public Adapter_ChangePosition.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_ChangePosition.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemview) {
            super(itemview);

        }
    }
}
