package cn.ucaner.wx.chat.bot.service.message;

import cn.ucaner.wx.chat.bot.service.message.core.BaseMessage;
import cn.ucaner.wx.chat.bot.service.message.core.IMessage;
import cn.ucaner.wx.chat.bot.service.message.core.MessageType;
import org.springframework.stereotype.Service;


/**
 * @ClassName：TextMessage
 * @Description： <p> TextMessage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:11
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class TextMessage extends BaseMessage implements IMessage {

    @Override
    public void showMessage() {
        String[] contentArray = message.getContent().split(":<br/>");
        String content = (super.senderIsMe||!super.group) ? contentArray[0]:contentArray[1];
        //打印消息
        super.printMessage(content, MessageType.TEXT);
    }

}
