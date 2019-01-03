package com.example.gbkim.gubonny;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SaveLoginActivity extends AppCompatActivity {

    private SharedPreferences appData;
    private boolean saveLoginData;
    private String id;
    private String pw;
    private EditText inID;
    private EditText inPW;
    private CheckBox checkBox;
    private Button btnLogin;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_login);

        inID = findViewById(R.id.et_SaveLogin_ID);
        inPW = findViewById(R.id.et_SaveLogin_PW);
        checkBox = findViewById(R.id.cb_SaveLogin);
        btnLogin = findViewById(R.id.btn_SaveLogin);

        // 설정값 불러오기
        appData = getSharedPreferences("appData", MODE_PRIVATE);

        load();

        // 이전 로그인 정보를 저장시킨 기록이 있다면
        if (saveLoginData) {
            inID.setText(id);
            inPW.setText(pw);
            checkBox.setChecked(saveLoginData);
        }

        // 로그인 성공시 id, pw 저장
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inID.getText().toString().equals("111") && inPW.getText().toString().equals("111")) {
                    save();
                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), SaveLoginNextActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
                    inID.setText("");
                    inPW.setText("");
                }

            }
        });

    }

    // 설정값 저장하는 함수
    private void save() {
        // SharedPreferences 객체만으론 저장 불가능, Eidtor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입(저장시킬 이름, 저장시킬 값)
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putBoolean("SAVE_LOGIN_DATA", checkBox.isChecked());
        editor.putString("ID", inID.getText().toString().trim());
        editor.putString("PW", inPW.getText().toString().trim());

        // apply, commit을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }

    private void load() {
        // SharedPreferences 객체.get타입 (저장된 이름, 기본값)
        // 저장된 이름이 존재하지 않을 시 기본값
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("ID", "");
        pw = appData.getString("PW", "");

    }
}
