package com.happy.packets.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.happy.libs.util.Utils;
import com.happy.packets.HappyConstants;
import com.happy.packets.R;
import com.happy.packets.ui.MainActivity;
import com.happy.packets.ui.WebActivity;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;

public class NotificationHelper {
    private static NotificationManager mNotificationManager;
    private static NotificationChannel mNotificationChannel;

    public static final String CHANNEL_ID = "RedPackage";
    private static final String CHANNEL_NAME = "Event Channel";
    private static final String CHANNEL_DESCRIPTION = "this is redPackage channel!";
    private static int NOTIFICATION_ID = 666;
    private static int NOTIFICATION_ALIPAY_ID = 999;

    public static void sendNotificationToMainActivity() {
        mNotificationManager = (NotificationManager) Utils.getApp().getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            mNotificationChannel.setDescription(CHANNEL_DESCRIPTION);
            mNotificationManager.createNotificationChannel(mNotificationChannel);
        }

        Intent mainIntent = new Intent(Utils.getApp(), MainActivity.class);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(Utils.getApp(), 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(Utils.getApp(), CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(Utils.getApp());
            builder.setPriority(NotificationCompat.PRIORITY_LOW);
        }
        builder.setContentTitle("红包猎手已启动");
        builder.setContentText("正在监控红包，发现立即自动抢");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setVisibility(VISIBILITY_PUBLIC);
        builder.setContentIntent(mainPendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(Utils.getApp().getResources(), R.mipmap.ic_launcher));

        Notification notification = builder.build();
        //设置 Notification 的 flags = FLAG_NO_CLEAR
        //FLAG_NO_CLEAR 表示该通知不能被状态栏的清除按钮给清除掉,也不能被手动清除,但能通过 cancel() 方法清除
        //flags 可以通过 |= 运算叠加效果
        notification.flags |= Notification.FLAG_NO_CLEAR;
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    public static void sendNotificationToOpenAccessibilittyService() {
        mNotificationManager = (NotificationManager) Utils.getApp().getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            mNotificationChannel.setDescription(CHANNEL_DESCRIPTION);
            mNotificationManager.createNotificationChannel(mNotificationChannel);
        }

        Intent mainIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(Utils.getApp(), 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(Utils.getApp(), CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(Utils.getApp());
            builder.setPriority(NotificationCompat.PRIORITY_LOW);
        }
        builder.setContentTitle("哎呀，您抢不了红包啦！");
        builder.setContentText("点击开启系统辅助-自动抢红包功能");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setVisibility(VISIBILITY_PUBLIC);
        builder.setContentIntent(mainPendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(Utils.getApp().getResources(), R.mipmap.ic_launcher));

        Notification notification = builder.build();
        //设置 Notification 的 flags = FLAG_NO_CLEAR
        //FLAG_NO_CLEAR 表示该通知不能被状态栏的清除按钮给清除掉,也不能被手动清除,但能通过 cancel() 方法清除
        //flags 可以通过 |= 运算叠加效果
        notification.flags |= Notification.FLAG_NO_CLEAR;
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    public static void sendNotificationToAliPay() {
        mNotificationManager = (NotificationManager) Utils.getApp().getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mNotificationChannel.setDescription(CHANNEL_DESCRIPTION);
            mNotificationManager.createNotificationChannel(mNotificationChannel);
        }
        Bundle bundle = new Bundle();
        bundle.putString(WebActivity.BUNDLE_URL, HappyConstants.URL_ALIPAY);
        bundle.putString(WebActivity.BUNDLE_TITLE, "支付宝活动");
        Intent mainIntent = new Intent(Utils.getApp(), WebActivity.class);
        mainIntent.putExtras(bundle);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(Utils.getApp(), 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(Utils.getApp(), CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(Utils.getApp());
            builder.setPriority(NotificationCompat.PRIORITY_MAX);
        }
        builder.setContentTitle("您有福利已查收");
        builder.setContentText("点击领取【支付宝】红包！");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setVisibility(VISIBILITY_PUBLIC);
        builder.setAutoCancel(true);
        builder.setContentIntent(mainPendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(Utils.getApp().getResources(), R.mipmap.ic_alipay));
        Notification notification = builder.build();
        //设置 Notification 的 flags = FLAG_NO_CLEAR
        //FLAG_NO_CLEAR 表示该通知不能被状态栏的清除按钮给清除掉,也不能被手动清除,但能通过 cancel() 方法清除
        //flags 可以通过 |= 运算叠加效果
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(NOTIFICATION_ALIPAY_ID, notification);
    }


    public static void closeNotification(){
        mNotificationManager = (NotificationManager) Utils.getApp().getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
}
