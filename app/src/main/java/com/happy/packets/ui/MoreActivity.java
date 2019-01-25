package com.happy.packets.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.happy.libs.base.BaseActivity;
import com.happy.libs.constant.PermissionConstants;
import com.happy.libs.util.ActivityUtils;
import com.happy.libs.util.PermissionUtils;
import com.happy.libs.util.ToastUtils;
import com.happy.packets.HappyConstants;
import com.happy.packets.R;
import com.tencent.bugly.beta.Beta;

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
                checkPermission(bundle);

                break;
            case R.id.btn_update:
                Beta.checkUpgrade(true, false);
                break;
            default:
                ToastUtils.showShort("正在升级中...");
        }
    }

    private void checkPermission(final Bundle bundle) {
        PermissionUtils.permission(PermissionConstants.PHONE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {
                        showTips(shouldRequest);
                    }
                })
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        bundle.putString(WebActivity.BUNDLE_URL, HappyConstants.URL_FEEDBACK);
                        bundle.putString(WebActivity.BUNDLE_TITLE, "意见反馈");
                        ActivityUtils.startActivity(bundle, WebActivity.class);
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showLong("为保证反馈的质量，请允许相关权限！");
                    }
                }).request();
    }

    private void showTips(final PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("权限申请")
                .setMessage("意见反馈需要收集相关信息，以确保更准确有效的处理问题，需要您同意相关权限！")
                .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(true);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(false);
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .create().show();
    }
}
