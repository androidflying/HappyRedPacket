package com.happy.packets.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;

import com.happy.libs.base.BaseActivity;
import com.happy.libs.util.ActivityUtils;
import com.happy.packets.R;
import com.happy.packets.helper.NotificationHelper;

public class SplashActivity extends BaseActivity {
    Handler handler = new Handler();

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void doBusiness() {
        NotificationHelper.sendNotificationToAliPay();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityUtils.startActivity(MainActivity.class);
                ActivityUtils.finishActivity(SplashActivity.class);
            }
        }, 1500);
    }

    @Override
    public void onWidgetClick(View view) {

    }
}
