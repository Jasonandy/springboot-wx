package cn.ucaner.wx.chat.bot.model.listen;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：AddMessage
 * @Description： <p> AddMessage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:50
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class AddMessage {

    @JSONField(name ="MsgId")
    private String msgId;

    @JSONField(name ="FromUserName")
    private String fromUserName;

    @JSONField(name ="ToUserName")
    private String toUserName;

    @JSONField(name ="MsgType")
    private Integer msgType;

    @JSONField(name ="Content")
    private String content;

    @JSONField(name ="Status")
    private Integer status;

    @JSONField(name ="ImgStatus")
    private Integer imgStatus;

    @JSONField(name ="CreateTime")
    private Integer createTime;

    @JSONField(name ="VoiceLength")
    private Integer voiceLength;

    @JSONField(name ="PlayLength")
    private Integer playLength;

    @JSONField(name ="FileName")
    private String fileName;

    @JSONField(name ="FileSize")
    private String fileSize;

    @JSONField(name ="MediaId")
    private String mediaId;

    @JSONField(name ="Url")
    private String url;

    @JSONField(name ="AppMsgType")
    private Integer appMsgType;

    @JSONField(name ="StatusNotifyCode")
    private Integer statusNotifyCode;

    @JSONField(name ="StatusNotifyUserName")
    private String statusNotifyUserName;

    @JSONField(name = "RecommendInfo")
    private AddMessageRecommendInfo recommendInfo;

    @JSONField(name = "ForwardFlag")
    private Integer forwardFlag;

    @JSONField(name = "AppInfo")
    private AddMessageAppInfo appInfo;

    @JSONField(name = "HasProductId")
    private Integer hasProductId;

    @JSONField(name = "Ticket")
    private String ticket;

    @JSONField(name = "ImgHeight")
    private Integer imgHeight;

    @JSONField(name = "ImgWidth")
    private Integer imgWidth;

    @JSONField(name = "SubMsgType")
    private Integer subMsgType;

    @JSONField(name = "NewMsgId")
    private Long newMsgId;

    @JSONField(name = "OriContent")
    private String oriContent;

    @JSONField(name = "EncryFileName")
    private String encryFileName;

    public AddMessage() {
    }

    public AddMessage(String msgId, String fromUserName, String toUserName, Integer msgType, String content, Integer status, Integer imgStatus, Integer createTime, Integer voiceLength, Integer playLength, String fileName, String fileSize, String mediaId, String url, Integer appMsgType, Integer statusNotifyCode, String statusNotifyUserName, AddMessageRecommendInfo recommendInfo, Integer forwardFlag, AddMessageAppInfo appInfo, Integer hasProductId, String ticket, Integer imgHeight, Integer imgWidth, Integer subMsgType, Long newMsgId, String oriContent, String encryFileName) {
        this.msgId = msgId;
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
        this.msgType = msgType;
        this.content = content;
        this.status = status;
        this.imgStatus = imgStatus;
        this.createTime = createTime;
        this.voiceLength = voiceLength;
        this.playLength = playLength;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mediaId = mediaId;
        this.url = url;
        this.appMsgType = appMsgType;
        this.statusNotifyCode = statusNotifyCode;
        this.statusNotifyUserName = statusNotifyUserName;
        this.recommendInfo = recommendInfo;
        this.forwardFlag = forwardFlag;
        this.appInfo = appInfo;
        this.hasProductId = hasProductId;
        this.ticket = ticket;
        this.imgHeight = imgHeight;
        this.imgWidth = imgWidth;
        this.subMsgType = subMsgType;
        this.newMsgId = newMsgId;
        this.oriContent = oriContent;
        this.encryFileName = encryFileName;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(Integer imgStatus) {
        this.imgStatus = imgStatus;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getVoiceLength() {
        return voiceLength;
    }

    public void setVoiceLength(Integer voiceLength) {
        this.voiceLength = voiceLength;
    }

    public Integer getPlayLength() {
        return playLength;
    }

    public void setPlayLength(Integer playLength) {
        this.playLength = playLength;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAppMsgType() {
        return appMsgType;
    }

    public void setAppMsgType(Integer appMsgType) {
        this.appMsgType = appMsgType;
    }

    public Integer getStatusNotifyCode() {
        return statusNotifyCode;
    }

    public void setStatusNotifyCode(Integer statusNotifyCode) {
        this.statusNotifyCode = statusNotifyCode;
    }

    public String getStatusNotifyUserName() {
        return statusNotifyUserName;
    }

    public void setStatusNotifyUserName(String statusNotifyUserName) {
        this.statusNotifyUserName = statusNotifyUserName;
    }

    public AddMessageRecommendInfo getRecommendInfo() {
        return recommendInfo;
    }

    public void setRecommendInfo(AddMessageRecommendInfo recommendInfo) {
        this.recommendInfo = recommendInfo;
    }

    public Integer getForwardFlag() {
        return forwardFlag;
    }

    public void setForwardFlag(Integer forwardFlag) {
        this.forwardFlag = forwardFlag;
    }

    public AddMessageAppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AddMessageAppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public Integer getHasProductId() {
        return hasProductId;
    }

    public void setHasProductId(Integer hasProductId) {
        this.hasProductId = hasProductId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(Integer imgHeight) {
        this.imgHeight = imgHeight;
    }

    public Integer getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(Integer imgWidth) {
        this.imgWidth = imgWidth;
    }

    public Integer getSubMsgType() {
        return subMsgType;
    }

    public void setSubMsgType(Integer subMsgType) {
        this.subMsgType = subMsgType;
    }

    public Long getNewMsgId() {
        return newMsgId;
    }

    public void setNewMsgId(Long newMsgId) {
        this.newMsgId = newMsgId;
    }

    public String getOriContent() {
        return oriContent;
    }

    public void setOriContent(String oriContent) {
        this.oriContent = oriContent;
    }

    public String getEncryFileName() {
        return encryFileName;
    }

    public void setEncryFileName(String encryFileName) {
        this.encryFileName = encryFileName;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
