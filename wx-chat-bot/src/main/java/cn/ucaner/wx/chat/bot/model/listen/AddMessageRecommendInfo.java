package cn.ucaner.wx.chat.bot.model.listen;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：AddMessageRecommendInfo
 * @Description： <p> AddMessageRecommendInfo </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:51
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class AddMessageRecommendInfo {

    @JSONField(name = "UserName")
    private String userName;

    @JSONField(name = "NickName")
    private String nickName;

    @JSONField(name = "QQNum")
    private Integer qQNum;

    @JSONField(name = "Province")
    private String province;

    @JSONField(name = "City")
    private String city;

    @JSONField(name = "Content")
    private String content;

    @JSONField(name = "Signature")
    private String signature;

    @JSONField(name = "Alias")
    private String alias;

    @JSONField(name = "Scene")
    private Integer scene;

    @JSONField(name = "VerifyFlag")
    private Integer verifyFlag;

    @JSONField(name = "AttrStatus")
    private Integer attrStatus;

    @JSONField(name = "Sex")
    private Integer sex;

    @JSONField(name = "Ticket")
    private String ticket;

    @JSONField(name = "OpCode")
    private Integer opCode;

    public AddMessageRecommendInfo() {
    }

    public AddMessageRecommendInfo(String userName, String nickName, Integer qQNum, String province, String city, String content, String signature, String alias, Integer scene, Integer verifyFlag, Integer attrStatus, Integer sex, String ticket, Integer opCode) {
        this.userName = userName;
        this.nickName = nickName;
        this.qQNum = qQNum;
        this.province = province;
        this.city = city;
        this.content = content;
        this.signature = signature;
        this.alias = alias;
        this.scene = scene;
        this.verifyFlag = verifyFlag;
        this.attrStatus = attrStatus;
        this.sex = sex;
        this.ticket = ticket;
        this.opCode = opCode;
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

    public Integer getqQNum() {
        return qQNum;
    }

    public void setqQNum(Integer qQNum) {
        this.qQNum = qQNum;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
    }

    public Integer getVerifyFlag() {
        return verifyFlag;
    }

    public void setVerifyFlag(Integer verifyFlag) {
        this.verifyFlag = verifyFlag;
    }

    public Integer getAttrStatus() {
        return attrStatus;
    }

    public void setAttrStatus(Integer attrStatus) {
        this.attrStatus = attrStatus;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getOpCode() {
        return opCode;
    }

    public void setOpCode(Integer opCode) {
        this.opCode = opCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
