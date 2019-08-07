package cn.ucaner.wx.chat.bot.service.message;

import cn.ucaner.wx.chat.bot.service.message.core.BaseMessage;
import cn.ucaner.wx.chat.bot.service.message.core.IMessage;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName：ActionMessage
 * @Description： <p> ActionMessage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:16
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class ActionMessage extends BaseMessage implements IMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionMessage.class);

    @Override
    public void showMessage() {
        Integer statusNotifyCode = message.getStatusNotifyCode();
        switch (statusNotifyCode){
            case 2:
                if(group){
                    LOGGER.info("你打开了->{}的群聊",msgToUserName);
                }else{
                    LOGGER.info("你打开了->{}的聊天窗口",msgToUserName);
                }
                break;
            case 5:
                if(group){
                    LOGGER.info("你关闭了->{}的群聊",msgToUserName);
                }else{
                    LOGGER.info("你关闭了->{}的聊天窗口",msgToUserName);
                }
                break;
            default:
                LOGGER.info(JSON.toJSONString(message));
                break;
        }
    }

}
