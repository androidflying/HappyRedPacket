package com.happy.packets.helper;

import com.happy.libs.constant.PackagesConstants;
import com.happy.libs.util.AppUtils;
import com.happy.libs.util.SPUtils;
import com.happy.packets.HappyConstants;

/**
 * 设置页面的相关操作帮助类
 */
public class ConfigHelper {
    /**
     * 是否显示通知栏
     * @return
     */
    public static boolean getNotification(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_NOTIFICATION, false);
    }

    /**
     * 是否防封号
     * @return
     */
    public static boolean getAlert(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_ALERT, true);
    }

    /**
     * 是否锁屏抢红包
     * @return
     */
    public static boolean getLock(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_LOCK, false);
    }

    /**
     * 是否监听微信
     * @return
     */
    public static boolean getWeiXin(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_WEIXIN, AppUtils.isAppInstalled(PackagesConstants.WECHAT));
    }
    /**
     * 是否监听钉钉
     * @return
     */
    public static boolean getDingDing(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_DINGDING, false);
    }

    /**
     * 是否监听QQ
     * @return
     */
    public static boolean getQQ(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_QQ, false);
    }

    /**
     * 是否监听企业微信
     * @return
     */
    public static boolean getWorkWeiXin(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_WORKWEIXIN, false);
    }

    /**
     * 是否声音提醒
     * @return
     */
    public static boolean getSound(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_SOUND, true);
    }

    /**
     * 是否自动返回
     * @return
     */
    public static boolean getBack(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_BACK, false);
    }


    /**
     * 是否打开自己发的红包
     * @return
     */
    public static boolean getSelf(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_SELF, true);
    }

    /**
     * 是否延时抢红包
     * @return
     */
    public static boolean getDelay(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_DELAY, false);
    }

    /**
     * 是否回复
     * @return
     */
    public static boolean getReply(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_REPLY, false);
    }

    /**
     * 是否过滤红包
     * @return
     */
    public static boolean getFilter(){
        return SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_FILTER, false);
    }



}
