package com.example.gbkim.gubonny.ServiceBind;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbkim.gubonny.R;

public class ServiceBindActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_start, bt_stop, bt_bind, bt_unbind, bt_call_variable;
    private TextView tv_text;
    //    private MyService mService;
    private boolean isBind;
    private SecuwayServiceConnetion mConn;
    private MyService tempService;

    private class SecuwayServiceConnetion implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
//            mService = myBinder.getService();
            tempService = myBinder.getService();

            isBind = true;
            Log.e("LOG", "onServiceConnected()");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            tempService = null;
            isBind = false;
            Log.e("LOG", "onServiceDisconnected()");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_bind);

        //버튼에 대한 참조
        bt_start = findViewById(R.id.bt_start);
        bt_stop = findViewById(R.id.bt_stop);
        bt_bind = findViewById(R.id.bt_bind);
        bt_unbind = findViewById(R.id.bt_unbind);
        bt_call_variable = findViewById(R.id.bt_call_variable);
        tv_text = findViewById(R.id.tv_text);

        //각 버튼에 대한 리스너 연결 - OnClickListener를 확장했으므로 onClick 오버라이딩 후 this사용
        bt_start.setOnClickListener(this);
        bt_stop.setOnClickListener(this);
        bt_bind.setOnClickListener(this);
        bt_unbind.setOnClickListener(this);
        bt_call_variable.setOnClickListener(this);

        mConn = new SecuwayServiceConnetion();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_start:
                startService(new Intent(ServiceBindActivity.this, MyService.class)); // 서비스 시작
                break;

            case R.id.bt_stop:
                stopService(new Intent(ServiceBindActivity.this, MyService.class)); // 서비스 종료
                break;

            case R.id.bt_bind:
                if (!isBind) { //해당 액티비이에서 바운딩 중이 아닐때만 호출 - 바운딩 시작
                    bindService(new Intent(ServiceBindActivity.this, MyService.class), mConn, BIND_AUTO_CREATE);

                    Intent intent = new Intent().setAction("net.secuwiz.SecuwaySSLU.service"); // 패키지 확인이 필요하다.
                    intent.setPackage("net.secuwiz.SecuwaySSLU.service");

                    if (bindService(intent, mConn, Context.BIND_AUTO_CREATE) == true) {
                        Log.e("LOG", "bindservice success");
                    } else {
                        Log.e("LOG", "bindservice Unsuccess");
                    }
                }
                break;

            case R.id.bt_unbind:
                if (isBind) //해당 액티비티에서 바운딩중일때만 호출 - 바운딩 종료
                    unbindService(mConn);
                break;


            case R.id.bt_call_variable:
                if (tempService == null)
                    Toast.makeText(this, "mService가 null이므로 불러 올 수 없습니다.", Toast.LENGTH_SHORT).show();
                else if (tempService != null)
                    tv_text.setText("불러온 값 : " + tempService.var);
                break;

        }
    }
}
