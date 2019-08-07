package cn.ucaner.wx.chat.bot.service.message;

import cn.ucaner.wx.chat.bot.dao.ListenMessageDao;
import cn.ucaner.wx.chat.bot.model.core.WechatCore;
import cn.ucaner.wx.chat.bot.service.message.core.BaseMessage;
import cn.ucaner.wx.chat.bot.service.message.core.IMessage;
import cn.ucaner.wx.chat.bot.service.message.core.MessageType;
import cn.ucaner.wx.chat.bot.utils.HttpUtils;
import cn.ucaner.wx.chat.bot.utils.IdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName：ImageMessage
 * @Description： <p> ImageMessage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:07
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class ImageMessage extends BaseMessage implements IMessage {

    @Autowired
    private ListenMessageDao listenMessageDao;

    @Override
    public void showMessage() {
        String messageImage = getMessageImage(message.getMsgId());
        super.printMessage(messageImage, MessageType.IMAGES);
    }

    public String getMessageImage(String messageId) {
        String imageUrl = listenMessageDao.getMessageImage(WechatCore.getScanCode(), WechatCore.getLoginModel(), messageId);
        String imagePath = String.format("%s/images/%s.png",super.sysConfig.wechatCachePath, IdGenerate.getUUId());
        String result = HttpUtils.downloadImage(imageUrl,imagePath);
        return result;
    }

}
