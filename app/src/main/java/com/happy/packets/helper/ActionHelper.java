package com.happy.packets.helper;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Parcelable;
import android.view.accessibility.AccessibilityNodeInfo;

public class ActionHelper {

    /**
     * 模拟点击通知栏
     *
     * @param parcelable
     */
    public static void actionNotification(Parcelable parcelable) {
        //逻辑走到这一步一定是监听到了红包事件，可以在此进行提醒
        if (ConfigHelper.getSound()) {
            SoundPoolHelper.playSonud();
        }
        if (parcelable instanceof Notification) {
            Notification notification = (Notification) parcelable;
            try {
                notification.contentIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 模拟打开当前Windows的红包UI
     *
     * @param accessibilityNodeInfo
     */
    public static void actionWindowRedPackage(AccessibilityNodeInfo accessibilityNodeInfo) {
        accessibilityNodeInfo.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
    }


    private static void openDingDingLucky() {

    }

    private static void openQQLucky() {

    }

    private static void openWorkWeiXinLucky() {

    }

    public static void openWeiXinLucky(AccessibilityNodeInfo accessibilityNodeInfo) {
        accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);

    }

    public static void closeWeiXinLucky(AccessibilityNodeInfo accessibilityNodeInfo) {
        accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
    }

    public static void backWeiXinLucky(AccessibilityNodeInfo accessibilityNodeInfo) {
        accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
    }
}
