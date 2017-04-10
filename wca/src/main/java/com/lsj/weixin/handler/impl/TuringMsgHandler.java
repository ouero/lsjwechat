package com.lsj.weixin.handler.impl;

import com.lsj.cache.QueueData;
import com.lsj.setting.UserSetting;
import com.lsj.setting.service.DialogService;
import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.bean.basebean.User;
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
        User toUser = DialogService.getDialogUser(addMsg.getFromUserName());
        if (toUser == null) {
            return false;
        }
        if (addMsg.getMsgType().equals("34")) {//声音
            addMsg.setMsgType("1");
            addMsg.setContent("然后呢");
        }
        addMsg.setToUserName(toUser.getUserName());
        try {
            delay(addMsg,toUser);
            QueueData.sendMsgQueue.put(addMsg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 当是机器人发送给用户时，延时
     * @param addMsg
     * @param toUser
     * @throws InterruptedException
     */
    private void delay(AddMsg addMsg,User toUser) throws InterruptedException {
        if(addMsg.getFromUserName().equals(getTuringUserName())&&!toUser.getUserName().equals(getTuringUserName())) {
            int delay;
            if(addMsg.getMsgType().equals("1")){
                delay=500+addMsg.getContent().length()*200;
            }else {
                delay=(int)((1 + Math.random()) * 1000);
            }
            Thread.sleep( delay);
        }
    }
    private String getTuringUserName() {
        return UserSetting.Turing.getUserName();
    }

}
