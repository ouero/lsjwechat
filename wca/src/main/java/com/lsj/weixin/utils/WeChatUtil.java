package com.lsj.weixin.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chan on 2016/8/7.
 */
public class WeChatUtil {
    public static void getQrcodeImg(String text, OutputStream outputStream) {
        int width = 220;
        int height = 220;
        String format = "png";
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
//            MatrixToImageWriter.writeToPath(bitMatrix, format, outputFile.toPath());
            MatrixToImageWriter.writeToStream(bitMatrix,format,outputStream);
        //    Runtime.getRuntime().exec("rundll32 c:\\Windows\\System32\\shimgvw.dll,ImageView_Fullscreen " + outputFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取js取反的时间
     *
     * @param l
     * @return
     */
    public static String getJsNegate(long l) {
        String lstr = Long.toBinaryString(l);
        lstr = lstr.substring(lstr.length() - 31);
        long re = Long.parseLong(lstr, 2) + 1;
        return "-" + re;
    }

    public static String getRandomStr(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(Math.round(Math.random() * 10));
        }
        return stringBuilder.toString();
    }

    public static String getXmlValue(String orgStr, String name) {
        int x = orgStr.indexOf(">", orgStr.indexOf(name));
        int x1 = orgStr.indexOf("</", x);
        String s1 = orgStr.substring(x + 1, x1);
        return s1;
    }

    public static String getDeviceId() {
        return "e" + WeChatUtil.getRandomStr(15);
    }

    public static boolean notBlank(String string) {
        return string != null && string.length() > 0;
    }

    public static String getLastModifiedDate(long l) {
        String s = new Date(l).toString();
        String[] strArr = s.split(" ");
        return strArr[0] + " " + strArr[1] + " " + strArr[2] + " " + strArr[5] + " " + strArr[3] + " GMT+0800 (中国标准时间)";
    }


    public static String getMd5ByFile(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }

    public static String getCurrentTime17(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return simpleDateFormat.format(new Date());
    }

    public static String replaceEmoji(String string) {
        if (string == null) {
            return null;
        }
        string = string.replaceAll("<span.*?></span>", "[e]");
        string = filterEmoji(string);
        return string;
    }


    public static String filterEmoji(String source) {
        if (source != null) {
            Pattern emoji = Pattern.compile("[\\x{10000}-\\x{10FFFF}]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("[emoji]");
                return source;
            }
            return source;
        }
        return source;
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
