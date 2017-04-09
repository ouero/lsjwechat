package com.lsj.weixin.bean.basebean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Chan on 2016/8/13.
 */
public class AddMsg {
    public AddMsg(){

    }

    public AddMsg(String toUserName,String content){
        this.toUserName=toUserName;
        this.content=content;
    }

    @JSONField(name = "MsgId")
    private String msgId;
    @JSONField(name = "FromUserName")
    private String fromUserName;

    @JSONField(name = "ToUserName")
    private String toUserName;

    @JSONField(name = "MsgType")
    private String msgType;
    @JSONField(name = "Content")
    private String content;

    @JSONField(name = "Status")
    private String status;

    @JSONField(name = "ImgStatus")
    private String imgStatus;

    @JSONField(name = "CreateTime")
    private String createTime;

    @JSONField(name = "PlayLength")
    private String playLength;


    private String realContent;

    private String realFromUserName;

    private String realUserNameForAT;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(String imgStatus) {
        this.imgStatus = imgStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPlayLength() {
        return playLength;
    }

    public void setPlayLength(String playLength) {
        this.playLength = playLength;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }


    public String getRealFromUserName() {
        return realFromUserName;
    }

    public void setRealFromUserName(String realFromUserName) {
        this.realFromUserName = realFromUserName;
    }


    public String getRealContent() {
        return realContent;
    }

    public void setRealContent(String realContent) {
        this.realContent = realContent;
    }

    public String getRealUserNameForAT() {
        return realUserNameForAT;
    }

    public void setRealUserNameForAT(String realUserNameForAT) {
        this.realUserNameForAT = realUserNameForAT;
    }
}
