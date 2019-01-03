package com.example.yrchoi.yurist.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yrchoi.yurist.VO.VO_MobileOS;
import com.example.yrchoi.yurist.VO.VO_Phone;
import com.example.yrchoi.yurist.R;
import com.example.yrchoi.yurist.ViewHolder.OSViewHolder;
import com.example.yrchoi.yurist.ViewHolder.PhoneViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by gbkim on 2018-01-11.
 */

public class Adapter_Expandable extends ExpandableRecyclerViewAdapter<OSViewHolder, PhoneViewHolder>{

    private final Activity activity;

    public Adapter_Expandable(Activity activity, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.activity = activity;
    }

    @Override
    public OSViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_view_holder, parent, false);

        return new OSViewHolder(view);
    }

    @Override
    public PhoneViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.child_view_holder, parent, false);

        return new PhoneViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(PhoneViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final VO_Phone phone = ((VO_MobileOS) group).getItems().get(childIndex);

        holder.onBind(phone, group);
    }

    @Override
    public void onBindGroupViewHolder(OSViewHolder holder, int flatPosition, ExpandableGroup group) {

        holder.setGroupName(group);
    }
}
