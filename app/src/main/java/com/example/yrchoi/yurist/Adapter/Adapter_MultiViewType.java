package com.example.yrchoi.yurist.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yrchoi.yurist.MultiplyViewActivity;
import com.example.yrchoi.yurist.R;
import com.example.yrchoi.yurist.VO.VO_Model;

import java.util.ArrayList;

/**
 * Created by gbkim on 2018-01-16.
 */

public class Adapter_MultiViewType extends RecyclerView.Adapter {

    private ArrayList<VO_Model> dataSet;
    Context context;
    int total_types;
    MediaPlayer mediaPlayer;
    private boolean fabStateVolume = false;

    public Adapter_MultiViewType(ArrayList<VO_Model> list, MultiplyViewActivity multiplyViewActivity) {
        this.dataSet = list;
        this.context = multiplyViewActivity;
        total_types = dataSet.size();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        switch (viewType) {
            case VO_Model.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_text_type, parent, false);

                return new TextTypeViewHolder(view);

            case VO_Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_image_type, parent, false);

                return new ImageTypeViewHolder(view);

            case VO_Model.AUDIO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_audio_type, parent, false);

                return new AudioTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        switch (dataSet.get(position).type) {
            case 0:
                return VO_Model.TEXT_TYPE;
            case 1:
                return VO_Model.IMAGE_TYPE;
            case 2:
                return VO_Model.AUDIO_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VO_Model object = dataSet.get(position);

        if (object != null) {
            switch (object.type) {
                case VO_Model.TEXT_TYPE:
                    ((TextTypeViewHolder) holder).txtType.setText(object.text);
                    break;

                case VO_Model.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);
                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
                    break;

                case VO_Model.AUDIO_TYPE:

                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.type);
        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.type);
            this.image = itemView.findViewById(R.id.background);
        }
    }

    public static class AudioTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        FloatingActionButton fab;

        public AudioTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.type);
            this.fab = itemView.findViewById(R.id.fab);
        }
    }
}
