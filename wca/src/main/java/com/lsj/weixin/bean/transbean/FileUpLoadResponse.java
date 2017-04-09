package com.lsj.weixin.bean.transbean;

import com.alibaba.fastjson.annotation.JSONField;
import com.lsj.weixin.bean.basebean.BaseResponse;

/**
 * Created by Chan on 2016/8/14.
 */
public class FileUpLoadResponse {
    @JSONField(name = "BaseResponse")
    private BaseResponse baseResponse;

    @JSONField(name = "MediaId")
    private String mediaId;

    @JSONField(name = "StartPos")
    private String startPos;


    @JSONField(name = "CDNThumbImgHeight")
    private String cDNThumbImgHeight;


    @JSONField(name = "CDNThumbImgWidth")
    private String cDNThumbImgWidth;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getStartPos() {
        return startPos;
    }

    public void setStartPos(String startPos) {
        this.startPos = startPos;
    }

    public String getcDNThumbImgHeight() {
        return cDNThumbImgHeight;
    }

    public void setcDNThumbImgHeight(String cDNThumbImgHeight) {
        this.cDNThumbImgHeight = cDNThumbImgHeight;
    }

    public String getcDNThumbImgWidth() {
        return cDNThumbImgWidth;
    }

    public void setcDNThumbImgWidth(String cDNThumbImgWidth) {
        this.cDNThumbImgWidth = cDNThumbImgWidth;
    }
}
