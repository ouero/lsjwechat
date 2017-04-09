package com.lsj.weixin.bean.transbean;

import com.alibaba.fastjson.annotation.JSONField;
import com.lsj.weixin.bean.basebean.AddMsg;
import com.lsj.weixin.bean.basebean.BaseResponse;
import com.lsj.weixin.bean.basebean.SyncKey;

import java.util.List;

/**
 * Created by Chan on 2016/8/13.
 */
public class WebSyncResponse {
    @JSONField(name = "BaseResponse")
    private BaseResponse baseResponse;

    @JSONField(name = "AddMsgCount")
    private String addMsgCount;

    @JSONField(name = "AddMsgList")
    private List<AddMsg> addMsgList;

    @JSONField(name = "SyncKey")
    private SyncKey syncKey;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public String getAddMsgCount() {
        return addMsgCount;
    }

    public void setAddMsgCount(String addMsgCount) {
        this.addMsgCount = addMsgCount;
    }

    public List<AddMsg> getAddMsgList() {
        return addMsgList;
    }

    public void setAddMsgList(List<AddMsg> addMsgList) {
        this.addMsgList = addMsgList;
    }

    public SyncKey getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(SyncKey syncKey) {
        this.syncKey = syncKey;
    }

    @Override
    public String toString() {
        return "WebSyncResponse{" +
                "baseResponse=" + baseResponse +
                ", addMsgCount='" + addMsgCount + '\'' +
                ", addMsgList=" + addMsgList +
                ", syncKey=" + syncKey +
                '}';
    }
}
