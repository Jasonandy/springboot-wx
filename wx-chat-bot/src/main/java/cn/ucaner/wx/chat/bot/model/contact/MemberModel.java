package cn.ucaner.wx.chat.bot.model.contact;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：MemberModel
 * @Description： <p> MemberModel </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:55
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class MemberModel {

    @JSONField(name = "Uin")
    private Long uin;

    @JSONField(name = "UserName")
    private String userName;

    @JSONField(name = "NickName")
    private String nickName;

    @JSONField(name = "AttrStatus")
    private Long attrStatus;

    @JSONField(name = "PYInitial")
    private String pYInitial;

    @JSONField(name = "PYQuanPin")
    private String pYQuanPin;

    @JSONField(name = "RemarkPYInitial")
    private String remarkPYInitial;

    @JSONField(name = "RemarkPYQuanPin")
    private String remarkPYQuanPin;

    @JSONField(name = "MemberStatus")
    private Long memberStatus;

    @JSONField(name = "DisplayName")
    private String displayName;

    @JSONField(name = "KeyWord")
    private String keyWord;

    public MemberModel() {
    }

    public MemberModel(Long uin, String userName, String nickName, Long attrStatus, String pYInitial, String pYQuanPin, String remarkPYInitial, String remarkPYQuanPin, Long memberStatus, String displayName, String keyWord) {
        this.uin = uin;
        this.userName = userName;
        this.nickName = nickName;
        this.attrStatus = attrStatus;
        this.pYInitial = pYInitial;
        this.pYQuanPin = pYQuanPin;
        this.remarkPYInitial = remarkPYInitial;
        this.remarkPYQuanPin = remarkPYQuanPin;
        this.memberStatus = memberStatus;
        this.displayName = displayName;
        this.keyWord = keyWord;
    }

    public Long getUin() {
        return uin;
    }

    public void setUin(Long uin) {
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

    public Long getAttrStatus() {
        return attrStatus;
    }

    public void setAttrStatus(Long attrStatus) {
        this.attrStatus = attrStatus;
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

    public Long getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Long memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
