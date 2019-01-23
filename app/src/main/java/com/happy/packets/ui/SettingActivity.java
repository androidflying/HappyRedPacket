package com.happy.packets.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.happy.libs.base.BaseActivity;
import com.happy.libs.constant.PackagesConstants;
import com.happy.libs.util.ActivityUtils;
import com.happy.libs.util.AppUtils;
import com.happy.libs.util.SPUtils;
import com.happy.libs.util.ToastUtils;
import com.happy.packets.HappyConstants;
import com.happy.packets.R;
import com.happy.packets.helper.AccessibilityHelper;
import com.happy.packets.helper.ConfigHelper;
import com.happy.packets.helper.NotificationHelper;
import com.happy.packets.widget.SuperTextView;

public class SettingActivity extends BaseActivity implements SuperTextView.OnSuperTextViewClickListener {


    private LinearLayout ll_content;
    private SuperTextView switch_notification;
    private SuperTextView switch_alert;
    private SuperTextView switch_lock;
    private SuperTextView switch_WeiXin;
    private SuperTextView switch_DingDing;
    private SuperTextView switch_QQ;
    private SuperTextView switch_WorkWeiXin;
    private SuperTextView switch_sound;
    private SuperTextView switch_back;
    private SuperTextView switch_self;
    private SuperTextView switch_delay;
    private SuperTextView switch_reply;
    private SuperTextView switch_filter;

    private AlertDialog alertDialog;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        findViewById(R.id.btn_back).setOnClickListener(this);
        ll_content = findViewById(R.id.ll_content);
        switch_notification = findViewById(R.id.switch_notification);
        switch_alert = findViewById(R.id.switch_alert);
        switch_lock = findViewById(R.id.switch_lock);
        switch_WeiXin = findViewById(R.id.switch_WeiXin);
        switch_DingDing = findViewById(R.id.switch_DingDing);
        switch_QQ = findViewById(R.id.switch_QQ);
        switch_WorkWeiXin = findViewById(R.id.switch_WorkWeiXin);
        switch_sound = findViewById(R.id.switch_sound);
        switch_back = findViewById(R.id.switch_back);
        switch_self = findViewById(R.id.switch_self);
        switch_delay = findViewById(R.id.switch_delay);
        switch_reply = findViewById(R.id.switch_reply);
        switch_filter = findViewById(R.id.switch_filter);

