package cn.ucaner.wx.chat.bot.dao;

import cn.ucaner.wx.chat.bot.model.group.GroupInfo;
import cn.ucaner.wx.chat.bot.model.initialization.UserModel;
import cn.ucaner.wx.chat.bot.model.login.LoginModel;
import cn.ucaner.wx.chat.bot.model.scan.ScanCode;

import java.util.List;

/**
 * @ClassName：WechatDao
 * @Description： <p> WechatDao </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:57
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public interface WechatDao {

    /**
     * 获取UUID
     * @return
     */
    String getUUID();

    /**
     * 判断是否登陆
     * @param uuid
     * @return
     */
    String waitForLogin(String uuid,Integer tip);

    /**
     * 登陆
     */
    String login(ScanCode scanCode);

    /**
     * 初始化
     */
    String initialization(ScanCode scanCode, LoginModel loginModel);

    /**
     * 开启状态通知
     */
    String statusNotify(ScanCode scanCode, LoginModel loginModel, UserModel toUser);

    /**
     * 获取联系人列表
     */
    String getContactList(ScanCode scanCode,LoginModel loginModel);

    /**
     * 获取群信息
     * @return
     */
    String getGroupInfo(ScanCode scanCode, LoginModel loginModel, List<GroupInfo> groupInfoList);

}
