package com.happy.libs.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.happy.libs.util.BarUtils;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    protected View mContentView;
    protected AppCompatActivity mActivity;

    private int fastTime = 200;
    /**
     * 上次点击时间
     */
    private long lastClick = 0;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        //默认实现沉浸式状态栏
        BarUtils.setStatusBarAlpha(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            initData(bundle);
        }
        setRootLayout(bindLayout());
        initView(savedInstanceState, mContentView);
        doBusiness();
    }

    protected void setRootLayout(int layoutId) {
        if (layoutId <= 0) {
            return;
        }
        mContentView = LayoutInflater.from(this).inflate(layoutId, null);
        setContentView(mContentView);
    }


    @Override
    public void onClick(final View view) {
        if (!isFastClick()) {
            onWidgetClick(view);
        }
    }

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= fastTime) {
            lastClick = now;
            return false;
        }
        return true;
    }


    public void showLoading() {
        showLoading("加载中...");
    }

    public void showLoading(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void missLoading() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
