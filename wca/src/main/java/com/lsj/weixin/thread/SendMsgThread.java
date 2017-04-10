package com.lsj.weixin.thread;

import com.lsj.cache.QueueData;
import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.trans.WeiXinTrans;

/**
 * Created by Chan on 2017/1/21.
 */
public class SendMsgThread extends Thread {
    private WeiXinTrans weiXinTrans;

    public SendMsgThread(WeiXinTrans weiXinTrans) {
        this.weiXinTrans=weiXinTrans;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                AddMsg addMsg= QueueData.sendMsgQueue.take();
                weiXinTrans.sendAddMsg(addMsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public WeiXinTrans getWeiXinTrans() {
        return weiXinTrans;
    }

    public void setWeiXinTrans(WeiXinTrans weiXinTrans) {
        this.weiXinTrans = weiXinTrans;
    }
}
