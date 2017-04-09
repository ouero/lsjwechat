package com.lsj.weixin.bean.transbean;

import com.alibaba.fastjson.annotation.JSONField;
import com.lsj.weixin.bean.basebean.BaseRequest;
import com.lsj.weixin.bean.basebean.SyncKey;

/**
 * Created by Chan on 2016/8/11.
 */
public class WebSyncRequestBody {
    @JSONField(name="BaseRequest")
    private BaseRequest baseRequest;
    @JSONField(name = "SyncKey")
    private SyncKey syncKey;
    private String rr;

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public SyncKey getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(SyncKey syncKey) {
        this.syncKey = syncKey;
    }

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }
}
