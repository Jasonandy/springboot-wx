package cn.ucaner.wx.chat.bot.service.message.core;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：MessageCache
 * @Description： <p> MessageCache </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:07
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class MessageCache {

    private String msgId;

    private String content;

    private MessageType type;

    public MessageCache() {
    }

    public MessageCache(String msgId, String content, MessageType type) {
        this.msgId = msgId;
        this.content = content;
        this.type = type;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
