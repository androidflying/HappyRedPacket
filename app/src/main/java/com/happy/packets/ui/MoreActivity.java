package com.happy.packets.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.happy.libs.base.BaseActivity;
import com.happy.libs.util.ActivityUtils;
import com.happy.libs.util.ToastUtils;
import com.happy.packets.HappyConstants;
import com.happy.packets.R;

public class MoreActivity extends BaseActivity {


    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_more;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_help).setOnClickListener(this);
        findViewById(R.id.btn_rule).setOnClickListener(this);
        findViewById(R.id.btn_feedback).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {

            case R.id.btn_close:
                ActivityUtils.finishActivity(MoreActivity.class, 0, R.anim.activity_close);
                break;
            case R.id.btn_help:
                bundle.putString(WebActivity.BUNDLE_URL, HappyConstants.URL_HELP);
                bundle.putString(WebActivity.BUNDLE_TITLE, "使用帮助");
                ActivityUtils.startActivity(bundle, WebActivity.class);
                break;
            case R.id.btn_rule:
                bundle.putString(WebActivity.BUNDLE_URL, HappyConstants.URL_RULES);
                bundle.putString(WebActivity.BUNDLE_TITLE, "用户协议");
                ActivityUtils.startActivity(bundle, WebActivity.class);
                break;
            case R.id.btn_feedback:
                ToastUtils.showShort("意见反馈");
                break;
            case R.id.btn_update:
                ToastUtils.showShort("检查更新");
                break;
            default:
                ToastUtils.showShort("正在升级中...");
        }
    }
}
