package com.lsj.weixin.bean.basebean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Chan on 2016/8/13.
 */
public class User {

    @JSONField(name = "Uin")
    private String uin;
    /**
     * 用户名称，一个"@"为好友，两个"@"为群组
     */
    @JSONField(name = "UserName")
    private String userName;

    @JSONField(name = "NickName")
    private String nickName;

    @JSONField(name = "HeadImgUrl")
    private String headImgUrl;
    /**
     *  1-好友， 2-群组， 3-公众号
     */
    @JSONField(name = "ContactFlag")
    private String contactFlag;

    @JSONField(name = "MemberCount")
    private String memberCount;

    @JSONField(name = "MemberList")
    private List<User> memberList;

    @JSONField(name = "RemarkName")
    private String remarkName;

    @JSONField(name = "HideInputBarFlag")
    private String hideInputBarFlag;

    @JSONField(name = "Sex")
    private String sex;

    @JSONField(name = "Signature")
    private String signature;

    @JSONField(name = "VerifyFlag")
    private String verifyFlag;

    @JSONField(name = "OwnerUin")
    private String ownerUin;

    @JSONField(name = "PYInitial")
    private String pYInitial;

    @JSONField(name = "PYQuanPin")
    private String pYQuanPin;

    @JSONField(name = "RemarkPYInitial")
    private String remarkPYInitial;

    @JSONField(name = "StarFriend")
    private String starFriend;

    @JSONField(name = "AppAccountFlag")
    private String appAccountFlag;

    @JSONField(name = "Statues")
    private String statues;

    @JSONField(name = "AttrStatus")
    private String attrStatus;

    @JSONField(name = "Province")
    private String province;

    @JSONField(name = "City")
    private String city;

    @JSONField(name = "Alias")
    private String alias;

    @JSONField(name = "SnsFlag")
    private String snsFlag;

    @JSONField(name = "UniFriend")
    private String uniFriend;

    @JSONField(name = "DisplayName")
    private String displayName;

    @JSONField(name = "ChatRoomId")
    private String chatRoomId;

    @JSONField(name = "KeyWord")
    private String keyWord;

    @JSONField(name = "EncryChatRoomId")
    private String encryChatRoomId;



    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }
    /**
     * 用户名称，一个"@"为好友，两个"@"为群组
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 用户名称，一个"@"为好友，两个"@"为群组
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
    /**
     *  1-好友， 2-群组， 3-公众号
     */
    public String getContactFlag() {
        return contactFlag;
    }
    /**
     *  1-好友， 2-群组， 3-公众号
     */
    public void setContactFlag(String contactFlag) {
        this.contactFlag = contactFlag;
    }

    public String getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(String memberCount) {
        this.memberCount = memberCount;
    }

    public List<User> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<User> memberList) {
        this.memberList = memberList;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getHideInputBarFlag() {
        return hideInputBarFlag;
    }

    public void setHideInputBarFlag(String hideInputBarFlag) {
        this.hideInputBarFlag = hideInputBarFlag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getVerifyFlag() {
        return verifyFlag;
    }

    public void setVerifyFlag(String verifyFlag) {
        this.verifyFlag = verifyFlag;
    }

    public String getOwnerUin() {
        return ownerUin;
    }

    public void setOwnerUin(String ownerUin) {
        this.ownerUin = ownerUin;
    }

    public String getpYInitial() {
        return pYInitial;
    }

    public void setpYInitial(String pYInitial) {
        this.pYInitial = pYInitial;
    }

    public String getpYQuanPin() {
        return pYQuanPin;
    }

    public void setpYQuanPin(String pYQuanPin) {
        this.pYQuanPin = pYQuanPin;
    }

    public String getRemarkPYInitial() {
        return remarkPYInitial;
    }

    public void setRemarkPYInitial(String remarkPYInitial) {
        this.remarkPYInitial = remarkPYInitial;
    }

    public String getStarFriend() {
        return starFriend;
    }

    public void setStarFriend(String starFriend) {
        this.starFriend = starFriend;
    }

    public String getAppAccountFlag() {
        return appAccountFlag;
    }

    public void setAppAccountFlag(String appAccountFlag) {
        this.appAccountFlag = appAccountFlag;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }

    public String getAttrStatus() {
        return attrStatus;
    }

    public void setAttrStatus(String attrStatus) {
        this.attrStatus = attrStatus;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSnsFlag() {
        return snsFlag;
    }

    public void setSnsFlag(String snsFlag) {
        this.snsFlag = snsFlag;
    }

    public String getUniFriend() {
        return uniFriend;
    }

    public void setUniFriend(String uniFriend) {
        this.uniFriend = uniFriend;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getEncryChatRoomId() {
        return encryChatRoomId;
    }

    public void setEncryChatRoomId(String encryChatRoomId) {
        this.encryChatRoomId = encryChatRoomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userName.equals(user.userName);

    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "uin='" + uin + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", contactFlag='" + contactFlag + '\'' +
                ", memberCount='" + memberCount + '\'' +
                ", memberList=" + memberList +
                ", remarkName='" + remarkName + '\'' +
                ", hideInputBarFlag='" + hideInputBarFlag + '\'' +
                ", sex='" + sex + '\'' +
                ", signature='" + signature + '\'' +
                ", verifyFlag='" + verifyFlag + '\'' +
                ", ownerUin='" + ownerUin + '\'' +
                ", pYInitial='" + pYInitial + '\'' +
                ", pYQuanPin='" + pYQuanPin + '\'' +
                ", remarkPYInitial='" + remarkPYInitial + '\'' +
                ", starFriend='" + starFriend + '\'' +
                ", appAccountFlag='" + appAccountFlag + '\'' +
                ", statues='" + statues + '\'' +
                ", attrStatus='" + attrStatus + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", alias='" + alias + '\'' +
                ", snsFlag='" + snsFlag + '\'' +
                ", uniFriend='" + uniFriend + '\'' +
                ", displayName='" + displayName + '\'' +
                ", chatRoomId='" + chatRoomId + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", encryChatRoomId='" + encryChatRoomId + '\'' +
                '}';
    }
}
