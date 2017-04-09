package com.lsj.weixin.handler.impl;

import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.handler.MsgHandler;
import com.lsj.weixin.trans.WeiXinTrans;

/**
 * Created by Chan on 2017/1/21.
 */
public class ImgMsgHandler extends MsgHandler {
    public ImgMsgHandler(WeiXinTrans weiXinTrans) {
        super(weiXinTrans);
    }

    @Override
    public boolean handleReceiveMsg(AddMsg addMsg) {
            if(addMsg.getMsgType().equals("3")||addMsg.getMsgType().equals("47")){
                weiXinTrans.getImg(addMsg);
            }
        return true;
    }
}
