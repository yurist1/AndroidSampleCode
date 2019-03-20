package com.example.yrchoi.yurist.preload;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yrchoi.yurist.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<AndroidVersion> android_version;
    private Context context;

    public DataAdapter(Context context, ArrayList<AndroidVersion> android_version){
        this.context = context;
        this.android_version = android_version;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_preload,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        String url = android_version.get(position).getAndroid_image_url();
        holder.tv_android.setText(android_version.get(position).getAndroid_version_name());
        Picasso.with(context).load(url).
                placeholder(R.drawable.img_icon_splash).
                resize(1200, 600).into(holder.img_android);
    }

    @Override
    public int getItemCount() {
        return android_version.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_android;
        ImageView img_android;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_android = itemView.findViewById(R.id.tv_android);
            img_android = itemView.findViewById(R.id.img_android);
        }
    }
}
