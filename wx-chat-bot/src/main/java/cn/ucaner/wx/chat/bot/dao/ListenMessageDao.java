package cn.ucaner.wx.chat.bot.dao;


import cn.ucaner.wx.chat.bot.model.core.BaseRequest;
import cn.ucaner.wx.chat.bot.model.initialization.SyncKeyModel;
import cn.ucaner.wx.chat.bot.model.login.LoginModel;
import cn.ucaner.wx.chat.bot.model.scan.ScanCode;

/**
 * @ClassName：ListenMessageDao
 * @Description： <p> ListenMessageDao </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:57
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public interface ListenMessageDao {

    /**
     * 轮询check是否有未读消息
     */
    String pollingCheckUnreadMessage(String host, BaseRequest base, SyncKeyModel syncKey);

    /**
     * 获取消息
     * @param loginModel
     * @return
     */
    String getMessage(ScanCode scanCode, LoginModel loginModel, SyncKeyModel syncKey);

    /**
     * 获取图片
     * @return
     */
    String getMessageImage(ScanCode scanCode, LoginModel loginModel,String messageId);

    /**
     * 获取视频
     * @return
     */
    String getMessageVideo(ScanCode scanCode, LoginModel loginModel,String messageId);

    /**
     * 获取语音
     */
    String getMessageVoice(ScanCode scanCode, LoginModel loginModel,String messageId);

}
