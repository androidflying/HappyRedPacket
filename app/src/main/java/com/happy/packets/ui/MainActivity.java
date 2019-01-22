package com.happy.packets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.happy.libs.base.BaseActivity;
import com.happy.libs.constant.PackagesConstants;
import com.happy.libs.util.ActivityUtils;
import com.happy.libs.util.AppUtils;
import com.happy.libs.util.ToastUtils;
import com.happy.packets.HappyConstants;
import com.happy.packets.R;
import com.happy.packets.entity.RedPackage;
import com.happy.packets.helper.AccessibilityHelper;
import com.happy.packets.helper.ConfigHelper;
import com.happy.packets.helper.NotificationHelper;
import com.happy.packets.widget.SuperTextView;

import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends BaseActivity implements AccessibilityManager.AccessibilityStateChangeListener {
    private SuperTextView ll_state_off;
    private LinearLayout ll_WeChat;
    private LinearLayout ll_DingDing;
    private TextView tv_WeChat_money;
    private TextView tv_DingDing_money;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        ll_state_off = findViewById(R.id.ll_state_off);
        ll_WeChat = findViewById(R.id.ll_WeChat);
        ll_DingDing = findViewById(R.id.ll_DingDing);
        tv_WeChat_money = findViewById(R.id.tv_WeChat_money);
        tv_DingDing_money = findViewById(R.id.tv_DingDing_money);

        ll_state_off.setOnClickListener(this);
        findViewById(R.id.ll_Lucky).setOnClickListener(this);
        findViewById(R.id.ll_History).setOnClickListener(this);
        findViewById(R.id.ll_AliPay).setOnClickListener(this);
        findViewById(R.id.ll_Setting).setOnClickListener(this);
        findViewById(R.id.ll_More).setOnClickListener(this);
        AccessibilityHelper.getAccessibilityManager().addAccessibilityStateChangeListener(this);
    }

    @Override
    public void doBusiness() {
        updateServiceStatus();
        updateMoneyStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateServiceStatus();
        updateMoneyStatus();
    }


    @Override
    public void onWidgetClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.ll_state_off:
                openAutoClickService();
                break;
            case R.id.ll_Lucky:
                ActivityUtils.startActivity(LuckyActivity.class);
                break;
            case R.id.ll_History:
                ActivityUtils.startActivity(HistoryActivity.class);
                break;
            case R.id.ll_AliPay:
                bundle.putString(WebActivity.BUNDLE_URL, HappyConstants.URL_ALIPAY);
                bundle.putString(WebActivity.BUNDLE_TITLE, "支付宝活动");
                ActivityUtils.startActivity(bundle, WebActivity.class);
                break;
            case R.id.ll_Setting:
                ActivityUtils.startActivity(SettingActivity.class);
                break;
            case R.id.ll_More:
                ActivityUtils.startActivity(MoreActivity.class);
                break;
            default:
                ToastUtils.showShort("正在升级中...");


        }
    }

    /**
     * 打开服务
     */
    private void openAutoClickService() {
        //首先判断有没有安装微信、钉钉、QQ、企业微信；
        if (AppUtils.isAppInstalled(PackagesConstants.WECHAT) ||
                AppUtils.isAppInstalled(PackagesConstants.DINGDING) ||
                AppUtils.isAppInstalled(PackagesConstants.QQ) ||
                AppUtils.isAppInstalled(PackagesConstants.WORKWEIXIN)) {
            //如果安装了其中的某一项，就去打开服务；
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        } else {
            //如果都没有安装，就不跳转，并给出提示。
            ToastUtils.showShort("您还没有安装相关软件！");
        }

    }

    /**
     * 更新服务状态
     */
    private void updateServiceStatus() {
        if (AccessibilityHelper.isServiceEnabled()) {
            if (ConfigHelper.getNotification()) {
                NotificationHelper.sendNotificationToMainActivity();
            }
            ll_state_off.setVisibility(View.GONE);
            if (AppUtils.isAppInstalled(PackagesConstants.WECHAT)) {
                ll_WeChat.setVisibility(View.VISIBLE);
            } else {
                ll_WeChat.setVisibility(View.GONE);
            }
            if (AppUtils.isAppInstalled(PackagesConstants.DINGDING)) {
                ll_DingDing.setVisibility(View.VISIBLE);
            } else {
                ll_DingDing.setVisibility(View.GONE);
            }
        } else {
            if (ConfigHelper.getNotification()) {
                NotificationHelper.sendNotificationToOpenAccessibilittyService();
            }
            ll_state_off.setVisibility(View.VISIBLE);
            ll_WeChat.setVisibility(View.GONE);
            ll_DingDing.setVisibility(View.GONE);
        }
    }


    /**
     * 更新收到的红包金额
     */
    private void updateMoneyStatus() {
        List<RedPackage> allRedPackage = LitePal.findAll(RedPackage.class);
        float total_wx = 0.00f;
        float total_dd = 0.00f;
        float total_qq = 0.00f;
        float total_ww = 0.00f;
        float total_o = 0.00f;

      total_o =  LitePal.sum(RedPackage.class,"money",float.class);
        for (RedPackage redPackage : allRedPackage) {
            switch (redPackage.getChannel()) {
                case HappyConstants.TAG_WEIXIN:
                    total_wx+=redPackage.getMoney();
                    break;
                case HappyConstants.TAG_DINGDING:
                    total_dd+=redPackage.getMoney();
                    break;
                case HappyConstants.TAG_QQ:
                    total_qq+=redPackage.getMoney();
                    break;
                case HappyConstants.TAG_WORK_WEIXIN:
                    total_ww+=redPackage.getMoney();
                    break;
                default:
                    total_o+=redPackage.getMoney();
            }
        }

        tv_WeChat_money.setText("共抢得 " + new DecimalFormat("#0.00").format(total_o) + " 元");
        tv_DingDing_money.setText("共抢得 " + new DecimalFormat("#0.00").format(total_dd) + " 元");
    }

    @Override
    public void onAccessibilityStateChanged(boolean enabled) {
        updateServiceStatus();
    }

    @Override
    protected void onDestroy() {
        //移除监听服务
        AccessibilityHelper.getAccessibilityManager().removeAccessibilityStateChangeListener(this);
        super.onDestroy();
    }

}
