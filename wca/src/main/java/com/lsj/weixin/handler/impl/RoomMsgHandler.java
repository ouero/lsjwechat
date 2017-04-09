package com.lsj.weixin.handler.impl;

import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.handler.MsgHandler;
import com.lsj.weixin.trans.WeiXinTrans;

/**
 * Created by Chan on 2017/1/21.
 */
public class RoomMsgHandler extends MsgHandler {
    public RoomMsgHandler(WeiXinTrans weiXinTrans) {
        super(weiXinTrans);
    }

    @Override
    public boolean handleReceiveMsg(AddMsg addMsg) {
        return !addMsg.getFromUserName().startsWith("@@");
    }
}
