package com.lsj.itask;

import com.lsj.setting.SystemSetting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chan on 2017/1/22.
 */
public class Step {
    private int id;
    private String taskId;
    private String msg;
    private String helpMsg;
    private String value;
    private String valueReg;

    public Step(String taskId, int id, String msg,String valueReg) {
        this.taskId = taskId;
        this.id = id;
        this.msg = msg;
        this.valueReg=valueReg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public boolean setValue(String value) {
        Pattern pattern = Pattern.compile(valueReg);
        Matcher matcher = pattern.matcher(value);
        // 字符串是否与正则表达式相匹]4
        if (matcher.matches()) {
            this.value = value;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String reg="(\\d+\\S+("+ SystemSetting.SPLIT_BY+"\\d+\\S+)*)(0{2})*";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("12汉字，34虎符");
        System.out.println(matcher.matches());
    }

    public String getHelpMsg() {
        return helpMsg;
    }

    public void setHelpMsg(String helpMsg) {
        this.helpMsg = helpMsg;
    }

    public String getValueReg() {
        return valueReg;
    }

    public void setValueReg(String valueReg) {
        this.valueReg = valueReg;
    }
}
