package com.lsj.itask;

import com.lsj.cahce.QueueData;
import com.lsj.cahce.SystemSetting;
import com.lsj.cahce.UserSetting;
import com.lsj.common.util;
import com.lsj.ui.ImageUtil;
import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.bean.basebean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chan on 2017/1/23.
 */
public class TaskHandler {

    public static List<Timer> timerTaskAList = new ArrayList<>();

    /**
     * 当前设置的任务
     */
    private static MsgTask currentMsgTask;

    /**
     * 当前的步数id
     */
    private static int currentStepId = 0;


    public static void setCurrentTask(MsgTask myTask) {
        currentMsgTask = myTask;
    }

    public static MsgTask getCurrentMsgTask() {
        return currentMsgTask;
    }

    public static void init() {
        currentMsgTask = null;
        currentStepId = 0;
    }

    public static void handle(String content) {
        AddMsg addMsg = new AddMsg();
        addMsg.setToUserName(UserSetting.fileHelperUser.getUserName());
        addMsg.setMsgType("1");
        addMsg.setContent(getNextStepString(content));


        try {
            QueueData.sendMsgQueue.put(addMsg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (currentStepId == 1) {//发送好友编码图片
            sendImgList(UserSetting.userList);
        } else if (currentStepId == 2) {//发送已选中的好友图片
            String indexs = currentMsgTask.getStepList().get(1).getValue();
            List<User> toAddlist = currentMsgTask.getToAddList();
            if (indexs.equals("00")) {//全部添加
                toAddlist.addAll(UserSetting.userList);
            } else {
                for (String index : indexs.split(SystemSetting.SPLIT_BY)) {
                    if (Integer.valueOf(index) <= UserSetting.userList.size()) {
                        toAddlist.add(UserSetting.userList.get(Integer.valueOf(index) - 1));
                    }
                }
            }
            sendImgList(toAddlist);//发送已选中的好友列表
        } else if (currentStepId == 3) {
            String indexs = currentMsgTask.getStepList().get(2).getValue();
            if (!indexs.equals("00")) {
                for (String s : indexs.split(SystemSetting.SPLIT_BY)) {
                    if (Integer.valueOf(s) <= currentMsgTask.getToAddList().size()) {
                        currentMsgTask.getToAddList().set(Integer.valueOf(s) - 1, null);
                    }
                }
                for (int i = currentMsgTask.getToAddList().size() - 1; i >= 0; i--) {
                    if (currentMsgTask.getToAddList().get(i) == null) {
                        currentMsgTask.getToAddList().remove(i);
                    }
                }
            }
        } else if (currentStepId == 4) {//发送备注名称消息
            for (User user : currentMsgTask.getToAddList()) {
                String name;
                if (user.getRemarkName() != null && user.getRemarkName().length() != 0) {
                    name = user.getRemarkName();
                } else {
                    name = user.getNickName();
                }
                user.setNickName(util.getSmartName(name));
            }
            sendImgList(currentMsgTask.getToAddList());
        } else if (currentStepId == 5) {
            sendImgList(currentMsgTask.getToAddList());
            init();
        }
    }

    private static void sendImgList(List<User> userList) {
        ImageUtil.createFriendListImg(userList);

        AddMsg img = new AddMsg();
        img.setMsgType("3");
        img.setMsgId("friendsList.jpg");
        img.setToUserName(UserSetting.fileHelperUser.getUserName());
        try {
            QueueData.sendMsgQueue.put(img);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getNextStepString(String content) {
        if ("设置定时消息".equals(content)) {
            currentMsgTask = null;
        }
        if (currentMsgTask == null) {
            currentMsgTask = new MsgTask();
            currentStepId = 0;
            return currentMsgTask.getStepList().get(0).getMsg();
        } else {
            int size = currentMsgTask.getStepList().size();
            Step step = currentMsgTask.getStepList().get(currentStepId);
            if (step.setValue(content)) {
                //符合规范
                currentStepId++;
                if (currentStepId == currentMsgTask.getStepList().size()) {
                    //设置成功
                    if (!"00".equals(content)) {
                        String[] indexs = content.split(SystemSetting.SPLIT_BY);//设置替换备注内容
                        for (String index : indexs) {
                            String regex = "\\d+";
                            Pattern p = Pattern.compile(regex);
                            Matcher m = p.matcher(index);
                            String num = null;
                            while (m.find()) {
                                num = m.group();
                                break;
                            }
                            if (Integer.valueOf(num) <= currentMsgTask.getToAddList().size()) {
                                currentMsgTask.getToAddList().get(Integer.valueOf(num) - 1).setNickName(index.substring(num.length(), index.length()));
                            }
                        }
                    }
                    handleSuccess(currentMsgTask);
                    String taskString = currentMsgTask.getTaskString();

                    return "定时任务生效!" + "\n" + taskString;
                }
            }
            return currentMsgTask.getStepList().get(currentStepId).getMsg();
        }
    }

    public static void handleSuccess(MsgTask msgTask) {
        UserSetting.taskList.offer(msgTask);
    }

}
