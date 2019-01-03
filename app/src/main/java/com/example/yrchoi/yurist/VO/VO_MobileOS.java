package com.example.gbkim.gubonny.VO;

import android.annotation.SuppressLint;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by gbkim on 2018-01-11.
 */

@SuppressLint("ParcelCreator")
public class VO_MobileOS extends ExpandableGroup<VO_Phone> {

    public VO_MobileOS(String title, List<VO_Phone> items) {
        super(title, items);

    }

}
