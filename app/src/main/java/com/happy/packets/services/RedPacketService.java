package com.happy.packets.services;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

import com.happy.libs.util.LogUtils;
import com.happy.libs.util.SPUtils;
import com.happy.libs.util.ToastUtils;
import com.happy.packets.HappyConstants;
import com.happy.packets.helper.AccessibilityHelper;

public class RedPacketService extends AccessibilityService  {

    /**
     * 启动服务的回调
     */
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        ToastUtils.showShort("❤自动抢红包❤已打开");
        SPUtils.getInstance().put(HappyConstants.SP_SERVICE_STATE, true);

    }

    /**
     * 监听窗口变化的回调
     *
     * @param accessibilityEvent
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int eventType = accessibilityEvent.getEventType();
        switch (eventType) {
            //通知栏发生变化的时候
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                AccessibilityHelper.watchNotification(accessibilityEvent);
                break;
            //窗口状态发生变化的时候
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                AccessibilityHelper.watchWindows(accessibilityEvent);
                break;
        }
    }

    /**
     * 中断服务的回调
     */
    @Override
    public void onInterrupt() {
        ToastUtils.showShort("❤自动抢红包❤已中断");
        SPUtils.getInstance().put(HappyConstants.SP_SERVICE_STATE, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ToastUtils.showShort("❤自动抢红包❤已停止");
        SPUtils.getInstance().put(HappyConstants.SP_SERVICE_STATE, false);
    }

}
