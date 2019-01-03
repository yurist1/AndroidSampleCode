package com.example.gbkim.gubonny.SplashImage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.example.gbkim.gubonny.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageLoading = findViewById(R.id.iv_splash_loading);
        Glide.with(this)
                .load(R.drawable.splash_intro_loading2)
                .apply(new RequestOptions().placeholder(R.drawable.splash_intro_loading2))
                .into(new DrawableImageViewTarget(imageLoading));
    }
}
