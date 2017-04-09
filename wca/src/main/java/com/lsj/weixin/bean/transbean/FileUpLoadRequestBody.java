package com.lsj.weixin.bean.transbean;

import com.alibaba.fastjson.annotation.JSONField;
import com.lsj.weixin.bean.basebean.BaseRequest;

/**
 * Created by Chan on 2016/8/14.
 */
public class FileUpLoadRequestBody {
    @JSONField(name = "BaseRequest")
    private BaseRequest baseRequest;

    @JSONField(name = "UploadType")
    private String uploadType;

    @JSONField(name = "ClientMediaId")
    private String clientMediaId;

    @JSONField(name = "TotalLen")
    private String totalLen;

    @JSONField(name = "StartPos")
    private String startPos;

    @JSONField(name = "DataLen")
    private String dataLen;

    @JSONField(name = "MediaType")
    private String mediaType;

    @JSONField(name = "FromUserName")
    private String fromUserName;

    @JSONField(name = "ToUserName")
    private String toUserName;

    @JSONField(name = "FileMd5")
    private String fileMd5;

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getClientMediaId() {
        return clientMediaId;
    }

    public void setClientMediaId(String clientMediaId) {
        this.clientMediaId = clientMediaId;
    }

    public String getTotalLen() {
        return totalLen;
    }

    public void setTotalLen(String totalLen) {
        this.totalLen = totalLen;
    }

    public String getStartPos() {
        return startPos;
    }

    public void setStartPos(String startPos) {
        this.startPos = startPos;
    }

    public String getDataLen() {
        return dataLen;
    }

    public void setDataLen(String dataLen) {
        this.dataLen = dataLen;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }
}