        updateStatus();
        setClickListener();

    }

    private void setClickListener() {
        switch_notification.setOnSuperTextViewClickListener(this)
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SPUtils.getInstance().put(HappyConstants.SP_KEY_NOTIFICATION, isChecked);
                        if (isChecked) {
                            if (AccessibilityHelper.isServiceEnabled()) {
                                NotificationHelper.sendNotificationToMainActivity();
                            } else {
                                NotificationHelper.sendNotificationToOpenAccessibilittyService();
                            }
                        } else {
                            NotificationHelper.closeNotification();
                        }
                    }
                });
        switch_alert.setOnSuperTextViewClickListener(this)
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SPUtils.getInstance().put(HappyConstants.SP_KEY_ALERT, isChecked);
                    }
                });
        switch_lock.setOnSuperTextViewClickListener(this)
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SPUtils.getInstance().put(HappyConstants.SP_KEY_LOCK, isChecked);
                    }
                });
        switch_WeiXin.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                if (AppUtils.isAppInstalled(PackagesConstants.WECHAT)) {
                    superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked());
                } else {
                    ToastUtils.showShort("您还没有安装微信！");
                }

            }
        }).setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (AppUtils.isAppInstalled(PackagesConstants.WECHAT)) {
                    SPUtils.getInstance().put(HappyConstants.SP_KEY_WEIXIN, isChecked);
                } else {
                    ToastUtils.showShort("您还没有安装微信！");
                    buttonView.setChecked(false);
                }

            }
        });
        switch_DingDing.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                if (AppUtils.isAppInstalled(PackagesConstants.DINGDING)) {
                    superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked());
                } else {
                    ToastUtils.showShort("您还没有安装钉钉！");
                }
            }
        })
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (AppUtils.isAppInstalled(PackagesConstants.DINGDING)) {
                            SPUtils.getInstance().put(HappyConstants.SP_KEY_DINGDING, isChecked);
                        } else {
                            ToastUtils.showShort("您还没有安装钉钉！");
                            buttonView.setChecked(false);
                        }
                    }
                });
        switch_QQ.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                if (AppUtils.isAppInstalled(PackagesConstants.QQ)) {
                    superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked());
                } else {
                    ToastUtils.showShort("您还没有安装QQ！");
                }
            }
        })
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (AppUtils.isAppInstalled(PackagesConstants.QQ)) {
                            SPUtils.getInstance().put(HappyConstants.SP_KEY_QQ, isChecked);
                        } else {
                            ToastUtils.showShort("您还没有安装QQ！");
                            buttonView.setChecked(false);
                        }

                    }
                });
        switch_WorkWeiXin.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                if (AppUtils.isAppInstalled(PackagesConstants.WORKWEIXIN)) {
                    superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked());
                } else {
                    ToastUtils.showShort("您还没有安装企业微信！");
                }
            }
        })
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (AppUtils.isAppInstalled(PackagesConstants.WORKWEIXIN)) {
                            SPUtils.getInstance().put(HappyConstants.SP_KEY_WORKWEIXIN, isChecked);
                        } else {
                            ToastUtils.showShort("您还没有安装企业微信！");
                            buttonView.setChecked(false);
                        }

                    }
                });
        switch_sound.setOnSuperTextViewClickListener(this)
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SPUtils.getInstance().put(HappyConstants.SP_KEY_SOUND, isChecked);
                    }
                });
        switch_back.setOnSuperTextViewClickListener(this)
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SPUtils.getInstance().put(HappyConstants.SP_KEY_BACK, isChecked);
                    }
                });
        switch_self.setOnSuperTextViewClickListener(this)
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SPUtils.getInstance().put(HappyConstants.SP_KEY_SELF, isChecked);
                    }
                });
        switch_delay.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                if (superTextView.getSwitchIsChecked()) {
                    superTextView.setSwitchIsChecked(false);
                } else {
                    showDelayChoice(superTextView);
                }
            }
        })
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SPUtils.getInstance().put(HappyConstants.SP_KEY_DELAY, isChecked);
                    }
                });
        switch_reply.setOnSuperTextViewClickListener(this)
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SPUtils.getInstance().put(HappyConstants.SP_KEY_REPLY, isChecked);
                    }
                });
        switch_filter.setOnSuperTextViewClickListener(this)
                .setSwitchCheckedChangeListener(new SuperTextView.OnSwitchCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SPUtils.getInstance().put(HappyConstants.SP_KEY_FILTER, isChecked);
                    }
                });
    }


    /**
     * 更新初始化页面状态
     */
    private void updateStatus() {
        switch_notification.setSwitchIsChecked(ConfigHelper.getNotification());
        switch_alert.setSwitchIsChecked(ConfigHelper.getAlert());
        switch_lock.setSwitchIsChecked(ConfigHelper.getLock());
        switch_WeiXin.setSwitchIsChecked(ConfigHelper.getWeiXin());
        switch_DingDing.setSwitchIsChecked(ConfigHelper.getDingDing());
        switch_QQ.setSwitchIsChecked(ConfigHelper.getQQ());
        switch_WorkWeiXin.setSwitchIsChecked(ConfigHelper.getWorkWeiXin());
        switch_sound.setSwitchIsChecked(ConfigHelper.getSound());
        switch_back.setSwitchIsChecked(ConfigHelper.getBack());
        switch_self.setSwitchIsChecked(ConfigHelper.getSelf());
        switch_delay.setSwitchIsChecked(ConfigHelper.getDelay());
        switch_reply.setSwitchIsChecked(ConfigHelper.getReply());
        switch_filter.setSwitchIsChecked(ConfigHelper.getFilter());
        ll_content.setVisibility(View.VISIBLE);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                ActivityUtils.finishActivity(SettingActivity.class, true);
                break;
            default:
                ToastUtils.showShort("正在升级中...");
        }
    }

    @Override
    public void onClickListener(SuperTextView superTextView) {
        superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked());
    }


    /**
     * 延时选择框
     *
     * @param superTextView
     */
    private void showDelayChoice(final SuperTextView superTextView) {
        final String[] items = {"1s", "2s", "3s", "4s", "5s"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("选择延长的时间");
        alertBuilder.setSingleChoiceItems(items, SPUtils.getInstance().getInt(HappyConstants.SP_DELAY_TIME), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                superTextView.setSwitchIsChecked(true);
                SPUtils.getInstance().put(HappyConstants.SP_DELAY_TIME, i);
                dialogInterface.dismiss();
            }
        });
        alertBuilder.setCancelable(true);

        alertDialog = alertBuilder.create();
        alertDialog.show();
    }

}
