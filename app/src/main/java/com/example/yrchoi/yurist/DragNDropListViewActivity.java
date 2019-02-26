package com.example.yrchoi.yurist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.yrchoi.yurist.Adapter.Adapter_dragAndDrop;
import com.example.yrchoi.yurist.DragnDrop.EditItemTouchHelperCallback;
import com.example.yrchoi.yurist.DragnDrop.ItemModel;
import com.example.yrchoi.yurist.DragnDrop.MainMenu;
import com.example.yrchoi.yurist.Interface.OnStartDragListener;

import java.util.ArrayList;
import java.util.List;

public class DragNDropListViewActivity extends AppCompatActivity {
    private Adapter_dragAndDrop mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private List<ItemModel> list;
    private static ArrayList<MainMenu> mainMenu_list;
    private  ArrayList<ArrayList> list_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop);

        RecyclerView main_menu_listView= findViewById(R.id.lv_menu_set_option_main_menu);


        MainMenu menu1 = new MainMenu("초콜릿","1","Y","M");
        mainMenu_list.add(menu1);
        MainMenu menu2 = new MainMenu("사탕","2","Y","M");
        mainMenu_list.add(menu2);
        MainMenu menu3 = new MainMenu("타르트","3","Y","M");
        mainMenu_list.add(menu3);
        MainMenu menu4 = new MainMenu("케이크","4","Y","M");
        mainMenu_list.add(menu4);
        MainMenu menu5 = new MainMenu("커피","5","Y","M");
        mainMenu_list.add(menu5);
        MainMenu menu6 = new MainMenu("얼그레이","6","Y","M");
        mainMenu_list.add(menu6);

        main_menu_listView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new Adapter_dragAndDrop(this, list, (OnStartDragListener) this,mainMenu_list, "M");
        ItemTouchHelper.Callback callback =
                new EditItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(main_menu_listView);
        main_menu_listView.setAdapter(mAdapter);
        list_order=mAdapter.getList_order();

    }

}
