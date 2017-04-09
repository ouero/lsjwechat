package com.lsj.weixin.bean.transbean;

import com.alibaba.fastjson.annotation.JSONField;
import com.lsj.weixin.bean.basebean.BaseRequest;
import com.lsj.weixin.bean.basebean.Msg;

/**
 * Created by Chan on 2016/8/13.
 */
public class SendMsgRequestBody {

    @JSONField(name="BaseRequest")
    private BaseRequest baseRequest;

    @JSONField(name = "Msg")
    private Msg msg;

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }
}
