package com.happy.packets;

import com.happy.libs.BaseApplication;

import org.litepal.LitePal;

public class RedApplication extends BaseApplication {
    @Override
    protected void childCreate() {
        LitePal.initialize(this);
    }

    @Override
    public void initCrashReport() {

    }
}
