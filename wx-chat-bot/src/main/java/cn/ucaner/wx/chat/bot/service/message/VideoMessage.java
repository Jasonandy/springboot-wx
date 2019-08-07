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
 * @ClassName：VideoMessage
 * @Description： <p> VideoMessage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:11
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class VideoMessage extends BaseMessage implements IMessage {

    @Autowired
    private ListenMessageDao listenMessageDao;

    @Override
    public void showMessage() {
        String messageVideo = getMessageVideo(message.getMsgId());
        super.printMessage(messageVideo, MessageType.VIDEO);
    }

    public String getMessageVideo(String messageId) {
        String videoUrl = listenMessageDao.getMessageVideo(WechatCore.getScanCode(), WechatCore.getLoginModel(), messageId);
        String imagePath = String.format("%s/video/%s.mp4",super.sysConfig.wechatCachePath, IdGenerate.getUUId());
        String result = HttpUtils.downloadVideo(videoUrl,imagePath);
        return result;
    }

}
