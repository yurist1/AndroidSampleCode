package com.example.yrchoi.yurist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WebviewInter extends AppCompatActivity {

    private static String JAVASRIPT_OBJ = "javascript_obj";
    private static String BASE_URL = "file:///android_asset/webview.html";
    private WebView webview;
    private TextView textBox;
    private EditText editText;
    private Button sendBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_interact);

        webview = findViewById(R.id.my_web_view);
        textBox = findViewById(R.id.txt_from_web);
        editText = findViewById(R.id.edit_text_to_web);
        sendBtn = findViewById(R.id.btn_send_to_web);

        webview.addJavascriptInterface(new JavascriptInterface(), JAVASRIPT_OBJ);

    }

    private class JavascriptInterface{
        @SuppressLint("JavascriptInterface")
        @android.webkit.JavascriptInterface
        private void textFromWeb(String fromWeb){
            textBox.setText(fromWeb);
        }
    }

}
