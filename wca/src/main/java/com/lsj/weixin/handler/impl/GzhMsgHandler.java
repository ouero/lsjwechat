package com.lsj.weixin.handler.impl;

import com.lsj.setting.UserSetting;
import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.bean.basebean.User;
import com.lsj.weixin.handler.MsgHandler;
import com.lsj.weixin.service.UserService;
import com.lsj.weixin.trans.WeiXinTrans;

/**公众号消息处理
 * Created by Chan on 2017/1/21.
 */
public class GzhMsgHandler extends MsgHandler {
    public GzhMsgHandler(WeiXinTrans weiXinTrans) {
        super(weiXinTrans);
    }

    @Override
    public boolean handleReceiveMsg(AddMsg addMsg) {
        User user = UserService.getUserByName(addMsg.getFromUserName());
        if (user == null) {
            return false;
        }
        if (!user.getVerifyFlag().equals("0")) {
            return user.getUserName().equals(UserSetting.Turing.getUserName());
        }
        return true;
    }
}
