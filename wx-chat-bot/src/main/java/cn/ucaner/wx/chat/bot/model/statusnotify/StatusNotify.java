package cn.ucaner.wx.chat.bot.model.statusnotify;

import cn.ucaner.wx.chat.bot.model.initialization.BaseResponse;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：StatusNotify
 * @Description： <p> StatusNotify </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:50
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class StatusNotify {

    @JSONField(name = "BaseResponse")
    private BaseResponse baseResponse;

    @JSONField(name = "MsgID")
    private String msgID;

    public StatusNotify() {
    }

    public StatusNotify(BaseResponse baseResponse, String msgID) {
        this.baseResponse = baseResponse;
        this.msgID = msgID;
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
