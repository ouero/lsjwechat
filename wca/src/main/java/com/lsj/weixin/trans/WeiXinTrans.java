package com.lsj.weixin.trans;


import com.alibaba.fastjson.JSON;
import com.lsj.setting.SystemSetting;
import com.lsj.weixin.bean.basebean.*;
import com.lsj.weixin.bean.transbean.*;
import com.lsj.weixin.handler.MsgHandlerChain;
import com.lsj.weixin.handler.UserInfoHandler;
import com.lsj.weixin.thread.SyncCheckThread;
import com.lsj.weixin.utils.WeChatUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by Chan on 2016/8/7.
 */
public class WeiXinTrans {

    private Logger logger = Logger.getLogger(WeiXinTrans.class);

    private UserInfoHandler userInfoHandler;
    private MsgHandlerChain msgHandlerChain;
    private User selfInfo;
    private Thread syncCheckThread;
    private List<User> userList = new ArrayList<>();
    private int retryTime = SystemSetting.RETRY_TIME;


    private Map<String, String> cookieMap = new HashMap<>(64);
    private String loginUUID = null;
    private String redirect_uri = null;
    private String pass_ticket = null;
    private String Uin = null;
    private String Sid = null;
    private String Skey = null;
    private SyncKey syncKey;
    private String webwx_data_ticket = null;
    private int fielNo = 0;
    private String subHost = "wx";
    private long lastActiveTime = System.currentTimeMillis();

    public String getLoginUUID() {
        String responseStr = httpGet("https://login.wx.qq.com/jslogin?appid=wx782c26e4c19acffb&redirect_uri=https%3A%2F%2Fwx.qq.com%2Fcgi-bin%2Fmmwebwx-bin%2Fwebwxnewloginpage&fun=new&lang=zh_CN&_" + System.currentTimeMillis());
        if (responseStr != null) {
            loginUUID = responseStr.substring(responseStr.indexOf("\"") + 1, responseStr.length() - 2);
            return loginUUID;
        }
        return null;
    }

    public boolean checkIsLogined() {
        long currnetTime = System.currentTimeMillis();
        String responseStr = httpGet("https://login.weixin.qq.com/cgi-bin/mmwebwx-bin/login?loginicon=true&uuid=" + loginUUID + "&tip=1&r=" + WeChatUtil.getJsNegate(currnetTime) + "&_=" + currnetTime);
        if (responseStr != null && responseStr.indexOf("redirect_uri") != -1) {
            redirect_uri = responseStr.substring(responseStr.indexOf("\"") + 1, responseStr.lastIndexOf("\""));
            String host = redirect_uri.substring(0, redirect_uri.indexOf("com") + 3);
            if (host.indexOf("2") != -1) {
                subHost = "wx2";
            }
            return true;
        }
        return false;
    }


