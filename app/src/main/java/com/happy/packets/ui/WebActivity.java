package com.happy.packets.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.happy.libs.base.BaseActivity;
import com.happy.libs.util.ActivityUtils;
import com.happy.libs.util.ToastUtils;
import com.happy.packets.R;
import com.happy.packets.widget.AdvancedWebView;

public class WebActivity extends BaseActivity implements AdvancedWebView.Listener {

    public static String BUNDLE_URL = "bundle_url";
    public static String BUNDLE_TITLE = "bundle_title";
    private AdvancedWebView mWebView;
    private TextView tv_title;
    private String url;
    private String title;

    @Override
    public void initData(@NonNull Bundle bundle) {
        url = bundle.getString(BUNDLE_URL);
        title = bundle.getString(BUNDLE_TITLE);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mWebView = findViewById(R.id.webView);
        tv_title = findViewById(R.id.tv_title);
        findViewById(R.id.btn_back).setOnClickListener(this);
        mWebView.setListener(this, this);
        mWebView.setGeolocationEnabled(false);
        mWebView.setMixedContentAllowed(true);
        mWebView.setCookiesEnabled(true);
        mWebView.setThirdPartyCookiesEnabled(true);
        tv_title.setText(title);
        mWebView.loadUrl(url);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        ActivityUtils.finishActivity(WebActivity.class, true);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        mWebView.setVisibility(View.INVISIBLE);
        showLoading();
    }

    @Override
    public void onPageFinished(String url) {
        mWebView.setVisibility(View.VISIBLE);
        missLoading();
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        ToastUtils.showShort("onPageError(errorCode = " + errorCode + ",  description = " + description + ",  failingUrl = " + failingUrl + ")");
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
        ToastUtils.showShort("onDownloadRequested(url = " + url + ",  suggestedFilename = " + suggestedFilename + ",  mimeType = " + mimeType + ",  contentLength = " + contentLength + ",  contentDisposition = " + contentDisposition + ",  userAgent = " + userAgent + ")");
    }

    @Override
    public void onExternalPageRequest(String url) {
        ToastUtils.showShort("onExternalPageRequest(url = " + url + ")");
    }


}
