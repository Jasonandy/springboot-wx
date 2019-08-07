package cn.ucaner.wx.chat.bot.model.initialization;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：UserModel
 * @Description： <p> UserModel </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:54
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class UserModel {

    @JSONField(name = "Uin")
    private String uin;

    @JSONField(name = "UserName")
    private String userName;

    @JSONField(name = "NickName")
    private String nickName;

    @JSONField(name = "HeadImgUrl")
    private String headImgUrl;

    @JSONField(name = "RemarkName")
    private String remarkName;

    @JSONField(name = "PYInitial")
    private String pYInitial;

    @JSONField(name = "PYQuanPin")
    private String pYQuanPin;

    @JSONField(name = "RemarkPYInitial")
    private String remarkPYInitial;

    @JSONField(name = "RemarkPYQuanPin")
    private String remarkPYQuanPin;

    @JSONField(name = "HideInputBarFlag")
    private Integer hideInputBarFlag;

    @JSONField(name = "StarFriend")
    private Integer starFriend;

    @JSONField(name = "Sex")
    private Integer sex;

    @JSONField(name = "Signature")
    private String signature;

    @JSONField(name = "AppAccountFlag")
    private Integer appAccountFlag;

    @JSONField(name = "VerifyFlag")
    private Integer verifyFlag;

    @JSONField(name = "ContactFlag")
    private Integer contactFlag;

    @JSONField(name = "WebWxPluginSwitch")
    private Integer webWxPluginSwitch;

    @JSONField(name = "HeadImgFlag")
    private Integer headImgFlag;

    @JSONField(name = "SnsFlag")
    private Integer snsFlag;

    public UserModel() {
    }

    public UserModel(String uin, String userName, String nickName, String headImgUrl, String remarkName, String pYInitial, String pYQuanPin, String remarkPYInitial, String remarkPYQuanPin, Integer hideInputBarFlag, Integer starFriend, Integer sex, String signature, Integer appAccountFlag, Integer verifyFlag, Integer contactFlag, Integer webWxPluginSwitch, Integer headImgFlag, Integer snsFlag) {
        this.uin = uin;
        this.userName = userName;
        this.nickName = nickName;
        this.headImgUrl = headImgUrl;
        this.remarkName = remarkName;
        this.pYInitial = pYInitial;
        this.pYQuanPin = pYQuanPin;
        this.remarkPYInitial = remarkPYInitial;
        this.remarkPYQuanPin = remarkPYQuanPin;
        this.hideInputBarFlag = hideInputBarFlag;
        this.starFriend = starFriend;
        this.sex = sex;
        this.signature = signature;
        this.appAccountFlag = appAccountFlag;
        this.verifyFlag = verifyFlag;
        this.contactFlag = contactFlag;
        this.webWxPluginSwitch = webWxPluginSwitch;
        this.headImgFlag = headImgFlag;
        this.snsFlag = snsFlag;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getUserName() {
        return userName;
    }

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

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
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

    public String getRemarkPYQuanPin() {
        return remarkPYQuanPin;
    }

    public void setRemarkPYQuanPin(String remarkPYQuanPin) {
        this.remarkPYQuanPin = remarkPYQuanPin;
    }

    public Integer getHideInputBarFlag() {
        return hideInputBarFlag;
    }

    public void setHideInputBarFlag(Integer hideInputBarFlag) {
        this.hideInputBarFlag = hideInputBarFlag;
    }

    public Integer getStarFriend() {
        return starFriend;
    }

    public void setStarFriend(Integer starFriend) {
        this.starFriend = starFriend;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getAppAccountFlag() {
        return appAccountFlag;
    }

    public void setAppAccountFlag(Integer appAccountFlag) {
        this.appAccountFlag = appAccountFlag;
    }

    public Integer getVerifyFlag() {
        return verifyFlag;
    }

    public void setVerifyFlag(Integer verifyFlag) {
        this.verifyFlag = verifyFlag;
    }

    public Integer getContactFlag() {
        return contactFlag;
    }

    public void setContactFlag(Integer contactFlag) {
        this.contactFlag = contactFlag;
    }

    public Integer getWebWxPluginSwitch() {
        return webWxPluginSwitch;
    }

    public void setWebWxPluginSwitch(Integer webWxPluginSwitch) {
        this.webWxPluginSwitch = webWxPluginSwitch;
    }

    public Integer getHeadImgFlag() {
        return headImgFlag;
    }

    public void setHeadImgFlag(Integer headImgFlag) {
        this.headImgFlag = headImgFlag;
    }

    public Integer getSnsFlag() {
        return snsFlag;
    }

    public void setSnsFlag(Integer snsFlag) {
        this.snsFlag = snsFlag;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
