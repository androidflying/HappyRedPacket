package com.happy.packets.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.happy.libs.base.BaseActivity;
import com.happy.libs.constant.PermissionConstants;
import com.happy.libs.util.ActivityUtils;
import com.happy.libs.util.AppUtils;
import com.happy.libs.util.DeviceUtils;
import com.happy.libs.util.PermissionUtils;
import com.happy.libs.util.PhoneUtils;
import com.happy.libs.util.ToastUtils;
import com.happy.packets.HappyConstants;
import com.happy.packets.R;
import com.happy.packets.widget.AdvancedWebView;

import java.util.List;

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

    @SuppressLint("MissingPermission")
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
        mWebView.addPermittedHostname("weixin://");
        tv_title.setText(title);
        if (url.equals(HappyConstants.URL_FEEDBACK)) {
            String openid = PhoneUtils.getDeviceId();
            String nickname = "猎手用户(我)";
            String head = "https://support.qq.com/data/53640/2019/0125/1ee875c303f9aaf926b7b3407bb5b1c0.png";
            String clientInfo = DeviceUtils.getSDKVersionName() + "|" + DeviceUtils.getManufacturer() + "|" + DeviceUtils.getModel();
            String clientVersion = AppUtils.getAppVersionName();
            String customInfo = "root["+DeviceUtils.isDeviceRooted()+"]";
            String postData = "clientInfo=" + clientInfo + "&avatar=" + head + "&openid=" + openid
                    + "&nickname=" + nickname + "&clientVersion=" + clientVersion
                    +"&customInfo="+customInfo;
            mWebView.postUrl(url, postData.getBytes());
        } else {
            mWebView.loadUrl(url);
        }
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
        ToastUtils.showLong("onPageError(errorCode = " + errorCode + ",  description = " + description + ",  failingUrl = " + failingUrl + ")");
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
        ToastUtils.showShort("onDownloadRequested(url = " + url + ",  suggestedFilename = " + suggestedFilename + ",  mimeType = " + mimeType + ",  contentLength = " + contentLength + ",  contentDisposition = " + contentDisposition + ",  userAgent = " + userAgent + ")");
    }

    @Override
    public void onExternalPageRequest(String url) {
        try {
            if (url.startsWith("weixin://")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                ActivityUtils.startActivity(intent);
            }
        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
        }
    }


}
