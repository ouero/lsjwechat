package com.lsj.weixin.handler;

import com.lsj.weixin.bean.basebean.User;
import com.lsj.weixin.trans.WeiXinTrans;

import java.util.List;

/**
 * Created by Chan on 2017/1/11.
 */
public abstract class UserInfoHandler {

    private WeiXinTrans weiXinTrans;

    public abstract void handleGetSelfInfo(User user);

    public abstract void handleGetUser(List<User> userList);

    public abstract void handleGetBatchUser(List<User> userList);

    public WeiXinTrans getWeiXinTrans() {
        return weiXinTrans;
    }

    public void setWeiXinTrans(WeiXinTrans weiXinTrans) {
        this.weiXinTrans = weiXinTrans;
    }
}
