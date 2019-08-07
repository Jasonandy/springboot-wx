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
 * @ClassName：VoiceMessage
 * @Description： <p> VoiceMessage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:11
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class VoiceMessage extends BaseMessage implements IMessage {

    @Autowired
    private ListenMessageDao listenMessageDao;

    @Override
    public void showMessage() {
        String messageVoice = getMessageVoice(message.getMsgId());
        super.printMessage(messageVoice, MessageType.VOICE);
    }

    public String getMessageVoice(String messageId) {
        String voiceUrl = listenMessageDao.getMessageVoice(WechatCore.getScanCode(), WechatCore.getLoginModel(), messageId);
        String voicePath = String.format("%s/voice/%s.mp3",super.sysConfig.wechatCachePath, IdGenerate.getUUId());
        String result = HttpUtils.downloadVideo(voiceUrl,voicePath);
        return result;
    }

}
