package com.lsj.setting.bean;

import com.lsj.weixin.bean.basebean.User;

/**对话类
 * Created by Chan on 2017/4/9.
 */
public class Dialog {
    private User leftUser;

    private User rightUser;

    public Dialog() {

    }

    public Dialog(User leftUser, User rightUser) {
        this.leftUser = leftUser;
        this.rightUser = rightUser;
    }

    public User getLeftUser() {
        return leftUser;
    }

    public void setLeftUser(User leftUser) {
        this.leftUser = leftUser;
    }

    public User getRightUser() {
        return rightUser;
    }

    public void setRightUser(User rightUser) {
        this.rightUser = rightUser;
    }
}
