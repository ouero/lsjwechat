package com.lsj.weixin.bean.basebean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Chan on 2016/8/13.
 */
public class Msg {
    @JSONField(name = "Type")
    private String type;

    @JSONField(name = "Content")
    private String content;

    @JSONField(name = "FromUserName")
    private String fromUserName;

    @JSONField(name = "ToUserName")
    private String toUserName;

    @JSONField(name = "LocalID")
    private String localID;

    @JSONField(name = "ClientMsgId")
    private String clientMsgId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }

    public String getClientMsgId() {
        return clientMsgId;
    }

    public void setClientMsgId(String clientMsgId) {
        this.clientMsgId = clientMsgId;
    }
}
