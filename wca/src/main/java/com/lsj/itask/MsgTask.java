package com.lsj.itask;

import com.lsj.cahce.SystemSetting;
import com.lsj.weixin.bean.basebean.User;
import com.lsj.weixin.utils.WeChatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chan on 2017/1/22.
 */
public class MsgTask extends MyTask {

    private String id;
    private ArrayList<Step> stepList=new ArrayList<>();
    private List<User> toAddList=new ArrayList<>();

    public MsgTask() {
        this.id = WeChatUtil.getCurrentTime17();
        stepList.add(new Step(id, 0, "请输入定时消息时间，格式：19:20:00","\\d{2}[:：]\\d{2}[:：]\\d{2}"));
        stepList.add(new Step(id, 1, "请输入下图中需要发送消息的好友编号，多个好友以.隔开。\n" +
                "如：1.233.520\n全部群发请回复00，下面还有一个步骤是删除某些不想发的好友。\n回复设置定时消息，重新开始本次设置。","\\d+("+ SystemSetting.SPLIT_BY+"\\d+)*"));
        stepList.add(new Step(id,2,"请输入下图中不需要发送消息的好友编号，多个好友以.隔开。\n如：1.233.520\n回复00跳过此步骤。\n" +
                "回复设置定时消息，重新开始本次设置。","\\d+("+ SystemSetting.SPLIT_BY+"\\d+)*"));
        stepList.add(new Step(id, 3, "请输入消息模板，消息内该好友的备注名可用 [备注] 代替，如好友备注叫李白，那么消息：[备注]新年快乐。会自动转化为：李白新年快乐。下面有个步骤是临时修改备注内容的。\n" +
                "回复设置定时消息，重新开始本次设置。","[\\s\\S]*"));
        stepList.add(new Step(id,4,"请确认使用备注模板替换的名字，修改请回复 数字新备注名，如图片上显示为23李白->李白，回复23李白白，可将李白改成李白白,发出的消息会变成：李白白新年快乐 \n回复00跳过此步骤。\n" +
                "回复设置定时消息，重新开始本次设置。","(\\d+\\S+(\"+ SystemSetting.SPLIT_BY+\"\\d+\\S+)*)(0{2})*"));
    }

    public Step getTimeStep(){
        return stepList.get(0);
    }

    public Step getUserStep(){
        return stepList.get(1);
    }
    public Step getMsgStep(){
        return stepList.get(3);
    }
    public String getTaskString() {
        StringBuilder stringBuilder = new StringBuilder("时间："+getTimeStep().getValue());
        stringBuilder.append("\n").append("消息内容：").append(getMsgStep().getValue()).append("\n")
                .append("发送给下图好友，如李白->李白白 则发出的昵称为李白白");
        return stringBuilder.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Step> getStepList() {
        return stepList;
    }

    public void setStepList(ArrayList<Step> stepList) {
        this.stepList = stepList;
    }

    public List<User> getToAddList() {
        return toAddList;
    }

    public void setToAddList(List<User> toAddList) {
        this.toAddList = toAddList;
    }
}
