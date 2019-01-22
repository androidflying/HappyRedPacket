package com.happy.packets.entity;

import org.litepal.crud.LitePalSupport;

public class RedPackage extends LitePalSupport {
    /**
     * 红包被标记的时间
     */
    private String time;
    /**
     * 红包的发送者
     */
    private String sender;
    /**
     * 收到的红包金额
     */
    private float money;
    /**
     * 红包的渠道
     */
    private String channel;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
