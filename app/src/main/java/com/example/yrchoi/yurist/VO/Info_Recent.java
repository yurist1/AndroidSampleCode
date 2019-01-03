package com.example.yrchoi.yurist.VO;

/**
 * Created by phkim on 2017-01-17.
 */

public class Info_Recent {
    private String address;
    private String link;

    public Info_Recent(String _address, String _link){
        address = _address;
        link= _link;
    }

    public String getAddress() {
        return address;
    }

    public String getLink() {
        return link;
    }
}
