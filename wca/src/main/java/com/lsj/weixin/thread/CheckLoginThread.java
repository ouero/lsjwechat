package com.lsj.weixin.thread;

import com.lsj.weixin.trans.WeiXinTrans;

/**
 * Created by Chan on 2017/1/15.
 */
public class CheckLoginThread extends Thread {
    private WeiXinTrans weiXinTrans;

    private Thread syncCheckThread;

    private Thread sendMsgThread;

    private Thread timerThread;

    public CheckLoginThread(WeiXinTrans weiXinTrans,Thread syncCheckThread,Thread sendMsgThread,Thread timerThread) {
        this.weiXinTrans = weiXinTrans;
        this.syncCheckThread=syncCheckThread;
        this.sendMsgThread=sendMsgThread;
        this.timerThread=timerThread;
    }

    @Override
    public void run() {
        boolean isLoginSuccess=false;
        while (!Thread.interrupted()&&!isLoginSuccess) {
          isLoginSuccess=  weiXinTrans.checkIsLogined();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        if(isLoginSuccess) {
            weiXinTrans.getCommonInfo();
            syncCheckThread.start();
            sendMsgThread.start();
            timerThread.start();
        }
    }
}
