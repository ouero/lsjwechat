package com.lsj.weixin.handler.impl;

import com.lsj.cahce.UserSetting;
import com.lsj.itask.TaskHandler;
import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.handler.MsgHandler;
import com.lsj.weixin.trans.WeiXinTrans;

/**
 * Created by Chan on 2017/1/21.
 */
public class FileHelpMsgHandler extends MsgHandler {

    public FileHelpMsgHandler(WeiXinTrans weiXinTrans) {
        super(weiXinTrans);
    }

    @Override
    public boolean handleReceiveMsg(AddMsg addMsg) {
        return !(addMsg.getMsgType().equals("1") && isSystemMsg(addMsg));
    }

    /**
     * 判断是否系统消息并处理
     *
     * @param addMsg
     * @return
     */
    private boolean isSystemMsg(AddMsg addMsg) {
        if (!addMsg.getMsgType().equals("1")) {
            return false;
        }
        if (addMsg.getToUserName().equals(UserSetting.fileHelperUser.getUserName())) {
            //TODO 处理文件传输助手消息
            if(UserSetting.handleTuringCmd(addMsg.getContent())){//是指令
                return false;
            }
            if("设置定时消息".equals(addMsg.getContent())||TaskHandler.getCurrentMsgTask()!=null){
                TaskHandler.handle(addMsg.getContent());
                return false;
            }


        }
        return false;
    }



}
