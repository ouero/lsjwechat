package com.lsj.weixin.bean.transbean;

import com.alibaba.fastjson.annotation.JSONField;
import com.lsj.weixin.bean.basebean.BaseResponse;

/**
 * Created by Chan on 2016/8/13.
 */
public class SendMsgOrImgResponse {
    @JSONField(name = "BaseResponse")
    private BaseResponse baseResponse;

    @JSONField(name = "MsgID")
    private String  msgID;

    @JSONField(name = "LocalID")
    private String  localID;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }
}
