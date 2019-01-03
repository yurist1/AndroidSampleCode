package com.example.gbkim.gubonny.VO;

/**
 * Created by gbkim on 2018-01-16.
 */

public class VO_Model {

    public static final int TEXT_TYPE = 0;
    public static final int IMAGE_TYPE = 1;
    public static final int AUDIO_TYPE = 2;

    public int type;
    public int data;
    public String text;

    public VO_Model(int type, String text, int data) {
        this.type = type;
        this.data = data;
        this.text = text;
    }
}