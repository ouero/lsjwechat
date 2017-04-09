package com.lsj.weixin.bean.basebean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Chan on 2016/8/8.
 */
public class SyncKeyKeyVal {
    @JSONField(name = "Key")
    private String key;
    @JSONField(name = "Val")
    private String val;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "SyncKeyKeyVal{" +
                "key='" + key + '\'' +
                ", val='" + val + '\'' +
                '}';
    }

    public static void main(String[] args) {
        SyncKeyKeyVal syncKeyKeyVal=new SyncKeyKeyVal();
        syncKeyKeyVal.setKey("q2");
        syncKeyKeyVal.setVal("dd");
       String ds= JSON.toJSONString(syncKeyKeyVal);
        System.out.println(ds);
    }
}
