package cn.ucaner.wx.chat.bot.model.initialization;

import cn.ucaner.wx.chat.bot.model.contact.ContactMemberModel;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @ClassName：InitModel
 * @Description： <p> InitModel </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:52
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class InitModel {

    @JSONField(name = "BaseResponse")
    private BaseResponse baseResponse;

    @JSONField(name = "Count")
    private Integer count;

    @JSONField(name = "ContactList")
    private List<ContactMemberModel> contactList;

    @JSONField(name = "SyncKey")
    private SyncKeyModel syncKey;

    @JSONField(name = "User")
    private UserModel toUser;

    @JSONField(name = "ChatSet")
    private String chatSet;

    @JSONField(name = "SKey")
    private String sKey;

    @JSONField(name = "ClientVersion")
    private Integer clientVersion;

    @JSONField(name = "SystemTime")
    private Integer systemTime;

    @JSONField(name = "GrayScale")
    private Integer grayScale;

    @JSONField(name = "InviteStartCount")
    private Integer inviteStartCount;

    /**
     * 微信公众号推送个数
     */
    @JSONField(name = "MPSubscribeMsgCount")
    private Integer mPSubscribeMsgCount;

    /**
     * 微信公众号推送列表
     */
    @JSONField(name = "MPSubscribeMsgList")
    private List<MPSubscribeMsg> mPSubscribeMsgList;

    @JSONField(name = "ClickReportInterval")
    private Integer clickReportInterval;

    public InitModel() {
    }

    public InitModel(BaseResponse baseResponse, Integer count, List<ContactMemberModel> contactList, SyncKeyModel syncKey, UserModel toUser, String chatSet, String sKey, Integer clientVersion, Integer systemTime, Integer grayScale, Integer inviteStartCount, Integer mPSubscribeMsgCount, List<MPSubscribeMsg> mPSubscribeMsgList, Integer clickReportInterval) {
        this.baseResponse = baseResponse;
        this.count = count;
        this.contactList = contactList;
        this.syncKey = syncKey;
        this.toUser = toUser;
        this.chatSet = chatSet;
        this.sKey = sKey;
        this.clientVersion = clientVersion;
        this.systemTime = systemTime;
        this.grayScale = grayScale;
        this.inviteStartCount = inviteStartCount;
        this.mPSubscribeMsgCount = mPSubscribeMsgCount;
        this.mPSubscribeMsgList = mPSubscribeMsgList;
        this.clickReportInterval = clickReportInterval;
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ContactMemberModel> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactMemberModel> contactList) {
        this.contactList = contactList;
    }

    public SyncKeyModel getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(SyncKeyModel syncKey) {
        this.syncKey = syncKey;
    }

    public UserModel getToUser() {
        return toUser;
    }

    public void setToUser(UserModel toUser) {
        this.toUser = toUser;
    }

    public String getChatSet() {
        return chatSet;
    }

    public void setChatSet(String chatSet) {
        this.chatSet = chatSet;
    }

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }

    public Integer getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(Integer clientVersion) {
        this.clientVersion = clientVersion;
    }

    public Integer getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Integer systemTime) {
        this.systemTime = systemTime;
    }

    public Integer getGrayScale() {
        return grayScale;
    }

    public void setGrayScale(Integer grayScale) {
        this.grayScale = grayScale;
    }

    public Integer getInviteStartCount() {
        return inviteStartCount;
    }

    public void setInviteStartCount(Integer inviteStartCount) {
        this.inviteStartCount = inviteStartCount;
    }

    public Integer getmPSubscribeMsgCount() {
        return mPSubscribeMsgCount;
    }

    public void setmPSubscribeMsgCount(Integer mPSubscribeMsgCount) {
        this.mPSubscribeMsgCount = mPSubscribeMsgCount;
    }

    public List<MPSubscribeMsg> getmPSubscribeMsgList() {
        return mPSubscribeMsgList;
    }

    public void setmPSubscribeMsgList(List<MPSubscribeMsg> mPSubscribeMsgList) {
        this.mPSubscribeMsgList = mPSubscribeMsgList;
    }

    public Integer getClickReportInterval() {
        return clickReportInterval;
    }

    public void setClickReportInterval(Integer clickReportInterval) {
        this.clickReportInterval = clickReportInterval;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
