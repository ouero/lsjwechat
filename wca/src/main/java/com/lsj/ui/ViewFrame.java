package com.lsj.ui;

import com.lsj.main.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Chan on 2017/1/15.
 */
public class ViewFrame {
    private static final int WIDTH = 320;
    private static final int HEIGHT = 320;

    private JLabel imageLabel;

    private JButton jButtonRefresh;

    private JButton jButtonSetting;

    private JButton jButtonLoginOut;

    private JButton center;

    public void initView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            JFrame frame = new JFrame();
            frame.setBackground(Color.white);
            frame.setTitle("测试窗口");
            frame.setSize(WIDTH, HEIGHT);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getLayout();
            Container container = frame.getContentPane();
         //   container.setBackground(Color.white);

            /**
             * 实验
             */
            center=new JButton("哈哈");
            center.setBounds(20,20,50,50);
            center.setPreferredSize(new Dimension(50,50));
            center.setBorder(BorderFactory.createLineBorder(Color.GREEN));



            Box rootBox = Box.createVerticalBox();
            //       rootBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            rootBox.setSize(WIDTH, HEIGHT);
            rootBox.setBackground(Color.WHITE);
            rootBox.add(Box.createVerticalStrut(20));

            Box middleLineBox = Box.createHorizontalBox();
            middleLineBox.setSize(WIDTH, 300);
            middleLineBox.add(Box.createHorizontalStrut(20));

            Box imageBox = Box.createHorizontalBox();
            imageBox.setSize(300, 300);

            imageLabel = new JLabel();
            imageLabel.setSize(300, 300);
            imageLabel.setIcon(ImageUtil.getImageIcon("21.jpg"));
            imageBox.add(imageLabel);
imageBox.add(center);
            Box buttonBox = Box.createVerticalBox();
            buttonBox.setSize(80, 300);
            jButtonRefresh = new JButton(ImageUtil.getImageIcon("refresh.png"));
            jButtonRefresh.setMaximumSize(new Dimension(80, 40));
            jButtonRefresh.setPreferredSize(new Dimension(80, 40));
            jButtonRefresh.setContentAreaFilled(false);
            jButtonRefresh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Start.getInstance().setLoginQrCodeImage();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });

//        jButtonRefresh.setRolloverIcon(ImageUtil.getImageIcon());
            jButtonSetting = new JButton(ImageUtil.getImageIcon("setting.png"));
            jButtonSetting.setMaximumSize(new Dimension(80, 40));
            jButtonSetting.setPreferredSize(new Dimension(80, 40));
            jButtonSetting.setContentAreaFilled(false);
            jButtonLoginOut = new JButton(ImageUtil.getImageIcon("loginOut.png"));
            jButtonLoginOut.setMaximumSize(new Dimension(80, 40));
            jButtonLoginOut.setPreferredSize(new Dimension(80, 40));
            jButtonLoginOut.setContentAreaFilled(false);
            buttonBox.add(jButtonRefresh);
            jButtonRefresh.setDisabledIcon(ImageUtil.getImageIcon("disableRefresh.png"));
            buttonBox.add(Box.createVerticalStrut(20));
            buttonBox.add(jButtonSetting);
            buttonBox.add(Box.createVerticalStrut(20));
            buttonBox.add(jButtonLoginOut);

            middleLineBox.add(imageBox);
            middleLineBox.add(buttonBox);
            rootBox.add(middleLineBox);

            rootBox.add(Box.createVerticalStrut(80));
            container.add(rootBox);

            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);


            //  jButtonRefresh.setEnabled(false);

            System.out.println(frame.getLayout());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }


    public JLabel getImageLabel() {
        return imageLabel;
    }

    public void setImageLabel(JLabel imageLabel) {
        this.imageLabel = imageLabel;
    }

    public JButton getjButtonRefresh() {
        return jButtonRefresh;
    }

    public void setjButtonRefresh(JButton jButtonRefresh) {
        this.jButtonRefresh = jButtonRefresh;
    }

    public JButton getjButtonSetting() {
        return jButtonSetting;
    }

    public void setjButtonSetting(JButton jButtonSetting) {
        this.jButtonSetting = jButtonSetting;
    }

    public JButton getjButtonLoginOut() {
        return jButtonLoginOut;
    }

    public void setjButtonLoginOut(JButton jButtonLoginOut) {
        this.jButtonLoginOut = jButtonLoginOut;
    }


}
