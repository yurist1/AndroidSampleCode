package com.example.yrchoi.yurist.Common;

import android.support.v4.app.Fragment;

/**
 * Created by gbkim on 2018-04-03.
 */

public class HistoryBack {
    int frag_index;
    Fragment fragment;
    String frag_tag;

    public void setFragIndex(int frag_index) {
        this.frag_index = frag_index;
    }

    public int getFragIndex() {
        return frag_index;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragTag(String frag_tag) {
        this.frag_tag = frag_tag;
    }

    public String getFragTag() {
        return frag_tag;
    }

}
