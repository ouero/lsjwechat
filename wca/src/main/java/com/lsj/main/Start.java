package com.lsj.main;

import com.lsj.setting.SystemSetting;
import com.lsj.ui.ImageUtil;
import com.lsj.ui.ViewFrame2;
import com.lsj.weixin.handler.MsgHandlerChain;
import com.lsj.weixin.handler.impl.DefaultUserInfoHandler;
import com.lsj.weixin.thread.CheckLoginThread;
import com.lsj.weixin.thread.SendMsgThread;
import com.lsj.weixin.thread.SyncCheckThread;
import com.lsj.weixin.thread.TimerThread;
import com.lsj.weixin.trans.WeiXinTrans;
import com.lsj.weixin.utils.WeChatUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
/**
 * Created by Chan on 2017/1/15.
 */
public class Start {

    public static void main(String[] args) throws Exception {
        Start start = Start.getInstance();
        start.initView();
        start.setLoginQrCodeImage();
    }

    private WeiXinTrans weiXinTrans;

    private ViewFrame2 viewFrame;

    /**
     * 检测是否登录线程
     */
    private Thread checkLoginThread;
    /**
     * 检测新消息线程
     */
    private Thread syncCheckThread;

    /**
     * 发送消息线程
     */
    private Thread sendMsgThread;
    /**
     * 定时消息线程
     */
    private Thread timerThread;
    /**
     * 初始化界面
     */
    private void initView() {
        viewFrame = new ViewFrame2();
        viewFrame.initView();
    }

    /**
     * 刷新二维码
     *
     * @throws Exception
     */
    public void setLoginQrCodeImage() throws Exception {
        weiXinTrans = new WeiXinTrans();
        weiXinTrans.setUserInfoAndMsgHandler(new DefaultUserInfoHandler(),new MsgHandlerChain());
        String uuid = null;
        for (int i = 0; i < SystemSetting.RETRY_TIME; i++) {
            uuid = weiXinTrans.getLoginUUID();
            if (uuid != null) {
                break;
            }
        }
        if (uuid != null) {
            OutputStream outputStream = new ByteArrayOutputStream();
            WeChatUtil.getQrcodeImg("https://login.weixin.qq.com/l/" +uuid, outputStream);
            viewFrame.getImageLabel().setIcon(new ImageIcon(ImageIO.read(ImageUtil.parse(outputStream))));

            startCheckLogin(weiXinTrans);
        } else {
            throw new Exception("获取二维码错误");
        }
    }

    /**
     * 开启检测是否已登录成功
     *
     * @param weiXinTrans
     */
    private void startCheckLogin(WeiXinTrans weiXinTrans) {
        if (checkLoginThread != null) {//打断旧的线程，终止之
            checkLoginThread.interrupt();
        }
        if(syncCheckThread!=null){
            syncCheckThread.interrupt();
        }
        if(sendMsgThread!=null){
            sendMsgThread.interrupt();
        }
        if(timerThread!=null){
            timerThread.interrupt();
        }
        syncCheckThread=new SyncCheckThread(weiXinTrans);
        sendMsgThread=new SendMsgThread(weiXinTrans);
        timerThread=new TimerThread();
        checkLoginThread = new CheckLoginThread(weiXinTrans,syncCheckThread,sendMsgThread,timerThread);
        checkLoginThread.start();
    }


    private Start() {
    }

    private static class LazyHolder {
        private static final Start INSTANCE = new Start();
    }

    public static final Start getInstance() {
        return LazyHolder.INSTANCE;
    }
}
