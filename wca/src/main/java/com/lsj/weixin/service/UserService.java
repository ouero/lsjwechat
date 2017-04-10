package com.lsj.weixin.service;

import com.lsj.weixin.bean.basebean.User;
import com.lsj.weixin.data.UserData;

/**
 * Created by Chan on 2017/1/23.
 */
public class UserService {
    public static User getUserByName(String userName){
        for (User user : UserData.userList) {
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    public static User getUserByRemarkName(String remarkName){
        for (User user : UserData.userList) {
            if(user.getRemarkName().equals(remarkName)){
                return user;
            }else if(user.getNickName().equals(remarkName)){
                return user;
            }
        }
        return null;
    }
}
