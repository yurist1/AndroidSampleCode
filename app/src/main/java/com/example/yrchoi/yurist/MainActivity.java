package com.example.yrchoi.yurist;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.SystemClock;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yrchoi.yurist.GridView.ActivityGridView;
import com.example.yrchoi.yurist.NotificationService.FCMActivity;
import com.example.yrchoi.yurist.NotificationService.NoServerNotifiActivity;
import com.example.yrchoi.yurist.Picker.NumberPickerActivity;
import com.example.yrchoi.yurist.ServiceBind.ServiceBindActivity;
import com.example.yrchoi.yurist.SplashImage.SplashActivity;
import com.example.yrchoi.yurist.TakePicture.ActivityTakePicture;
import com.example.yrchoi.yurist.TreeView.TreeView2Activity;
import com.example.yrchoi.yurist.TreeView.TreeViewActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Map<String, Class<?>> menuActs = new LinkedHashMap<>();
    private ArrayList<String> arDate;
    private ArrayAdapter<String> arA;
    private ListView lv;
    private FrameLayout frame_rec;
    private ImageView img_rec_btn;
    private ImageView img_stop_btn;
    private ImageView img_play_btn;
    private Chronometer rec_timer;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer = null;
    private String fileName = null;
    private boolean isPlaying = false;

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
        menuActs.put("Google Graph", GoogleGraph.class);
        menuActs.put("Webview interact", WebviewInter.class);


    }

    private void setAdapter() {
        arDate = new ArrayList<>();
        for (String key : menuActs.keySet()) arDate.add(key);

        arA = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arDate);

        lv = findViewById(R.id.lvMainMenu);
        frame_rec = findViewById(R.id.frame_rec_box);
        img_rec_btn = findViewById(R.id.img_rec_btn);
        img_stop_btn = findViewById(R.id.img_stop_btn);
        img_play_btn = findViewById(R.id.img_play_btn);
        rec_timer = findViewById(R.id.chronometerTimer);

        lv.setAdapter(arA);
    }

    private void itemClick() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //녹음 위젯 나타나랏
                if(menuActs.get(((ListView) findViewById(R.id.lvMainMenu)).getItemAtPosition(position)).getName().contains("Voice")){
                    img_rec_btn.clearAnimation();
                    frame_rec.setVisibility(View.VISIBLE);
                    img_rec_btn.setVisibility(View.VISIBLE);
                    img_stop_btn.setVisibility(View.GONE);
                    img_play_btn.setVisibility(View.GONE);
                    rec_timer.setVisibility(View.GONE);
                } else {
                    frame_rec.setVisibility(View.GONE);
                    //클래스로 넘어가랏
                    Intent intent = new Intent(MainActivity.this, menuActs.get(((ListView) findViewById(R.id.lvMainMenu)).getItemAtPosition(position)));
                    startActivity(intent);
                }

            }
        });


        //녹음 버튼 눌렀을 때
        img_rec_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ui 변경
                prepareForRec();
                //녹음시작
                startRec();
            }
        });

        //정지 눌렀을 때
        img_stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ui 변경
                prepareForStop();
                //녹음중지
                stopRec();

            }
        });

        //플레이 눌렀을 때
        img_play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !isPlaying && fileName != null ){
                    isPlaying = true;
                    playRec();
                }else{
                    isPlaying = false;
                    stopPlayingRec();
                }

            }
        });
    }

    private void stopPlayingRec() {
//        try{
//            mPlayer.release();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        mPlayer = null;
        //showing the play button
        img_play_btn.setImageResource(R.drawable.ic_play);
        rec_timer.stop();
    }

    private void playRec() {
        if(mPlayer == null){
            //플레이어 객체 초기화
            mPlayer = new MediaPlayer();
            //
            try {
                mPlayer.setDataSource(fileName);
                mPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        mPlayer.start();
        //이미지 변경
        img_play_btn.setImageResource(R.drawable.ic_pause);
        //
        rec_timer.setVisibility(View.VISIBLE);

        rec_timer.setBase(SystemClock.elapsedRealtime() - mPlayer.getCurrentPosition());
        rec_timer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                img_play_btn.setImageResource(R.drawable.ic_play);
                isPlaying = false;
                rec_timer.stop();
                mPlayer = null;
            }
        });
    }

    private void stopRec() {
        //녹음 정지
        mRecorder.stop();
        //파일 발행
        mRecorder.release();
        //미디어 객체 초기화
        mRecorder = null;
        //showing the play button
        Toast.makeText(this, "Recording saved successfully.", Toast.LENGTH_SHORT).show();
    }

    private void prepareForStop() {
        //타이머 멈추고
        rec_timer.stop();
        img_rec_btn.clearAnimation();
       //rec, stop 버튼이랑 타이버 없애고
        img_rec_btn.setVisibility(View.VISIBLE);
        img_stop_btn.setVisibility(View.GONE);
        rec_timer.setVisibility(View.GONE);
        //play 버튼 나오게 하기
        img_play_btn.setVisibility(View.VISIBLE);

    }

    private void startRec() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //오디오 전용파일 포맷은 AMR or AAC
        //비디오 전용파일 포맷은 MPEG-4 or THREE-GPP (MP4 = MPEG-4)
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        //파일 저장공간
        File root = android.os.Environment.getExternalStorageDirectory();
        File file = new File(root.getAbsolutePath() + "/VoiceRecorderSimplifiedCoding/Audios");
        //파일이 없다면 디렉토리 생성
        if (!file.exists()) {
            file.mkdirs();
        }
        //파일명 지정
        fileName =  root.getAbsolutePath() + "/VoiceRecorderSimplifiedCoding/Audios/" + String.valueOf(System.currentTimeMillis() + ".mp3");
        //파일 추출
        mRecorder.setOutputFile(fileName);
        //파일 형식
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        //녹음 준비! 시작!
        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //타이머 스타트!
        rec_timer.setBase(SystemClock.elapsedRealtime());
        rec_timer.start();
    }

    private void prepareForRec() {
        // 애니메이션으로 깜빡깜빡이기
        Animation anim = new AlphaAnimation(0.4f, 1.0f);
        anim.setDuration(400); //You can manage the time of the blink with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        img_rec_btn.startAnimation(anim);

        //stop 버튼이랑 타이버 보여주기
        img_stop_btn.setVisibility(View.VISIBLE);
        rec_timer.setVisibility(View.VISIBLE);
        img_play_btn.setVisibility(View.GONE);
    }
}

