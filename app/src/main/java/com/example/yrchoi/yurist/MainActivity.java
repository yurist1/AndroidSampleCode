package com.example.yrchoi.yurist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yrchoi.yurist.GridView.ActivityGridView;
import com.example.yrchoi.yurist.NotificationService.FCMActivity;
import com.example.yrchoi.yurist.NotificationService.NoServerNotifiActivity;
import com.example.yrchoi.yurist.Picker.NumberPickerActivity;
import com.example.yrchoi.yurist.ServiceBind.ServiceBindActivity;
import com.example.yrchoi.yurist.SplashImage.SplashActivity;
import com.example.yrchoi.yurist.TakePicture.ActivityTakePicture;
import com.example.yrchoi.yurist.TreeView.TreeView2Activity;
import com.example.yrchoi.yurist.TreeView.TreeViewActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Map<String, Class<?>> menuActs = new LinkedHashMap<>();
    private ArrayList<String> arDate;
    private ArrayAdapter<String> arA;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addList();    // list 추가

        setAdapter(); // 화면구성, 어탭터 추가

        itemClick();  // 리스트 선택 이벤트
    }

    private void addList() {
        // list 메뉴 명 / 클래스명
        menuActs.put("ReclycerView", CardViewActivity.class);
        menuActs.put(" - CardView", CardViewActivity.class);
        menuActs.put(" - ChangeBackground", ChangeBgActivity.class);
        menuActs.put(" - Filter", FilterActivity.class);
        menuActs.put(" - Drag&Drop ListView", DragNDropListViewActivity.class);
        menuActs.put("JavaCode UI", JavaLayoutActivity.class);
        menuActs.put("ListView", ListActivity.class);
        menuActs.put(" - LvItemSelect", LvItemSelectActivity.class);
        menuActs.put(" - SwipeListView", SwipeListViewActivity.class);
        menuActs.put("Event", EventBasicActivity.class);
        menuActs.put(" - MotionEvent", MotionEventActivity.class);
        menuActs.put("Receiver", ReceiverActivity.class);
        menuActs.put(" - NetworkReceiver", NetworkReceiverActivity.class);
        menuActs.put("Dialog", DialogBasicActivity.class);
        menuActs.put(" - CustomeDialog", CustomDialogActivity.class);
        menuActs.put(" - DateCustomDialog", DateCustomDialogActivity.class);
        menuActs.put("SaveLogin", SaveLoginActivity.class);
        menuActs.put("Fragment", FragmentActivity.class);
        menuActs.put(" - CustomListFragment", ListFragmentActivity.class);
        menuActs.put("ViewPager", ViewPagerActivity.class);
        menuActs.put(" - Clickable", ClickableVpActivity.class);
        menuActs.put("Expandable", ExpandableActivity.class);
        menuActs.put("Multiple View Type", MultiplyViewActivity.class);
        menuActs.put("ScrollView", ScrollViewActivity.class);
        menuActs.put("AutoComplete", AutoCompleteActivity.class);
        menuActs.put("Temp", TempActivity.class);
        menuActs.put("Notification", null);
        menuActs.put(" - No_Server_Notification", NoServerNotifiActivity.class);
        menuActs.put(" - FCMNotification", FCMActivity.class);
        menuActs.put("Media", null);
        menuActs.put(" - TakePicture", ActivityTakePicture.class);
        menuActs.put(" - Recording Voice", RecordingVoice.class);
        menuActs.put("ServiceBind", ServiceBindActivity.class);
        menuActs.put("GridView", ActivityGridView.class);
        menuActs.put("Picker", NumberPickerActivity.class);
        menuActs.put("Splash Image", SplashActivity.class);
        menuActs.put("Tree View", null);
        menuActs.put(" - Tree View1", TreeViewActivity.class);
        menuActs.put(" - Tree View2", TreeView2Activity.class);
        menuActs.put("Sliding Up panel", SlidingUpPanelActivity.class);
        menuActs.put("Preload", Preload.class);
        menuActs.put("Load More(Scroll and load)", LoadMore.class);
        menuActs.put("DrawerMenu", DrawerMenu.class);
        menuActs.put("CoordinatorLayout", CoordiActivity.class);

    }

    private void setAdapter() {
        arDate = new ArrayList<>();
        for (String key : menuActs.keySet()) arDate.add(key);

        arA = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arDate);

        lv = findViewById(R.id.lvMainMenu);
        lv.setAdapter(arA);
    }

    private void itemClick() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, menuActs.get(((ListView) findViewById(R.id.lvMainMenu)).getItemAtPosition(position)));
                startActivity(intent);
            }
        });
    }
}

