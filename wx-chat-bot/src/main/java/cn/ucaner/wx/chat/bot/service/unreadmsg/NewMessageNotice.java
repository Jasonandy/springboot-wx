package cn.ucaner.wx.chat.bot.service.unreadmsg;

import cn.ucaner.wx.chat.bot.dao.ListenMessageDao;
import cn.ucaner.wx.chat.bot.model.core.WechatCore;
import cn.ucaner.wx.chat.bot.model.listen.AddMessage;
import cn.ucaner.wx.chat.bot.model.listen.MessageModel;
import cn.ucaner.wx.chat.bot.service.message.core.IMessage;
import cn.ucaner.wx.chat.bot.service.message.core.IShowMessage;
import cn.ucaner.wx.chat.bot.service.unreadmsg.core.INotice;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName：NewMessageNotice
 * @Description： <p> NewMessageNotice </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:13
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class NewMessageNotice implements INotice {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewMessageNotice.class);

    @Autowired
    private SleepNotice sleepNotice;

    @Autowired
    private ListenMessageDao listenMessageDao;

    @Autowired
    private IShowMessage showMessage;

    @Override
    public void process() {
        //获取最新消息
        MessageModel message = this.getNewMessage();
        if (message.getBaseResponse().verify()) {
            for (AddMessage newMessage : message.getAddMsgList()) {
                //创建消息工厂
                IMessage messageFactory = showMessage.createMessage(newMessage);
                //显示消息
                messageFactory.showMessage();
            }
            //更新同步key,表示该消息已读,微信就不会在推送该消息了
            WechatCore.setSyncKey(message.getSyncKey());
        }
        //sleep
        sleepNotice.process();
    }

    /**
     * 获取最新消息
     */
    public MessageModel getNewMessage() {
        String content = listenMessageDao.getMessage(WechatCore.getScanCode(), WechatCore.getLoginModel(), WechatCore.getSyncKey());
        MessageModel message = JSON.parseObject(content, MessageModel.class);
        return message;
    }

}
