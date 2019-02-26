package com.example.yrchoi.yurist.DragnDrop;


public class ItemModel {
    public ItemModel(String name, String checked, int position) {
        this.name = name;
        this.checked = checked;
        this.list_position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public int getList_position() {
        return list_position;
    }

    public void setList_position(int position) {
        this.list_position = position;
    }

    private String name;
    private String checked;
    private int list_position;
}