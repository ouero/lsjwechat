package com.lsj.weixin.bean.basebean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Chan on 2016/8/13.
 */
public class ChatRoom {

    @JSONField(name = "UserName")
    private String userName;

    @JSONField(name = "ChatRoomId")
    private String chatRoomId;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
}
