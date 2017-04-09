package com.lsj.weixin.bean.transbean;

import com.alibaba.fastjson.annotation.JSONField;
import com.lsj.weixin.bean.basebean.BaseResponse;
import com.lsj.weixin.bean.basebean.User;

import java.util.List;

/**
 * Created by Chan on 2016/8/8.
 */
public class GetBatchContactResponse {
    @JSONField(name = "BaseResponse")
    private BaseResponse baseResponse;

    @JSONField(name = "Count")
    private String count;

    @JSONField(name = "ContactList")
    private List<User> contactList;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
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

    @Override
    public String toString() {
        return "GetBatchContactResponse{" +
                "baseResponse=" + baseResponse +
                ", count='" + count + '\'' +
                ", contactList=" + contactList +
                '}';
    }
}
