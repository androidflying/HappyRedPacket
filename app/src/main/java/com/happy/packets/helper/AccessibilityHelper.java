package com.happy.packets.helper;

import android.view.accessibility.AccessibilityEvent;

import com.happy.libs.util.SPUtils;
import com.happy.libs.util.ToastUtils;
import com.happy.packets.HappyConstants;

import java.util.List;

public class AccessibilityHelper {

    /**
     * 监听通知栏
     * @param accessibilityEvent
     */
    public static void watchNotification(AccessibilityEvent accessibilityEvent) {
        //获取通知栏的内容
        List<CharSequence> texts = accessibilityEvent.getText();
        ToastUtils.showShort(texts.get(0).toString());
        if (!texts.isEmpty()) {//判断内容是否为空
            //遍历内容文字
            for (CharSequence text : texts) {
                String content = text.toString();
                if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_WEIXIN)&&content.contains("[微信红包]")){
                    //将Intent添加到等待队列

                }
                if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_DINGDING)&&content.contains("")){
                    //将Intent添加到等待队列

                }
                if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_QQ)&&content.contains("[QQ红包]")){
                    //将Intent添加到等待队列

                }
                if (SPUtils.getInstance().getBoolean(HappyConstants.SP_KEY_WORKWEIXIN)&&content.contains("")){
                    //将Intent添加到等待队列

                }
            }
        }

    }

    public static void watchWindows(AccessibilityEvent accessibilityEvent) {

    }
}
