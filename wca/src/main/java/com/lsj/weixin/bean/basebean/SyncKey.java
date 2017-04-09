package com.lsj.weixin.bean.basebean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

/**
 * Created by Chan on 2016/8/8.
 */
public class SyncKey {
    @JSONField(name = "Count")
    private String count;
    @JSONField(name="List")
    private ArrayList<SyncKeyKeyVal> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<SyncKeyKeyVal> getList() {
        return list;
    }

    public void setList(ArrayList<SyncKeyKeyVal> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SyncKey{" +
                "Count='" + count + '\'' +
                ", list=" + list +
                '}';
    }
}
