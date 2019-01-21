package com.happy.packets.helper;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

import com.happy.libs.constant.PackagesConstants;
import com.happy.libs.util.LogUtils;
import com.happy.libs.util.SPUtils;
import com.happy.libs.util.ToastUtils;
import com.happy.libs.util.Utils;
import com.happy.packets.HappyConstants;

import java.util.List;

public class AccessibilityHelper {

    private static AccessibilityManager accessibilityManager;

    public static AccessibilityManager getAccessibilityManager() {

        if (accessibilityManager == null) {
            accessibilityManager = (AccessibilityManager) Utils.getApp().getSystemService(Context.ACCESSIBILITY_SERVICE);
        }

        return accessibilityManager;
    }

    /**
     * 获取 RedPacketService 是否启用状态
     *
     * @return
     */
    public static boolean isServiceEnabled() {

        List<AccessibilityServiceInfo> accessibilityServices =
                getAccessibilityManager().getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK);
        for (AccessibilityServiceInfo info : accessibilityServices) {
            if (info.getId().equals(Utils.getApp().getPackageName() + "/.services.RedPacketService")) {
                SPUtils.getInstance().put(HappyConstants.SP_SERVICE_STATE, true);
                return true;
            }
        }
        SPUtils.getInstance().put(HappyConstants.SP_SERVICE_STATE, false);
        return false;
    }


    /**
     * 获取当前监听到的事件的包名
     *
     * @param accessibilityEvent
     * @return
     */
    private static String getPackageName(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getPackageName().toString();
    }

    /**
     * 监听通知栏
     *
     * @param accessibilityEvent
     */
    public static void watchNotification(AccessibilityEvent accessibilityEvent) {
        //获取通知栏的内容
        List<CharSequence> texts = accessibilityEvent.getText();
        //判断内容是否为空
        if (!texts.isEmpty()) {
            //遍历内容文字
            for (CharSequence text : texts) {
                String content = text.toString();
                if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_WEIXIN) && content.contains("[微信红包]")) {
                    ActionHelper.actionNotification(accessibilityEvent.getParcelableData());
                }
                if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_DINGDING) && content.contains("")) {
                    ActionHelper.actionNotification(accessibilityEvent.getParcelableData());
                }
                if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_QQ) && content.contains("[QQ红包]")) {
                    ActionHelper.actionNotification(accessibilityEvent.getParcelableData());
                }
                if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_WORKWEIXIN) && content.contains("")) {
                    ActionHelper.actionNotification(accessibilityEvent.getParcelableData());
                }
            }
        }

    }

    /**
     * 监听窗口状态
     *
     * @param accessibilityEvent
     */
    public static void watchWindow(AccessibilityEvent accessibilityEvent) {
        if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_WEIXIN) && getPackageName(accessibilityEvent).endsWith(PackagesConstants.WECHAT)) {
//            ActionHelper.actionWindowRedPackage(accessibilityEvent);
        }
        if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_DINGDING) && getPackageName(accessibilityEvent).endsWith(PackagesConstants.DINGDING)) {

        }
        if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_QQ) && getPackageName(accessibilityEvent).endsWith(PackagesConstants.QQ)) {

        }
        if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_WORKWEIXIN) && getPackageName(accessibilityEvent).endsWith(PackagesConstants.WORKWEIXIN)) {

        }
    }


    /**
     * 监听窗口内容
     *
     * @param accessibilityEvent
     */
    public static void watchContent(AccessibilityEvent accessibilityEvent) {
        if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_WEIXIN) && getPackageName(accessibilityEvent).endsWith(PackagesConstants.WECHAT)) {

        }
        if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_DINGDING) && getPackageName(accessibilityEvent).endsWith(PackagesConstants.DINGDING)) {

        }
        if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_QQ) && getPackageName(accessibilityEvent).endsWith(PackagesConstants.QQ)) {

        }
        if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_WORKWEIXIN) && getPackageName(accessibilityEvent).endsWith(PackagesConstants.WORKWEIXIN)) {

        }
    }
}
