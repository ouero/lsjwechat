package com.lsj.weixin.data;

import com.lsj.weixin.bean.basebean.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Chan on 2017/4/9.
 */
public class UserData {
    /**
     * 所有的好友列表
     */
    public static List<User> userList=new ArrayList<>();

    public static  void addUser(List<User> toAddUserList){
        for (User user1 : toAddUserList) {
            if(user1.getUserName().startsWith("@@")){
                continue;
            }
//            else if(!user1.getVerifyFlag().equals("0")){
//                continue;
//            }
            if(userList.contains(user1)){
                userList.remove(user1);
            }
            userList.add(user1);
        }
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                String name1=o1.getRemarkName().length()==0?o1.getNickName():o1.getRemarkName();
                String name2=o2.getRemarkName().length()==0?o2.getNickName():o2.getRemarkName();
                return  com.ibm.icu.text.Collator.getInstance(com.ibm.icu.util.ULocale.SIMPLIFIED_CHINESE).compare(name1,name2);
            }
        });
    }
}
