package com.happy.packets.helper;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.happy.libs.util.Utils;
import com.happy.packets.R;

public class SoundPoolHelper {

    private static SoundPool soundPool;

    public static SoundPool getSoundPool() {
        if (soundPool == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                soundPool = new SoundPool.Builder()
                        .setMaxStreams(4)
                        .build();
            } else {
                soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 1);
            }

        }
        return soundPool;
    }


    public static void playSonud() {
        getSoundPool().load(Utils.getApp(), R.raw.ring, 1);

        getSoundPool().setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                //播放
                soundPool.play(sampleId, 1, 1, 1, 0, 1);
            }
        });
    }
}
