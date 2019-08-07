package cn.ucaner.wx.chat.bot.service.message.core;

import cn.ucaner.wx.chat.bot.model.listen.AddMessage;

/**
 * @ClassName：IMessage
 * @Description： <p> IMessage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:09
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public interface IMessage {

    void showMessage();

    void initData(AddMessage message);

}
