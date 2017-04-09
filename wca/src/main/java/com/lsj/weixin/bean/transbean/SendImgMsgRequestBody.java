package com.lsj.weixin.bean.transbean;

import com.alibaba.fastjson.annotation.JSONField;
import com.lsj.weixin.bean.basebean.BaseRequest;
import com.lsj.weixin.bean.basebean.ImgMsg;

/**
 * Created by Chan on 2016/8/14.
 */
public class SendImgMsgRequestBody {
    @JSONField(name = "BaseRequest")
    private BaseRequest baseRequest;

    @JSONField(name = "Msg")
    private ImgMsg msg;

    @JSONField(name = "Scene")
    private String scene;

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public ImgMsg getMsg() {
        return msg;
    }

    public void setMsg(ImgMsg msg) {
        this.msg = msg;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
