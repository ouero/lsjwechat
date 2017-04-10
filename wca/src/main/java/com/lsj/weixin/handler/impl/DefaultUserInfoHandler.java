package com.lsj.weixin.handler.impl;

import com.lsj.setting.UserSetting;
import com.lsj.weixin.bean.basebean.User;
import com.lsj.weixin.data.UserData;
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
        UserData.addUser(userList);
        UserSetting.SetDefaultUser(UserData.userList);
    }

    /**
     * 群成员不处理
     * @param userList
     */
    @Override
    public void handleGetBatchUser(List<User> userList) {

    }

}
