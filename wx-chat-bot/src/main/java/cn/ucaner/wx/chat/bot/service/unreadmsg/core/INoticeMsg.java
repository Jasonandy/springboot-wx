package cn.ucaner.wx.chat.bot.service.unreadmsg.core;

import cn.ucaner.wx.chat.bot.model.listen.UnreadMessageModel;

/**
 * @ClassName：INoticeMsg
 * @Description： <p> INoticeMsg </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:03
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public interface INoticeMsg {

    INotice createNoticeMsg(UnreadMessageModel unreadMessage);

}
