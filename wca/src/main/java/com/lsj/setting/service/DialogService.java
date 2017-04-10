package com.lsj.setting.service;

import com.lsj.cache.QueueData;
import com.lsj.setting.UserSetting;
import com.lsj.setting.bean.Dialog;
import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.bean.basebean.User;
import com.lsj.weixin.service.UserService;

/**
 * Created by Chan on 2017/4/9.
 */
public class DialogService {
    /**
     * 获取对话得那个
     * @param userName
     * @return
     */
    public static User getDialogUser(String userName){
        for (Dialog dialog : UserSetting.dialogList) {
            if(dialog.getLeftUser().getUserName().equals(userName)){
                return dialog.getRightUser();
            }else if(dialog.getRightUser().getUserName().equals(userName)){
                return dialog.getLeftUser();
            }
        }
        return null;
    }

    /**
     * 设置对话
     * @param content
     */
    public static void settingDialog(String content){
        if(content.equals("-cp 取消")){
            UserSetting.dialogList.clear();
        }

        String[] names=content.split("[  ]");
        if(names.length<2){
            return ;
        }
        User leftUser= UserService.getUserByRemarkName(names[1]);
        User rightUser=UserService.getUserByRemarkName(names[2]);
        if(leftUser==null||rightUser==null){
            return ;
        }
        Dialog dialog=new Dialog(leftUser,rightUser);
        UserSetting.dialogList.add(dialog);

        AddMsg replay = new AddMsg();
        replay.setToUserName(UserSetting.fileHelperUser.getUserName());
        replay.setMsgType("1");
        replay.setContent("cp设置成功");
        try {
            QueueData.sendMsgQueue.put(replay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
