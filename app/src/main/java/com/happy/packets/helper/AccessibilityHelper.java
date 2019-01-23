package com.happy.packets.helper;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;

import com.happy.libs.constant.PackagesConstants;
import com.happy.libs.util.SPUtils;
import com.happy.libs.util.ScreenUtils;
import com.happy.libs.util.TimeUtils;
import com.happy.libs.util.Utils;
import com.happy.packets.HappyConstants;
import com.happy.packets.entity.RedPackage;
import com.happy.packets.services.RedPacketService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AccessibilityHelper {

    private static AccessibilityManager accessibilityManager;
    private static AccessibilityNodeInfo rootNodeInfo;
    private static RedPacketService redPacketService;

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
                if (ConfigHelper.getWeiXin() && content.contains(HappyConstants.LUCKY_TAG_WEIXIN)) {
                    ActionHelper.actionNotification(accessibilityEvent.getParcelableData());
                } else if (ConfigHelper.getDingDing() && content.contains(HappyConstants.LUCKY_TAG_DINGDING)) {
                    ActionHelper.actionNotification(accessibilityEvent.getParcelableData());
                } else if (ConfigHelper.getQQ() && content.contains(HappyConstants.LUCKY_TAG_QQ)) {
                    ActionHelper.actionNotification(accessibilityEvent.getParcelableData());
                } else if (ConfigHelper.getWorkWeiXin() && content.contains(HappyConstants.LUCKY_TAG_WORK_WEIXIN)) {
                    ActionHelper.actionNotification(accessibilityEvent.getParcelableData());
                }
            }
        }

    }

    /**
     * 监听窗口状态
     *
     * @param redPacketService
     * @param accessibilityEvent
     */
    public static void watchWindow(RedPacketService redPacketService, AccessibilityEvent accessibilityEvent) {
        AccessibilityHelper.redPacketService = redPacketService;
        if (ConfigHelper.getWeiXin() && getPackageName(accessibilityEvent).equals(PackagesConstants.WECHAT)) {
            if (SPUtils.getInstance().getString(HappyConstants.SP_CURRENT_ACTIVITY).equals(HappyConstants.WEIXIN_LAUNCHER_UI)) {
                findWeiXinLucky();
            }
            if (SPUtils.getInstance().getString(HappyConstants.SP_CURRENT_ACTIVITY).equals(HappyConstants.WEIXIN_LUCKY_UI)) {
                SPUtils.getInstance().put(HappyConstants.SP_HISTORY_STATE, true);
                openWeiXinLucky();
            }
            if (SPUtils.getInstance().getString(HappyConstants.SP_CURRENT_ACTIVITY).equals(HappyConstants.WEIXIN_LUCKY_DETAIL_UI)) {
                seeWeiXinLuckyDetail();
            }
        }
        if (ConfigHelper.getDingDing() && getPackageName(accessibilityEvent).equals(PackagesConstants.DINGDING)) {
        }
        if (ConfigHelper.getQQ() && getPackageName(accessibilityEvent).equals(PackagesConstants.QQ)) {
        }
        if (ConfigHelper.getWorkWeiXin() && getPackageName(accessibilityEvent).equals(PackagesConstants.WORKWEIXIN)) {
        }
    }


    /**
     * 在会话窗口，遍历节点匹配“微信红包”
     */
    private static void findWeiXinLucky() {

        AccessibilityNodeInfo mLuckyNode = getLastNodeByTag(redPacketService.getRootInActiveWindow(), "微信红包");
        if (mLuckyNode != null) {
            //说明有红包，并模拟点击红包操作
            if (needClickByWeiXin(mLuckyNode)) {
                ActionHelper.actionWindowRedPackage(mLuckyNode);
            }
        }
    }

    /**
     * 打开微信红包
     */
    private static void openWeiXinLucky() {
        if (!ConfigHelper.getAlert()) {
            //如果不是防封号，自动点击红包。
            long delay = 300;
            if (ConfigHelper.getDelay()) {
                delay = (SPUtils.getInstance().getInt(HappyConstants.SP_DELAY_TIME) + 1) * 1000;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AccessibilityNodeInfo openInfo = getLastNodeById(redPacketService.getRootInActiveWindow(), "com.tencent.mm:id/cv0");
                    if (openInfo == null) {
                        AccessibilityNodeInfo closeInfo = getLastNodeById(redPacketService.getRootInActiveWindow(), "com.tencent.mm:id/cs9");
                        if (closeInfo != null) {
                            ActionHelper.closeWeiXinLucky(getLastNodeById(redPacketService.getRootInActiveWindow(), "com.tencent.mm:id/cs9"));
                        }
                    } else {
                        ActionHelper.openWeiXinLucky(openInfo);
                    }
                }
            }, delay);

        }
    }


    /**
     * 查看抢的红包详情
     */
    private static void seeWeiXinLuckyDetail() {
        if (SPUtils.getInstance().getBoolean(HappyConstants.SP_HISTORY_STATE, false)) {
            AccessibilityNodeInfo moneyInfo = getLastNodeById(redPacketService.getRootInActiveWindow(), "com.tencent.mm:id/cqv");
            AccessibilityNodeInfo senderInfo = getLastNodeById(redPacketService.getRootInActiveWindow(), "com.tencent.mm:id/cqr");
            if (moneyInfo != null) {
                RedPackage redPackage = new RedPackage();
                redPackage.setMoney(Float.parseFloat(moneyInfo.getText().toString()));
                redPackage.setTime(TimeUtils.getNowString());
                redPackage.setChannel(HappyConstants.TAG_WEIXIN);
                redPackage.setSender(senderInfo.getText().toString());
                redPackage.save();
            }
        }
        if (ConfigHelper.getBack()) {
            AccessibilityNodeInfo backInfo = getLastNodeById(redPacketService.getRootInActiveWindow(), "com.tencent.mm:id/k4");
            if (backInfo != null) {
                SPUtils.getInstance().put(HappyConstants.SP_HISTORY_STATE, false);
                ActionHelper.backWeiXinLucky(backInfo);
            }
        }

    }


    private static AccessibilityNodeInfo getLastNodeById(AccessibilityNodeInfo nodeInfo, String id) {
        rootNodeInfo = nodeInfo;
        if (rootNodeInfo != null) {
            List<AccessibilityNodeInfo> nodes = rootNodeInfo.findAccessibilityNodeInfosByViewId(id);
            if (nodes != null && !nodes.isEmpty()) {
                if (nodes.get(nodes.size() - 1) != null) {
                    return nodes.get(nodes.size() - 1);
                }
            }
        }
        return null;
    }

    private static AccessibilityNodeInfo getLastNodeByTag(AccessibilityNodeInfo nodeInfo, String tag) {
        rootNodeInfo = nodeInfo;
        if (rootNodeInfo != null) {
            List<AccessibilityNodeInfo> nodes = rootNodeInfo.findAccessibilityNodeInfosByText(tag);
            if (nodes != null && !nodes.isEmpty()) {
                if (nodes.get(nodes.size() - 1) != null) {
                    return nodes.get(nodes.size() - 1);
                }
            }
        }
        return null;
    }


    private static boolean needClickByWeiXin(AccessibilityNodeInfo mLuckyNode) {
        //微信版本号7.0.0
        //获取到红包可点击的NodeInfo
        AccessibilityNodeInfo nodeInfo = mLuckyNode.getParent();
        AccessibilityNodeInfo received = getLastNodeByTag(nodeInfo, "已领取");
        AccessibilityNodeInfo completed = getLastNodeByTag(nodeInfo, "已被领完");
        AccessibilityNodeInfo expired = getLastNodeByTag(nodeInfo, "已过期");
        Set<AccessibilityNodeInfo> filterSet = new HashSet<>();
        Set<String> filters = SPUtils.getInstance().getStringSet(HappyConstants.SP_FILTER_CONTENTS);
        if (ConfigHelper.getFilter()) {
            for (String content : filters) {
                filterSet.add(getLastNodeByTag(nodeInfo, content));
            }
        }
        if (nodeInfo != null && received == null && completed == null && expired == null && filterSet.isEmpty()) {
            Rect rect = new Rect();
            nodeInfo.getBoundsInScreen(rect);
            //打开自己发的红包
            //判断nodeInfo的Bounds是否在右边
            if (ConfigHelper.getSelf()) {
                return true;
            } else {
                if (rect.left > ScreenUtils.getScreenWidth() / 3) {
                    return false;
                } else {
                    return true;
                }
            }

        } else {
            return false;
        }
    }


}
