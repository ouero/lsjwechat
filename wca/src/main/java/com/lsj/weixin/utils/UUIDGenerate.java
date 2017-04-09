package com.lsj.weixin.utils;

import java.util.UUID;

/**
 * Created by Chan on 2016/9/10.
 */
public class UUIDGenerate {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
