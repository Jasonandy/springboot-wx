package cn.ucaner.wx.chat.bot.service;

import cn.ucaner.wx.chat.bot.model.contact.ContactList;
import cn.ucaner.wx.chat.bot.model.group.GroupModel;
import cn.ucaner.wx.chat.bot.model.initialization.InitModel;
import cn.ucaner.wx.chat.bot.model.initialization.UserModel;
import cn.ucaner.wx.chat.bot.model.login.LoginModel;
import cn.ucaner.wx.chat.bot.model.scan.ScanCode;

/**
 * @ClassName：WechatService
 * @Description： <p> WechatService </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:00
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public interface WechatService {

    /**
     * 获取uuid
     */
    String getUUID();

    /**
     * 等待扫码
     */
    ScanCode waitForLogin(String uuid, Integer tip);

    /**
     * 登陆
     */
    LoginModel login(ScanCode scanCode);

    /**
     * 初始化
     * @param loginModel
     */
    InitModel initialization(ScanCode scanCode, LoginModel loginModel);

    /**
     * 开启状态通知
     */
    String statusNotify(ScanCode scanCode, LoginModel loginModel, UserModel toUser);

    /**
     * 获取联系人列表
     */
    ContactList getContactList(ScanCode scanCode, LoginModel loginModel);

    /**
     * 获取群信息
     * @return
     */
    GroupModel getGroupInfo(ScanCode scanCode, LoginModel loginModel, ContactList contactList);

    /**
     * 获取单个群信息
     */
    GroupModel getGroupInfo(ScanCode scanCode, LoginModel loginModel, String groupId);

}
