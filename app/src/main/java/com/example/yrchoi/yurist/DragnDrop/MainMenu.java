package com.example.yrchoi.yurist.DragnDrop;

public class MainMenu {


    public String getScreen_Menu_Name() {
        return Screen_Menu_Name;
    }

    public void setScreen_Menu_Name(String screen_Menu_Name) {
        Screen_Menu_Name = screen_Menu_Name;
    }

    public String getScreen_Order_Num() {
        return Screen_Order_Num;
    }

    public void setScreen_Order_Num(String screen_Order_Num) {
        Screen_Order_Num = screen_Order_Num;
    }

    public String getScreen_Active_YN() {
        return Screen_Active_YN;
    }

    public void setScreen_Active_YN(String screen_Active_YN) {
        Screen_Active_YN = screen_Active_YN;
    }

    public String getScreen_Type() {
        return Screen_Type;
    }

    public void setScreen_Type(String screen_Type) {
        Screen_Type = screen_Type;
    }

    public MainMenu(String screen_Menu_Name, String screen_Order_Num, String screen_Active_YN, String screen_Type) {
        Screen_Menu_Name = screen_Menu_Name;
        Screen_Order_Num = screen_Order_Num;
        Screen_Active_YN = screen_Active_YN;
        Screen_Type = screen_Type;
    }

    private String Screen_Menu_Name;
    private String Screen_Order_Num;
    private String Screen_Active_YN;
    private String Screen_Type;
}
