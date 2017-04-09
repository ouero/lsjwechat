package com.lsj.weixin.handler.impl;

import com.lsj.cahce.UserSetting;
import com.lsj.weixin.bean.basebean.User;
import com.lsj.weixin.handler.UserInfoHandler;

import java.util.List;

/**
 * Created by Chan on 2017/1/11.
 */
public class DefaultUserInfoHandler extends UserInfoHandler {

    @Override
    public void handleGetSelfInfo(User user) {
        UserSetting.Self = user;
    }

    @Override
    public void handleGetUser(List<User> userList) {
        UserSetting.addUser(userList);
        UserSetting.SetDefaultUser(userList);
    }


    @Override
    public void handleGetBatchUser(List<User> userList) {

    }

}
