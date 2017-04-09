package com.lsj.cahce;

import com.lsj.itask.MsgTask;
import com.lsj.weixin.bean.basebean.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Chan on 2017/1/12.
 */
public class UserSetting {

    /**
     * 自己的信息
     */
    public static User Self;
    /**
     * 文件传输助手
     */
    public static User fileHelperUser;
    /**
     * 智能聊天机器人
     */
    public static User Turing;

    /**
     * 是否需要开启机器人
     */
    public static boolean isNeedTuring = true;
    /**
     * 所有的好友列表
     */
    public static List<User> userList=new ArrayList<>();


    /**
     * 持久化的设置
     */

    /**
     * 智能机器人名称
     */
    public static String TuringName = "小冰";

    /**
     * 开启图灵机器人命令
     */
    public static String StartTuringCmd = "小咸来";

    /**
     * 关闭图灵机器人命令
     */
    public static String EndTuringCmd = "小咸走";

    /**
     * 定时任务列表
     */
    public static LinkedBlockingQueue<MsgTask> taskList = new LinkedBlockingQueue<>();

    public static  void addUser(List<User> toAddUserList){
        for (User user1 : toAddUserList) {
            if(user1.getUserName().startsWith("@@")){
                continue;
            }else if(!user1.getVerifyFlag().equals("0")){
                continue;
            }
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



    public static void SetDefaultUser(List<User> userList1) {

        for (User user : userList1) {
            //设置聊天机器人名称
            if (TuringName != null && TuringName.equals(user.getNickName())) {
                Turing = user;
            }
            //设置文件传输助手
            if ("filehelper".equals(user.getUserName())) {
                fileHelperUser = user;
            }
        }
    }


    /**
     * 处理聊天机器人命令
     *
     * @param content
     */
    public static boolean handleTuringCmd(String content) {
        if (isCmd(content, StartTuringCmd)) {
            isNeedTuring = true;
            return true;
        }
        if (isCmd(content, EndTuringCmd)) {
            UserSetting.isNeedTuring = false;
            return true;
        }
        return false;
    }

    /**
     * 是否命令
     *
     * @param content
     * @return
     */
    private static boolean isCmd(String content, String cmd) {//每个字拆开判断
        for (int i = 0; i < cmd.length(); i++) {
            if (content.indexOf(cmd.substring(i, i + 1)) == -1) {
                return false;
            }
        }
        return true;
    }
}
