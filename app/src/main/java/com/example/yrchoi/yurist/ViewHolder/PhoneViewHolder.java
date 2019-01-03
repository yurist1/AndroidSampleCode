package com.example.gbkim.gubonny.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.gbkim.gubonny.VO.VO_Phone;
import com.example.gbkim.gubonny.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by gbkim on 2018-01-11.
 */

public class PhoneViewHolder extends ChildViewHolder {

    private TextView phoneName;

    public PhoneViewHolder(View itemView) {
        super(itemView);

        phoneName = itemView.findViewById(R.id.phone_name);
    }

    public void onBind(VO_Phone phone, ExpandableGroup group) {

        phoneName.setText(phone.getName());
    }
}
