package com.lsj.weixin.thread;

import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.bean.basebean.SyncCheckResponse;
import com.lsj.weixin.bean.transbean.WebSyncResponse;
import com.lsj.weixin.trans.WeiXinTrans;

import java.util.List;

/**
 * Created by Chan on 2017/1/11.
 */
public class SyncCheckThread extends Thread {

    private WeiXinTrans weiXinTrans;

    public SyncCheckThread(WeiXinTrans weiXinTrans) {
        this.weiXinTrans = weiXinTrans;
    }

    @Override
    public void run() {
        syncCheck();
    }



    private void syncCheck() {
        while (!Thread.interrupted()) {
            SyncCheckResponse syncCheckResponse = null;
            try {
                syncCheckResponse = weiXinTrans.syncCheck();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (syncCheckResponse == null) {//已登录退出
                break;
            }
            if (!"0".equals(syncCheckResponse.getSelector())) {
                WebSyncResponse webSyncResponse = weiXinTrans.webSync();

                if (webSyncResponse != null && webSyncResponse.getAddMsgList() != null) {
                    List<AddMsg> addMsgList = webSyncResponse.getAddMsgList();
                    weiXinTrans.getMsgHandlerChain().handleReceiveMsg(addMsgList);
                }
            }
        }
    }

}