    private String httpGet(String url) {
        System.setProperty("jsse.enableSNIExtension", "false");
        // System.setProperty("https.protocols", "TLSv1.1");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        //       httpget.addHeader("Connection","keep-alive");
        String cookieStr = getCookieStr();
        httpget.setHeader("Connection", "keep-alive");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
        if (cookieStr != null) {
            httpget.setHeader("Cookie", cookieStr);
        }
        CloseableHttpResponse response = null;
        String responseHtml = null;
        try {
            response = httpClient.execute(httpget);
            setCookie(response);
            responseHtml = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("get 请求发起异常" + e.getClass());
        } finally {
            if (response != null) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("关闭请求异常" + e.getClass());
                }
            }
            // logger.error("get请求回复==>" + responseHtml);
            return responseHtml;
        }
    }

    private String httpPost(String url, String json) {
        System.setProperty("jsse.enableSNIExtension", "false");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json, "utf-8");
        httppost.setEntity(stringEntity);
        httppost.setHeader("Cookie", getCookieStr());
        httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
        logger.error("发出post请求==> " + httppost.getURI());
        //   logger.error("body json为==>" + json);
        CloseableHttpResponse response = null;
        String responseHtml = null;
        try {
            response = httpClient.execute(httppost);
            setCookie(response);
            responseHtml = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            logger.error("post 请求异常" + e.getClass());
        } finally {
            if (response != null) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("post 关闭异常" + e.getClass());
                }
            }
            return responseHtml;
        }
    }

    private boolean newLoginPage() {
        String responseStr = httpGet(redirect_uri + "&fun=new&version=v2");
        if (responseStr != null && responseStr.indexOf("pass_ticket") != -1) {
            pass_ticket = WeChatUtil.getXmlValue(responseStr, "pass_ticket");
            Uin = WeChatUtil.getXmlValue(responseStr, "wxuin");
            Sid = WeChatUtil.getXmlValue(responseStr, "wxsid");
            Skey = WeChatUtil.getXmlValue(responseStr, "skey");
            return true;
        } else {
            return false;
        }
    }

    private boolean initPage() {
        Map<String, Object> map = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("Uin", Uin);
        map1.put("Sid", Sid);
        map1.put("Skey", Skey);
        map1.put("Uin", Uin);
        map1.put("DeviceID", WeChatUtil.getDeviceId());
        map.put("BaseRequest", map1);
        String json = JSON.toJSONString(map);
        String responseStr = httpPost("https://" + subHost + ".qq.com/cgi-bin/mmwebwx-bin/webwxinit?r=" + System.currentTimeMillis() + "&lang=zh_CN&pass_ticket=" + pass_ticket, json);

        if (responseStr != null) {
            InitPageResponse initPageResponse = JSON.parseObject(responseStr, InitPageResponse.class);
            syncKey = initPageResponse.getSyncKey();
            selfInfo = initPageResponse.getUser();
            userInfoHandler.handleGetSelfInfo(selfInfo);
            List<User> newUserList = initPageResponse.getContactList();
            userList.addAll(newUserList);
            userInfoHandler.handleGetUser(userList);
            return true;
        }
        return false;
    }

    private boolean getContactList() {
        String response = httpGet("https://" + subHost + ".qq.com/cgi-bin/mmwebwx-bin/webwxgetcontact?" +
                "pass_ticket=" + pass_ticket +
                "&r=" + System.currentTimeMillis() +
                "&seq=0" +
                "&skey=" + Skey);
        if (response != null) {
            feedDog();
            GetContactResponse getContactResponse = JSON.parseObject(response, GetContactResponse.class);
            userList.addAll(getContactResponse.getMemberList());
            userInfoHandler.handleGetUser(getContactResponse.getMemberList());
            return true;
        }
        return false;
    }


    private boolean getContactBatchList() {
        GetBatchContactRequestBody getBatchContactRequestBody = getAllBatchContactRequestBody();
        if (getBatchContactRequestBody == null) {
            return true;
        }
        String json = JSON.toJSONString(getBatchContactRequestBody);
        String s = httpPost("https://" + subHost + ".qq.com/cgi-bin/mmwebwx-bin/webwxbatchgetcontact?type=ex&r=" + System.currentTimeMillis() + "&lang=zh_CN&pass_ticket=" + pass_ticket, json);
        if (s != null) {
            feedDog();
            GetBatchContactResponse getContactResponse = JSON.parseObject(s, GetBatchContactResponse.class);
            userInfoHandler.handleGetBatchUser(getContactResponse.getContactList());
            return true;
        }
        return false;
    }

    private void setCookie(HttpResponse httpResponse) {
        //   logger.error("----setCookieStore");
        Header headers[] = httpResponse.getHeaders("Set-Cookie");
        if (headers == null || headers.length == 0) {
            //        logger.error("----there are no cookies");
            return;
        }
        String cookie = "";
        for (int i = 0; i < headers.length; i++) {
            cookie += headers[i].getValue();
            if (i != headers.length - 1) {
                cookie += ";";
            }
        }
        String cookies[] = cookie.split(";");
        for (String c : cookies) {
            // logger.error("接收到 cookie"+c);
            c = c.trim();
            if (cookieMap.containsKey(c.split("=")[0])) {
                cookieMap.remove(c.split("=")[0]);
            }
            cookieMap.put(c.split("=")[0], c.split("=").length == 1 ? "" : (c.split("=").length == 2 ? c.split("=")[1] : c.split("=", 2)[1]));
        }
        webwx_data_ticket = cookieMap.get("webwx_data_ticket");
        return;
    }

    public SyncCheckResponse syncCheck() throws InterruptedException {
        String url = "https://webpush." + subHost + ".qq.com/cgi-bin/mmwebwx-bin/synccheck?" +
                "r=" + System.currentTimeMillis() +
                "&skey=" + Skey +
                "&sid=" + Sid +
                "&synckey=" + getSyncStr() +
                "&uin=" + Uin +
                "&deviceid=" + WeChatUtil.getDeviceId() +
                "&_=" + System.currentTimeMillis();
        String response = httpGet(url);
        if (response != null && response.indexOf("retcode:\"1101\"") == -1) {//1101退出的标志
            feedDog();
            String s = response.substring(response.indexOf("=") + 1);
            return JSON.parseObject(s, SyncCheckResponse.class);
        }
        return null;
    }

    public WebSyncResponse webSync() {
        String url = "https://" + subHost + ".qq.com/cgi-bin/mmwebwx-bin/webwxsync?" +
                "sid=" + Sid +
                "&skey=" + Skey +
                "&pass_ticket=" + pass_ticket;

        WebSyncRequestBody webSyncRequestBody = new WebSyncRequestBody();
        webSyncRequestBody.setBaseRequest(getBaseRequest());
        webSyncRequestBody.setSyncKey(syncKey);
        webSyncRequestBody.setRr(WeChatUtil.getJsNegate(System.currentTimeMillis()));
        String requestBody = JSON.toJSONString(webSyncRequestBody);
        String responseStr = httpPost(url, requestBody);
        if (responseStr != null) {
            WebSyncResponse webSyncResponse = JSON.parseObject(responseStr, WebSyncResponse.class);
            syncKey = webSyncResponse.getSyncKey();
            return webSyncResponse;
        }
        return null;
    }

    private synchronized SendMsgOrImgResponse sendMsg(String fromUserName, String toUserName, String content) {
        SendMsgRequestBody sendMsgRequestBody = new SendMsgRequestBody();
        sendMsgRequestBody.setBaseRequest(getBaseRequest());
        Msg msg = new Msg();
        msg.setContent(content);
        msg.setType("1");//文字消息
        msg.setFromUserName(fromUserName);
        msg.setToUserName(toUserName);
        msg.setLocalID(System.currentTimeMillis() + WeChatUtil.getRandomStr(4));
        msg.setClientMsgId(msg.getLocalID());
        sendMsgRequestBody.setMsg(msg);
        String json = JSON.toJSONString(sendMsgRequestBody);
        String s = httpPost("https://" + subHost + ".qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg?pass_ticket=" + pass_ticket, json);
        if (s != null) {
            return JSON.parseObject(s, SendMsgOrImgResponse.class);
        }
        return null;
    }

    private SendMsgOrImgResponse sendMsgLoop(String toUserName, String content) {
        int i = 3;
        while (i > 0) {
            SendMsgOrImgResponse sendMsgResponse = sendMsg(selfInfo.getUserName(), toUserName, content);
            if (sendMsgResponse != null) {
                break;
            }
            WeChatUtil.sleep(1000);
            i--;
        }

        return null;
    }


    private SendMsgOrImgResponse sendImg(String fromUserName, String toUserName, String mediaId) {
        SendImgMsgRequestBody sendImgMsgRequestBody = new SendImgMsgRequestBody();
        sendImgMsgRequestBody.setBaseRequest(getBaseRequest());
        sendImgMsgRequestBody.setScene("0");
        ImgMsg imgMsg = new ImgMsg();
        imgMsg.setToUserName(toUserName);
        imgMsg.setFromUserName(fromUserName);
        imgMsg.setLocalID(System.currentTimeMillis() + WeChatUtil.getRandomStr(4));
        imgMsg.setClientMsgId(imgMsg.getLocalID());
        imgMsg.setType("3");
        imgMsg.setMediaId(mediaId);
        sendImgMsgRequestBody.setMsg(imgMsg);
        String json = JSON.toJSONString(sendImgMsgRequestBody);
        String s = httpPost("https://" + subHost + ".qq.com/cgi-bin/mmwebwx-bin/webwxsendmsgimg?fun=async&f=json&pass_ticket=" + pass_ticket, json);
        if (s != null) {
            SendMsgOrImgResponse sendMsgOrImgResponse = JSON.parseObject(s, SendMsgOrImgResponse.class);
            return sendMsgOrImgResponse;
        }
        return null;
    }

    private FileUpLoadResponse fileUpload(AddMsg addMsg) {
        String response = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        HttpsURLConnection conn = null;
        URL urlObj = null;
        try {
            urlObj = new URL("https://file.wx.qq.com/cgi-bin/mmwebwx-bin/webwxuploadmedia?f=json");
            conn = (HttpsURLConnection) urlObj.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Cookie", getCookieStr());
            conn.setRequestProperty("Accept-Encoding", "*/*");
            conn.setRequestProperty("Accept", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            conn.setRequestProperty("Connection", "keep-alive");
            String boundayRandom = UUID.randomUUID().toString().replace("-", "").substring(16);
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary" + boundayRandom);
            conn.setRequestProperty("Host", "file.wx.qq.com");
            conn.setRequestProperty("Origin", "https://wx.qq.com");
            conn.setRequestProperty("Referer", "https://wx.qq.com/?&lang=zh_CN");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");

            File file = new File(SystemSetting.IMG_PATH + addMsg.getMsgId());
            FileUpLoadRequestBody fileUpLoadRequestBody = new FileUpLoadRequestBody();
            fileUpLoadRequestBody.setUploadType("2");
            fileUpLoadRequestBody.setBaseRequest(getBaseRequest());
            fileUpLoadRequestBody.setClientMediaId(System.currentTimeMillis() + "");
            fileUpLoadRequestBody.setTotalLen(file.length() + "");
            fileUpLoadRequestBody.setStartPos("0");
            fileUpLoadRequestBody.setDataLen(file.length() + "");
            fileUpLoadRequestBody.setMediaType("4");
            fileUpLoadRequestBody.setFromUserName(selfInfo.getUserName());
            fileUpLoadRequestBody.setToUserName(addMsg.getToUserName());
            fileUpLoadRequestBody.setFileMd5(WeChatUtil.getMd5ByFile(file));
            StringBuilder stringBuilder = new StringBuilder();
            String boundary = "------WebKitFormBoundary" + boundayRandom;
            String newLine = "\r\n";
            stringBuilder.append(boundary).append(newLine)
                    .append("Content-Disposition: form-data; name=\"id\"").append(newLine)
                    .append(newLine)
                    .append("WU_FILE_" + fielNo).append(newLine)
                    .append(boundary).append(newLine)
                    .append("Content-Disposition: form-data; name=\"name\"").append(newLine)
                    .append(newLine)
                    .append(file.getName()).append(newLine)
                    .append(boundary).append(newLine)
                    .append("Content-Disposition: form-data; name=\"type\"").append(newLine)
                    .append(newLine);
            if (addMsg.getMsgType().equals("3")) {
                stringBuilder.append("image/jpeg");
            } else if (addMsg.getMsgType().equals("47")) {
                stringBuilder.append("image/gif");
            }
            stringBuilder.append(newLine)
                    .append(boundary).append(newLine)
                    .append("Content-Disposition: form-data; name=\"lastModifiedDate\"").append(newLine)
                    .append(newLine)
                    .append(WeChatUtil.getLastModifiedDate(file.lastModified())).append(newLine)
                    .append(boundary).append(newLine)
                    .append("Content-Disposition: form-data; name=\"size\"").append(newLine)
                    .append(newLine)
                    .append(file.length()).append(newLine)
                    .append(boundary).append(newLine)
                    .append("Content-Disposition: form-data; name=\"mediatype\"").append(newLine)
                    .append(newLine);
            if (addMsg.getMsgType().equals("3")) {
                stringBuilder.append("pic");
            } else if (addMsg.getMsgType().equals("47")) {
                stringBuilder.append("doc");
            }

            stringBuilder.append(newLine)
                    .append(boundary).append(newLine)
                    .append("Content-Disposition: form-data; name=\"uploadmediarequest\"").append(newLine)
                    .append(newLine)
                    .append(JSON.toJSONString(fileUpLoadRequestBody)).append(newLine)
                    .append(boundary).append(newLine)
                    .append("Content-Disposition: form-data; name=\"webwx_data_ticket\"").append(newLine)
                    .append(newLine)
                    .append(webwx_data_ticket).append(newLine)
                    .append(boundary).append(newLine)
                    .append("Content-Disposition: form-data; name=\"pass_ticket\"").append(newLine)
                    .append(newLine)
                    .append(pass_ticket).append(newLine)
                    .append(boundary).append(newLine)
                    .append("Content-Disposition: form-data; name=\"filename\"; filename=\"" + file.getName() + "\"").append(newLine);
            if (addMsg.getMsgType().equals("3")) {
                stringBuilder.append("Content-Type: image/jpeg");
            } else if (addMsg.getMsgType().equals("47")) {
                stringBuilder.append("Content-Type: image/gif");
            }

            stringBuilder.append(newLine)
                    .append(newLine);

            conn.setRequestProperty("Content-Length", file.length() + stringBuilder.length() + 44 + "");
            OutputStream outputStream = new DataOutputStream(conn.getOutputStream());
            outputStream.write(stringBuilder.toString().getBytes("utf-8"));//写入请求参数
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = dis.read(bufferOut)) != -1) {
                outputStream.write(bufferOut, 0, bytes);//写入图片
            }
            outputStream.write(newLine.getBytes());
            outputStream.write((boundary + "--" + newLine).getBytes("utf-8"));//标识请求数据写入结束

            dis.close();
            outputStream.close();

            //读取响应信息
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            response = buffer.toString();
            file.delete();//上传后删除图片
            logger.error("上传图片返回:" + response);
            return JSON.parseObject(response, FileUpLoadResponse.class);
        } catch (IOException e) {
            logger.error("上传图片获取mediaId异常" + e.getClass());
        }
        return null;
    }

    /**
     * 发送表情
     *
     * @param toUserName
     * @param fromUserName
     * @param mediaId
     * @return
     */
    private synchronized SendMsgOrImgResponse sendEmoticon(String fromUserName, String toUserName, String mediaId) {
        SendImgMsgRequestBody sendImgMsgRequestBody = new SendImgMsgRequestBody();
        sendImgMsgRequestBody.setBaseRequest(getBaseRequest());
        sendImgMsgRequestBody.setScene("0");
        EmotionMsg imgMsg = new EmotionMsg();
        imgMsg.setToUserName(toUserName);
        imgMsg.setEmojiFlag("2");
        imgMsg.setFromUserName(fromUserName);
        imgMsg.setLocalID(System.currentTimeMillis() + WeChatUtil.getRandomStr(4));
        imgMsg.setClientMsgId(imgMsg.getLocalID());
        imgMsg.setType("47");//表情编码
        imgMsg.setMediaId(mediaId);
        sendImgMsgRequestBody.setMsg(imgMsg);
        String json = JSON.toJSONString(sendImgMsgRequestBody);
        String s = httpPost("https://" + subHost + ".qq.com/cgi-bin/mmwebwx-bin/webwxsendemoticon?fun=sys&pass_ticket=" + pass_ticket, json);
        if (s != null) {
            SendMsgOrImgResponse sendMsgOrImgResponse = JSON.parseObject(s, SendMsgOrImgResponse.class);
            return sendMsgOrImgResponse;
        }
        return null;
    }


    private synchronized void sendImgOrEmotion(AddMsg addMsg) {
        FileUpLoadResponse fileUpLoadResponse = fileUpload(addMsg);
        if (fileUpLoadResponse != null) {
            if (addMsg.getMsgType().equals("3")) {
                sendImg(selfInfo.getUserName(), addMsg.getToUserName(), fileUpLoadResponse.getMediaId());
            } else if (addMsg.getMsgType().equals("47")) {
                sendEmoticon(selfInfo.getUserName(), addMsg.getToUserName(), fileUpLoadResponse.getMediaId());
            }
        }
    }

    public void sendAddMsg(AddMsg addMsg) {
        if (addMsg.getMsgType().equals("1")) {
            sendMsgLoop(addMsg.getToUserName(), addMsg.getContent());
        } else if (addMsg.getMsgType().equals("3") || addMsg.getMsgType().equals("47")) {
            sendImgOrEmotion(addMsg);
        }
    }


    public void getImg(AddMsg addMsg) {
        String url = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxgetmsgimg?" +
                "&MsgID=" + addMsg.getMsgId() + "&skey=" + Skey;
        if (addMsg.getMsgType().equals("47")) {//动画表情
            url = url + "&type=big";
        }
        System.setProperty("jsse.enableSNIExtension", "false");
        // System.setProperty("https.protocols", "TLSv1.1");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        String cookieStr = getCookieStr();
        httpget.setHeader("Connection", "keep-alive");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
        if (cookieStr != null) {
            httpget.setHeader("Cookie", cookieStr);
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            setCookie(response);
            InputStream inputStream = response.getEntity().getContent();
            File imgDir = new File(SystemSetting.IMG_PATH);
            if (!imgDir.exists()) {
                imgDir.mkdirs();
            }
            OutputStream outputStream = new FileOutputStream(new File(SystemSetting.IMG_PATH + addMsg.getMsgId()));
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                }
            }
            return;
        }
    }

    private String getCookieStr() {
        String cookiesTmp = "";
        for (String key : cookieMap.keySet()) {
            cookiesTmp += key + "=" + cookieMap.get(key) + ";";
            //   logger.error("得到Cookie==>  【" + key + "】=【" + cookieMap.get(key) + "】");
        }
        if (cookiesTmp.length() < 3) {
            return null;
        }
        return cookiesTmp.substring(0, cookiesTmp.length() - 2);
    }


    private String getSyncStr() {
        String syncStr = "";
        List<SyncKeyKeyVal> syncKeyKeyValList = syncKey.getList();
        for (int i = 0; i < syncKeyKeyValList.size(); i++) {
            syncStr = syncStr + syncKeyKeyValList.get(i).getKey() + "_" + syncKeyKeyValList.get(i).getVal();
            if (i < syncKeyKeyValList.size() - 1) {
                syncStr = syncStr + "%7C";
            }
        }
        return syncStr;
    }

    private BaseRequest getBaseRequest() {
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setSid(Sid);
        baseRequest.setSkey(Skey);
        baseRequest.setUin(Uin);
        baseRequest.setDeviceID(WeChatUtil.getDeviceId());
        return baseRequest;
    }

    public GetBatchContactRequestBody getBatchContactRequestBody(List<User> list) {
        List<ChatRoom> chatRoomList = new ArrayList<>();
        for (User user : list) {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setUserName(user.getUserName());
            chatRoom.setChatRoomId("");
            chatRoomList.add(chatRoom);
        }
        GetBatchContactRequestBody getBatchContactRequestBody = new GetBatchContactRequestBody();
        getBatchContactRequestBody.setBaseRequest(getBaseRequest());
        getBatchContactRequestBody.setList(chatRoomList);
        getBatchContactRequestBody.setCount(chatRoomList.size() + "");
        return getBatchContactRequestBody;
    }

    public GetBatchContactRequestBody getAllBatchContactRequestBody() {
        List<User> list = new ArrayList<>();
        for (User user : userList) {
            if (user.getUserName().startsWith("@@")) {
                list.add(user);
            }
        }
        if (list.size() == 0) {
            return null;
        }
        return getBatchContactRequestBody(list);
    }

    public UserInfoHandler getUserInfoHandler() {
        return this.userInfoHandler;
    }


    public long getLastActiveTime() {
        return this.lastActiveTime;
    }


    public void start() {
        syncCheckThread = new Thread(new SyncCheckThread(this));
        syncCheckThread.start();
    }

    public void end() {
        try {
            syncCheckThread.interrupt();
        } catch (Exception e) {
            //TODO停止
        }
    }

    public void getCommonInfo() {
        for (int i = 0; i < retryTime; i++) {
            if (newLoginPage()) {
                break;
            }
        }

        for (int i = 0; i < retryTime; i++) {
            if (initPage()) {
                break;
            }
        }

        for (int i = 0; i < retryTime; i++) {
            if (getContactList()) {
                break;
            }
        }

        for (int i = 0; i < retryTime; i++) {
            if (getContactBatchList()) {
                break;
            }
        }
    }

    /**
     * 更新最后激活时间
     */
    private void feedDog() {
        lastActiveTime = System.currentTimeMillis();
    }

    public User getSelfInfo() {
        return selfInfo;
    }

    public List<User> getUserList() {
        return this.userList;
    }

    public void setSelfInfo(User selfInfo) {
        this.selfInfo = selfInfo;
    }

    public void setUserInfoAndMsgHandler(UserInfoHandler userInfoHandler, MsgHandlerChain msgHandlerChain) {
        this.userInfoHandler = userInfoHandler;
        this.msgHandlerChain = msgHandlerChain;
        userInfoHandler.setWeiXinTrans(this);
        msgHandlerChain.setWeiXinTrans(this);
    }

    public MsgHandlerChain getMsgHandlerChain() {
        return msgHandlerChain;
    }
}
