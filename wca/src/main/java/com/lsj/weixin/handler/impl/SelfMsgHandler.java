package com.lsj.weixin.handler.impl;

import com.lsj.setting.UserSetting;
import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.handler.MsgHandler;
import com.lsj.weixin.trans.WeiXinTrans;

/**
 * Created by Chan on 2017/1/21.
 */
public class SelfMsgHandler extends MsgHandler {

    public SelfMsgHandler(WeiXinTrans weiXinTrans) {
        super(weiXinTrans);
    }

    @Override
    public boolean handleReceiveMsg(AddMsg addMsg) {
        if (addMsg.getFromUserName().equals(UserSetting.Self.getUserName())) {
            if (addMsg.getMsgType().equals("1")) {
                UserSetting.handleTuringCmd(addMsg.getContent());//处理开关聊天机器人
            }
            return false;
        }
        return true;
    }


}
