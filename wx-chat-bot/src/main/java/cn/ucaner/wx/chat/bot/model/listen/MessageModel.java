package cn.ucaner.wx.chat.bot.model.listen;

import cn.ucaner.wx.chat.bot.model.initialization.BaseResponse;
import cn.ucaner.wx.chat.bot.model.initialization.SyncKeyModel;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @ClassName：MessageModel
 * @Description： <p> MessageModel </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:51
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class MessageModel {

    @JSONField(name = "BaseResponse")
    private BaseResponse baseResponse;

    @JSONField(name = "SyncKey")
    private SyncKeyModel syncKey;

    @JSONField(name = "AddMsgList")
    private List<AddMessage> addMsgList;

    public MessageModel() {
    }

    public MessageModel(BaseResponse baseResponse, SyncKeyModel syncKey, List<AddMessage> addMsgList) {
        this.baseResponse = baseResponse;
        this.syncKey = syncKey;
        this.addMsgList = addMsgList;
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public SyncKeyModel getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(SyncKeyModel syncKey) {
        this.syncKey = syncKey;
    }

    public List<AddMessage> getAddMsgList() {
        return addMsgList;
    }

    public void setAddMsgList(List<AddMessage> addMsgList) {
        this.addMsgList = addMsgList;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
