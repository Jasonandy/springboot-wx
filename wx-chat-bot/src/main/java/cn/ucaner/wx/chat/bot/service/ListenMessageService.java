package cn.ucaner.wx.chat.bot.service;

import cn.ucaner.wx.chat.bot.model.initialization.SyncKeyModel;
import cn.ucaner.wx.chat.bot.model.login.LoginModel;
import cn.ucaner.wx.chat.bot.model.scan.ScanCode;

/**
 * @ClassName：ListenMessageService
 * @Description： <p> ListenMessageService </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:59
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public interface ListenMessageService {


    /**
     * listen
     * @param scanCode
     * @param loginModel
     * @param syncKey
     */
    void listen(ScanCode scanCode, LoginModel loginModel, SyncKeyModel syncKey);

}
