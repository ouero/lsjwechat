package com.lsj.weixin.service;

import com.lsj.cahce.UserSetting;
import com.lsj.weixin.bean.basebean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chan on 2017/1/23.
 */
public class UserService {
    public static List<User> getUserByName(String names){
        List<User> userList=new ArrayList<>();
        if(names.equals("全部")){
            for (User user : UserSetting.userList) {
                if(!user.getUserName().startsWith("@@")){
                    userList.add(user);
                }
            }
            return userList;
        }
        //按逗号隔开的条件
        String[] nameArr=names.split("[,，]");
        for (String s : nameArr) {
            for (User user : UserSetting.userList) {
                if(user.getRemarkName().equals(s)||user.getNickName().equals(s)){
                    if(!userList.contains(user)) {
                        userList.add(user);
                    }
                }
            }
        }
        return userList;
    }
}
