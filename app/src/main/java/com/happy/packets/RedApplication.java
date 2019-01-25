package com.happy.packets;

import com.happy.libs.BaseApplication;
import com.happy.packets.ui.MainActivity;
import com.happy.packets.ui.MoreActivity;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import org.litepal.LitePal;

public class RedApplication extends BaseApplication {
    @Override
    protected void childCreate() {
        LitePal.initialize(this);
    }

    @Override
    public void initCrashReport() {
        Beta.canShowUpgradeActs.add(MainActivity.class);
        Beta.canShowUpgradeActs.add(MoreActivity.class);
        Bugly.init(getApplicationContext(), "35b13bf8e6", false);
    }
}
