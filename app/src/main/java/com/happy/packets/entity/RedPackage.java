package com.happy.packets.entity;

public class RedPackage {
    //红包被标记的时间
    private long time;
    //红包的发送者
    private String sender;
    //红包的描述
    private String content;
    //收到的红包金额
    private String money;
    //红包的渠道
    private String channel;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
