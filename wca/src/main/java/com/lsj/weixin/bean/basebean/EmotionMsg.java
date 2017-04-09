package com.lsj.weixin.bean.basebean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Chan on 2017/1/21.
 */
public class EmotionMsg extends ImgMsg {

    @JSONField(name = "EmojiFlag")
    private String emojiFlag;

    public String getEmojiFlag() {
        return emojiFlag;
    }

    public void setEmojiFlag(String emojiFlag) {
        this.emojiFlag = emojiFlag;
    }
}
