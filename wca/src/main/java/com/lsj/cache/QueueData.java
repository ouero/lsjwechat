package com.lsj.cache;


import com.lsj.weixin.bean.basebean.AddMsg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Chan on 2016/8/31.
 */
public class QueueData {
    /**
     * 发送微信消息队列
     */
    public static LinkedBlockingQueue<AddMsg> sendMsgQueue =new LinkedBlockingQueue();
    /**
     * 发送给小冰的消息队列
     */
//    public static LinkedBlockingQueue<AddMsg> sendToXiaoBingQueue=new LinkedBlockingQueue();
    /**
     * 接受小冰的信息
     */
    public static List<AddMsg> receiveXiaoBingQueue=new ArrayList<>();

}
