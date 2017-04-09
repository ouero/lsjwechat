package com.lsj.weixin.bean.transbean;

import com.alibaba.fastjson.annotation.JSONField;
import com.lsj.weixin.bean.basebean.BaseResponse;
import com.lsj.weixin.bean.basebean.SyncKey;
import com.lsj.weixin.bean.basebean.User;

import java.util.List;

/**
 * Created by Chan on 2016/8/8.
 */
public class InitPageResponse {
    @JSONField(name = "BaseResponse")
    private BaseResponse baseResponse;

    @JSONField(name = "SyncKey")
    private SyncKey syncKey;

    @JSONField(name = "Count")
    private String count;

    @JSONField(name = "ContactList")
    private List<User> contactList;

    @JSONField(name = "User")
    private User user;



    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public SyncKey getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(SyncKey syncKey) {
        this.syncKey = syncKey;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<User> getContactList() {
        return contactList;
    }

    public void setContactList(List<User> contactList) {
        this.contactList = contactList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "InitPageResponse{" +
                "baseResponse=" + baseResponse +
                ", syncKey=" + syncKey +
                ", count='" + count + '\'' +
                ", contactList=" + contactList +
                ", user=" + user +
                '}';
    }
}
