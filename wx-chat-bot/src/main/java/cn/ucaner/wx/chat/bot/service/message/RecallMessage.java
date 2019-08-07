package cn.ucaner.wx.chat.bot.service.message;

import cn.ucaner.wx.chat.bot.service.message.core.BaseMessage;
import cn.ucaner.wx.chat.bot.service.message.core.IMessage;
import cn.ucaner.wx.chat.bot.service.message.core.MessageCache;
import cn.ucaner.wx.chat.bot.service.message.core.MessageCacheUtils;
import cn.ucaner.wx.chat.bot.service.message.core.MessageType;
import com.xiaoleilu.hutool.util.ReUtil;
import org.springframework.stereotype.Service;


/**
 * @ClassName：RecallMessage
 * @Description： <p> 撤回消息 </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:08
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class RecallMessage extends BaseMessage implements IMessage {
    @Override
    public void showMessage() {
        String msgId = ReUtil.get("msgid&gt;(\\d+)&lt;/msgid", message.getContent(), 1);
        MessageCache messageCache = MessageCacheUtils.get(msgId);
        String content = null;
        if(null != messageCache){
            //显示内容
            content = String.format("%s->%s,MsgId=%s", MessageType.RECALL.getName(),messageCache.getContent(),message.getMsgId());
        }else{
            content = String.format("%s,MsgId=%s", MessageType.RECALL.getName(),message.getMsgId());
        }
        super.printMessage(content, MessageType.RECALL);
    }
}
