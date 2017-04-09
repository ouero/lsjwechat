package com.lsj.ui;

import com.lsj.main.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Chan on 2017/1/15.
 */
public class ViewFrame2 {
    private static final int WIDTH = 247;
    private static final int HEIGHT = 267;

    private JLabel imageLabel;

    private JButton jButtonRefresh;

    private JButton jButtonSetting;

    private JButton jButtonLoginOut;

    private JButton center;

    public void initView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            JFrame frame = new JFrame();
            frame.setTitle("厦门老司机微信助手");
            frame.setSize(WIDTH, HEIGHT);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLayeredPane layerPanel = new JLayeredPane();
            layerPanel.setSize(WIDTH,HEIGHT);
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(null);
            imagePanel.setBounds(0, 0, WIDTH, HEIGHT);
            imageLabel = new JLabel();
            imageLabel.setBounds(10, 10, 220, 220);
            imageLabel.setIcon(ImageUtil.getImageIcon("21.jpg"));
            imagePanel.add(imageLabel);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setOpaque(false);
            buttonPanel.setLayout(null);
            buttonPanel.setBounds(0, 0, WIDTH, HEIGHT);

            jButtonRefresh = new JButton(ImageUtil.getImageIcon("refresh.png"));
            jButtonRefresh.setBounds(120, 120, 40, 40);
            jButtonRefresh.setContentAreaFilled(false);
            jButtonRefresh.setVisible(true);
            jButtonRefresh.setOpaque(true);
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

            JLabel jLabel=new JLabel(ImageUtil.getImageIcon("refresh.png"));
            jLabel.setSize(40,40);
            jLabel.setPreferredSize(new Dimension(40,40));
            jLabel.setBounds(120,120,40,40);
            jLabel.setVisible(true);
            jLabel.setOpaque(true);
            buttonPanel.add(jLabel);


//        jButtonRefresh.setRolloverIcon(ImageUtil.getImageIcon());
            jButtonSetting = new JButton(ImageUtil.getImageIcon("setting.png"));
            jButtonSetting.setMaximumSize(new Dimension(80, 40));
            jButtonSetting.setPreferredSize(new Dimension(80, 40));
            jButtonSetting.setContentAreaFilled(false);
            jButtonLoginOut = new JButton(ImageUtil.getImageIcon("loginOut.png"));
            jButtonLoginOut.setMaximumSize(new Dimension(80, 40));
            jButtonLoginOut.setPreferredSize(new Dimension(80, 40));
            jButtonLoginOut.setContentAreaFilled(false);


            jButtonRefresh.setDisabledIcon(ImageUtil.getImageIcon("disableRefresh.png"));
            layerPanel.add(imagePanel, 2);
            layerPanel.add(buttonPanel, 1);

            buttonPanel.setSize(WIDTH,HEIGHT);
            frame.setContentPane(layerPanel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);


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

    public static void main(String[] args) {
        new ViewFrame2().initView();
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
