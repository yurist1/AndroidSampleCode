package com.example.gbkim.gubonny.NotificationService;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.example.gbkim.gubonny.R;


/**
 * Created by gbkim on 2018-03-21.
 */

public class MyNotificationManager {

    private Context ctx;

    public static final int NOTIFICATION_ID = 234;
    private String mChannelId = "my_channel_01";
    private String imgURL;

    public MyNotificationManager(Context ctx) {
        this.ctx = ctx;
    }

    public void showNotification(String title, String body, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                ctx,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mNotification = new NotificationCompat.Builder(ctx, mChannelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(ctx.getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSound(defaultSoundUri).setLights(000000255, 500, 200)
                .setPriority(NotificationCompat.PRIORITY_MAX)                                       // 화면에 Push 알림 띄우기
                .setContentTitle(title)                                                             // 알림 제목
                .setContentText("123");             // 내용이 많을 경우 보여줄 문자

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle(mNotification);
        style.bigText(body).setBigContentTitle(title);                                              // 긴 메시지 제목, 내용 설정

        PowerManager powerManager = (PowerManager) ctx.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakeLock.acquire(5000);

        notificationManager.notify(1, mNotification.build());
    }
}
