package com.example.yrchoi.yurist.NotificationService;

import android.util.Log;


//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by gbkim on 18. 3. 13.
 */

public class FirebaseInstanceIDService /*extends FirebaseInstanceIdService*/ {

    private static final String TAG = "MyFirebaseIIDService";

//    // [START refresh_token]
//    @Override
//    public void onTokenRefresh() {
//        // Get updated InstanceID token.
//        String token = FirebaseInstanceId.getInstance().getToken();
//        Log.d(TAG, "Refreshed token: " + token);
//
//        // 생성등록된 토큰을 개인 앱서버에 보내 저장해 두었다가 추가 뭔가를 하고 싶으면 할 수 있도록 한다.
//        sendRegistrationToServer(token);
//    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        //request
        Request request = new Request.Builder()
                .url("http://localhost/fcm/register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private static final String TAG = "MyFirebaseIDService";
//
//    /*
//    * Called if InstanceID token is updated. This may occur if the security of
//    * the previous token had been compromised. Note that this is called when the Instance
//    * is initially generated so this is where you wiykd retrieve the token
//    * InstanceID 토큰이 업데이트 된 경우 호출됩니다.
//    * 이전 토큰의 보안이 손상된 경우에 발생할 수 있습니다.
//    * 이것은 InstanceID 토큰이 처음 생성 될 때 호출되어 토큰을 검색 할 곳입니다.*/
//
//    @Override
//    public void onTokenRefresh() {
//        // Get updated InstanceID token.
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d(TAG, "Refreshed token: " + refreshedToken);
//
//        /*
//        * If you want to send messages to this application instance or
//        * manage this apps subscriptions on the server side,
//        * send the Instance ID token to your app server.*/
//        sendRegistrationToServer(refreshedToken);
//
//    } // END refresh_token
//
//    /*
//    * Persist token to third-party servers.
//    *
//    * Modify this method to associate the user's FCM InstanceID token with any server-side account
//    * maintained by your application
//    *
//    * @param token The new token. */
//    private void sendRegistrationToServer(String refreshedToken) {
//        // TODO : Implement this method to send token to your app server.
//    }
}
