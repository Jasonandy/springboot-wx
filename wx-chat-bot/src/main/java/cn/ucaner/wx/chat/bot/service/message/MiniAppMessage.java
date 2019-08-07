package cn.ucaner.wx.chat.bot.service.message;

import cn.ucaner.wx.chat.bot.service.message.core.BaseMessage;
import cn.ucaner.wx.chat.bot.service.message.core.IMessage;
import cn.ucaner.wx.chat.bot.service.message.core.MessageType;
import org.springframework.stereotype.Service;


/**
 * @ClassName：MiniAppMessage
 * @Description： <p> MiniAppMessage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:08
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class MiniAppMessage extends BaseMessage implements IMessage {
    @Override
    public void showMessage() {
        //分享标题
        String messageTitle = message.getFileName();
        //分享链接
        String messageUrl = message.getUrl();
        //显示内容
        String content = String.format("(%s)->%s",messageTitle,messageUrl);
        super.printMessage(content, MessageType.MINIAPP);
    }
}
