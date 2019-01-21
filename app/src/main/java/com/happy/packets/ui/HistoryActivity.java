package com.happy.packets.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.happy.libs.base.BaseActivity;
import com.happy.libs.util.ActivityUtils;
import com.happy.packets.R;

public class HistoryActivity extends BaseActivity {

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_history;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        findViewById(R.id.btn_close).setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                ActivityUtils.finishActivity(HistoryActivity.class,0,R.anim.activity_close);
                break;
            default:
        }
    }
}
