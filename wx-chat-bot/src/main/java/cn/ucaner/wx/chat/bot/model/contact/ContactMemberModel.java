package cn.ucaner.wx.chat.bot.model.contact;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
 * @ClassName：ContactMemberModel
 * @Description： <p> ContactMemberModel </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:56
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class ContactMemberModel {

    @JSONField(name = "Uin")
    private Long uin;

    @JSONField(name = "UserName")
    private String userName;

    /**
     * 微信昵称
     */
    @JSONField(name = "NickName")
    private String nickName;

    @JSONField(name = "HeadImgUrl")
    private String headImgUrl;

    @JSONField(name = "ContactFlag")
    private Long contactFlag;

    @JSONField(name = "MemberCount")
    private Long memberCount;

    @JSONField(name = "MemberList")
    private List<MemberModel> memberList;

    /**
     * 备注
     */
    @JSONField(name = "RemarkName")
    private String remarkName;

    @JSONField(name = "HideInputBarFlag")
    private Long hideInputBarFlag;

    @JSONField(name = "Sex")
    private Long sex;

    @JSONField(name = "Signature")
    private String signature;

    @JSONField(name = "VerifyFlag")
    private Long verifyFlag;

    @JSONField(name = "OwnerUin")
    private Long ownerUin;

    @JSONField(name = "PYInitial")
    private String pYInitial;

    @JSONField(name = "PYQuanPin")
    private String pYQuanPin;

    @JSONField(name = "RemarkPYInitial")
    private String remarkPYInitial;

    @JSONField(name = "RemarkPYQuanPin")
    private String remarkPYQuanPin;

    @JSONField(name = "StarFriend")
    private Long starFriend;

    @JSONField(name = "AppAccountFlag")
    private Long appAccountFlag;

    @JSONField(name = "Statues")
    private Long statues;

    @JSONField(name = "AttrStatus")
    private Long attrStatus;

    @JSONField(name = "Province")
    private String province;

    @JSONField(name = "City")
    private String city;

    @JSONField(name = "Alias")
    private String alias;

    @JSONField(name = "SnsFlag")
    private Long snsFlag;

    @JSONField(name = "UniFriend")
    private Long uniFriend;

    @JSONField(name = "DisplayName")
    private String displayName;

    @JSONField(name = "ChatRoomId")
    private Long chatRoomId;

    @JSONField(name = "KeyWord")
    private String keyWord;

    @JSONField(name = "EncryChatRoomId")
    private String encryChatRoomId;

    @JSONField(name = "IsOwner")
    private Long isOwner;

    public ContactMemberModel() {
    }

    public ContactMemberModel(Long uin, String userName, String nickName, String headImgUrl, Long contactFlag, Long memberCount, List<MemberModel> memberList, String remarkName, Long hideInputBarFlag, Long sex, String signature, Long verifyFlag, Long ownerUin, String pYInitial, String pYQuanPin, String remarkPYInitial, String remarkPYQuanPin, Long starFriend, Long appAccountFlag, Long statues, Long attrStatus, String province, String city, String alias, Long snsFlag, Long uniFriend, String displayName, Long chatRoomId, String keyWord, String encryChatRoomId, Long isOwner) {
        this.uin = uin;
        this.userName = userName;
        this.nickName = nickName;
        this.headImgUrl = headImgUrl;
        this.contactFlag = contactFlag;
        this.memberCount = memberCount;
        this.memberList = memberList;
        this.remarkName = remarkName;
        this.hideInputBarFlag = hideInputBarFlag;
        this.sex = sex;
        this.signature = signature;
        this.verifyFlag = verifyFlag;
        this.ownerUin = ownerUin;
        this.pYInitial = pYInitial;
        this.pYQuanPin = pYQuanPin;
        this.remarkPYInitial = remarkPYInitial;
        this.remarkPYQuanPin = remarkPYQuanPin;
        this.starFriend = starFriend;
        this.appAccountFlag = appAccountFlag;
        this.statues = statues;
        this.attrStatus = attrStatus;
        this.province = province;
        this.city = city;
        this.alias = alias;
        this.snsFlag = snsFlag;
        this.uniFriend = uniFriend;
        this.displayName = displayName;
        this.chatRoomId = chatRoomId;
        this.keyWord = keyWord;
        this.encryChatRoomId = encryChatRoomId;
        this.isOwner = isOwner;
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

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Long getContactFlag() {
        return contactFlag;
    }

    public void setContactFlag(Long contactFlag) {
        this.contactFlag = contactFlag;
    }

    public Long getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Long memberCount) {
        this.memberCount = memberCount;
    }

    public List<MemberModel> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberModel> memberList) {
        this.memberList = memberList;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public Long getHideInputBarFlag() {
        return hideInputBarFlag;
    }

    public void setHideInputBarFlag(Long hideInputBarFlag) {
        this.hideInputBarFlag = hideInputBarFlag;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getVerifyFlag() {
        return verifyFlag;
    }

    public void setVerifyFlag(Long verifyFlag) {
        this.verifyFlag = verifyFlag;
    }

    public Long getOwnerUin() {
        return ownerUin;
    }

    public void setOwnerUin(Long ownerUin) {
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

    public String getRemarkPYQuanPin() {
        return remarkPYQuanPin;
    }

    public void setRemarkPYQuanPin(String remarkPYQuanPin) {
        this.remarkPYQuanPin = remarkPYQuanPin;
    }

    public Long getStarFriend() {
        return starFriend;
    }

    public void setStarFriend(Long starFriend) {
        this.starFriend = starFriend;
    }

    public Long getAppAccountFlag() {
        return appAccountFlag;
    }

    public void setAppAccountFlag(Long appAccountFlag) {
        this.appAccountFlag = appAccountFlag;
    }

    public Long getStatues() {
        return statues;
    }

    public void setStatues(Long statues) {
        this.statues = statues;
    }

    public Long getAttrStatus() {
        return attrStatus;
    }

    public void setAttrStatus(Long attrStatus) {
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

    public Long getSnsFlag() {
        return snsFlag;
    }

    public void setSnsFlag(Long snsFlag) {
        this.snsFlag = snsFlag;
    }

    public Long getUniFriend() {
        return uniFriend;
    }

    public void setUniFriend(Long uniFriend) {
        this.uniFriend = uniFriend;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
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

    public Long getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Long isOwner) {
        this.isOwner = isOwner;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
