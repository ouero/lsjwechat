package com.lsj.weixin.handler;

import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.handler.impl.*;
import com.lsj.weixin.trans.WeiXinTrans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chan on 2017/1/20.
 */
public class MsgHandlerChain {
    private WeiXinTrans weiXinTrans;

    /**
     * 消息处理链路
     */
    private List<MsgHandler> msgHandlerList;

    /**
     * 处理消息
     *
     * @param addMsgList
     */
    public void handleReceiveMsg(List<AddMsg> addMsgList) {
        for (AddMsg addMsg : addMsgList) {
            for (MsgHandler msgHandler : msgHandlerList) {
                if (!msgHandler.handleReceiveMsg(addMsg)) {
                    break;
                }
            }
        }
    }

    public void setWeiXinTrans(WeiXinTrans weiXinTrans) {
        this.weiXinTrans = weiXinTrans;
        msgHandlerList = new ArrayList<>();
        msgHandlerList.add(new RoomMsgHandler(this.weiXinTrans));
        msgHandlerList.add(new GzhMsgHandler(this.weiXinTrans));
        msgHandlerList.add(new FileHelpMsgHandler(this.weiXinTrans));
        msgHandlerList.add(new SelfMsgHandler(this.weiXinTrans));
        msgHandlerList.add(new ImgMsgHandler(this.weiXinTrans));
        msgHandlerList.add(new SelfSettingMsgHandler(this.weiXinTrans));
        msgHandlerList.add(new TuringMsgHandler(this.weiXinTrans));
    }
}
