package com.example.yrchoi.yurist.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.yrchoi.yurist.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by gbkim on 2018-01-11.
 */

public class OSViewHolder extends GroupViewHolder{

    private TextView osName;

    public OSViewHolder(View itemView) {
        super(itemView);

        osName = itemView.findViewById(R.id.mobile_os);
    }

    @Override
    public void expand() {

    }

    @Override
    public void collapse() {
    }

    public void setGroupName(ExpandableGroup group) {
        osName.setText(group.getTitle());

    }
}
