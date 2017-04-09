package com.lsj.ui;

import com.lsj.cahce.SystemSetting;
import com.lsj.weixin.bean.basebean.User;
import com.lsj.weixin.utils.WeChatUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chan on 2017/1/15.
 */
public class ImageUtil {
    public static ImageIcon getImageIcon(String fileName) {
        try {
            InputStream inputStream = ImageUtil.class.getResourceAsStream("/icon/" + fileName);
            return new ImageIcon(ImageIO.read(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ByteArrayInputStream parse(OutputStream out) throws Exception {
        ByteArrayOutputStream baos;
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    public static void createFriendListImg(List<User> userList) {
        try {
            int listNum = userList.size() / 50 + 1;
            int width = listNum * 200 + 20;
            int height = 820;
            // 创建BufferedImage对象
            Font font = new Font("宋体", Font.PLAIN, 16);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            // 获取Graphics2D
//            image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
//            g2d.dispose();
//            g2d = image.createGraphics();
            // 画图
            g2d.setBackground(new Color(255, 255, 255));
            g2d.setPaint(new Color(0, 0, 0));
            g2d.clearRect(0, 0, width, height);
            g2d.setColor(new Color(0, 0, 0));
            g2d.setStroke(new BasicStroke(1));
            for (int x = 1; x <= listNum; x++) {//每一列
                for (int y = 1; y <= 50; y++) {//每一行
                    int i=y+(x-1)*50-1;
                    if(i==userList.size()){//最后一列没有了
                        break;
                    }
                    User user=userList.get(i);
                    String name;
                    if(user.getRemarkName()==null||user.getRemarkName().length()==0){
                        name= user.getNickName();
                    }else {
                        name=user.getRemarkName()+" -> "+user.getNickName();
                    }
                    name=WeChatUtil.replaceEmoji(name);
                    g2d.drawString(y + (x - 1) * 50 +" "+ name, 10 + (x - 1) * 200, 20 + (y - 1) * 16);
                }
            }
            g2d.setFont(font);
            //释放对象
            g2d.dispose();
            // 保存文件
            ImageIO.write(image, "jpg", new File(SystemSetting.IMG_PATH+"friendsList.jpg"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<User> userList=new ArrayList<>();
        User user=new User();
        for (int i = 0; i < 10; i++) {
            user.setRemarkName("备注名");
            user.setNickName("昵称啊。。");
            userList.add(user);
        }

        createFriendListImg(userList);
    }
}
