package com.lsj.weixin.handler.impl;

import com.lsj.cahce.QueueData;
import com.lsj.cahce.UserSetting;
import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.handler.MsgHandler;
import com.lsj.weixin.trans.WeiXinTrans;

/**
 * Created by Chan on 2017/1/21.
 */
public class TuringMsgHandler extends MsgHandler {
    public TuringMsgHandler(WeiXinTrans weiXinTrans) {
        super(weiXinTrans);
    }

    @Override
    public boolean handleReceiveMsg(AddMsg addMsg) {
        if (!UserSetting.isNeedTuring) {//不需要机器人
            return false;
        }
        //发什么回什么
//            try {
//                addMsg.setToUserName(addMsg.getFromUserName());
//                QueueData.sendMsgQueue.put(addMsg);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        if (addMsg.getFromUserName().equals(getTuringUserName())) {
            AddMsg readToTuring = QueueData.sendToXiaoBingQueue.poll();
            if (readToTuring != null) {
                try {
                    if (addMsg.getMsgType().equals("34")) {//声音
                        addMsg.setMsgType("1");
                        addMsg.setContent("然后呢");
                    }
                    addMsg.setToUserName(readToTuring.getFromUserName());
                    Thread.sleep((int) ((1 + Math.random()) * 1000));
                    QueueData.sendMsgQueue.put(addMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            addMsg.setToUserName(getTuringUserName());
            try {
                QueueData.sendMsgQueue.put(addMsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            QueueData.sendToXiaoBingQueue.offer(addMsg);
        }
        return false;
    }

    private String getTuringUserName() {
        return UserSetting.Turing.getUserName();
    }

}
