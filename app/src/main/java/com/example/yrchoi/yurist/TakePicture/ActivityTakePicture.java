package com.example.gbkim.gubonny.TakePicture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gbkim.gubonny.R;

/**
 * 참고 블로그
 * http://raon-studio.tistory.com/6
 */
public class ActivityTakePicture extends AppCompatActivity {

    private Button btn_take_picture;
    private ImageView iv_take_picture_photo;
    private int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        btn_take_picture = findViewById(R.id.btn_take_picture);
        iv_take_picture_photo = findViewById(R.id.iv_take_picture_photo);

        btn_take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTakePhotoIntent();
            }
        });
    }

    private void sendTakePhotoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            iv_take_picture_photo.setImageBitmap(imageBitmap);

            if (imageBitmap != null) {
                Toast.makeText(getApplicationContext(), "사진 찍었다.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
