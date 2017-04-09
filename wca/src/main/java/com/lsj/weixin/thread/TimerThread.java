package com.lsj.weixin.thread;

import com.lsj.cahce.QueueData;
import com.lsj.cahce.UserSetting;
import com.lsj.itask.MsgTask;
import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.bean.basebean.User;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Chan on 2017/1/27.
 */
public class TimerThread extends Thread {
    @Override
    public void run() {
        while (!Thread.interrupted()){
           MsgTask msgTask= UserSetting.taskList.poll();
            if(msgTask!=null){
                if(isNowSend(msgTask)) {
                    List<User> userList = msgTask.getToAddList();
                    for (User user1 : userList) {
                        AddMsg addMsg = new AddMsg();
                        addMsg.setToUserName(user1.getUserName());
                        addMsg.setMsgType("1");
                        addMsg.setContent(getRealContent(user1, msgTask.getMsgStep().getValue()));
                        try {
                            QueueData.sendMsgQueue.put(addMsg);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    UserSetting.taskList.add(msgTask);
                }
            }
        }
    }

    private String getRealContent(User user, String contentModel) {
        String nickName = user.getNickName();//该昵称为经过更改的昵称
        contentModel=contentModel.replace("[备注]",nickName);
        return contentModel;
    }

    private boolean isNowSend(MsgTask msgTask){
        String time = msgTask.getTimeStep().getValue();
        String[] times = time.split("[:：]");
        int hour=Integer.valueOf(times[0]);
        int minute=Integer.valueOf(times[1]);
        int second=Integer.valueOf(times[2]);
        long now=System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,second);
        return now - calendar.getTimeInMillis() > 0 && now - calendar.getTimeInMillis() < 1000 * 60 * 2;
    }
}
