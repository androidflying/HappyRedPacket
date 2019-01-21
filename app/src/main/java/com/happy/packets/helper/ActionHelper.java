package com.happy.packets.helper;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Parcelable;

import com.happy.libs.util.SPUtils;
import com.happy.libs.util.Utils;
import com.happy.packets.HappyConstants;
import com.happy.packets.R;

public class ActionHelper {

    /**
     * 模拟点击通知栏
     * @param parcelable
     */
    public static void actionNotification(Parcelable parcelable) {
        //逻辑走到这一步一定是监听到了红包事件，可以在此进行提醒
        if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_SOUND)){
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
     */
    public static void actionWindowRedPackage() {

    }
}
