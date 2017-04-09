package com.lsj.weixin.handler;

import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.trans.WeiXinTrans;

/**
 * Created by Chan on 2017/1/20.
 */
public abstract class MsgHandler {

    protected WeiXinTrans weiXinTrans;

    public MsgHandler(WeiXinTrans weiXinTrans){
        this.weiXinTrans=weiXinTrans;
    }

    /**
     * 返回是否继续往下处理的参数
     * @param addMsg
     * @return
     */
    public abstract boolean handleReceiveMsg(AddMsg addMsg);

    public WeiXinTrans getWeiXinTrans() {
        return weiXinTrans;
    }

    public void setWeiXinTrans(WeiXinTrans weiXinTrans) {
        this.weiXinTrans = weiXinTrans;
    }
}
