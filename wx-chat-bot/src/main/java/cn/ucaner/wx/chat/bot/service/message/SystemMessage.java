package cn.ucaner.wx.chat.bot.service.message;

import cn.ucaner.wx.chat.bot.service.message.core.BaseMessage;
import cn.ucaner.wx.chat.bot.service.message.core.IMessage;
import cn.ucaner.wx.chat.bot.utils.DingMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName：SystemMessage
 * @Description： <p> SystemMessage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:10
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class SystemMessage extends BaseMessage implements IMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemMessage.class);

    @Autowired
    private DingMessageUtils dingMessageUtils;

    @Override
    public void showMessage() {
        String content = message.getContent();
        switch (content){
            case "收到红包，请在手机上查看":
                LOGGER.info(content);
                dingMessageUtils.sendMessage(content);
                break;
            default:
                LOGGER.info(content);
                break;
        }
    }
}
