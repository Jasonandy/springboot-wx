package cn.ucaner.wx.chat.bot.service.message;

import cn.ucaner.wx.chat.bot.service.message.core.BaseMessage;
import cn.ucaner.wx.chat.bot.service.message.core.IMessage;
import cn.ucaner.wx.chat.bot.service.message.core.MessageType;
import com.xiaoleilu.hutool.util.ReUtil;
import org.springframework.stereotype.Service;

/**
 * @ClassName：EmojiMessage
 * @Description： <p> EmojiMessage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:16
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class EmojiMessage extends BaseMessage implements IMessage {

    @Override
    public void showMessage() {
        String imageUrl = ReUtil.get("cdnurl = \\\\\"(\\S+?)\\\\\"", message.getContent(), 1);
        if(null == imageUrl){
            imageUrl = ReUtil.get("cdnurl = \\\"(\\S+?)\\\"", message.getContent(), 1);
        }
        if(null == imageUrl){
            imageUrl="表情包请在手机上查看";
        }
        super.printMessage(imageUrl, MessageType.EMOJI);
    }

}
