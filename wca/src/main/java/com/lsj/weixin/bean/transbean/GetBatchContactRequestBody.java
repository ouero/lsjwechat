package com.lsj.weixin.bean.transbean;

import com.alibaba.fastjson.annotation.JSONField;
import com.lsj.weixin.bean.basebean.BaseRequest;
import com.lsj.weixin.bean.basebean.ChatRoom;

import java.util.List;

/**
 * Created by Chan on 2016/8/13.
 */
public class GetBatchContactRequestBody {

    @JSONField(name="BaseRequest")
    private BaseRequest baseRequest;

    @JSONField(name = "Count")
    private String count;

    @JSONField(name = "List")
    private List<ChatRoom> list;

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ChatRoom> getList() {
        return list;
    }

    public void setList(List<ChatRoom> list) {
        this.list = list;
    }
}
