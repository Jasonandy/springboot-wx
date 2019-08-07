package cn.ucaner.wx.chat.bot.service.unreadmsg;

import cn.ucaner.wx.chat.bot.model.listen.UnreadMessageModel;
import cn.ucaner.wx.chat.bot.service.unreadmsg.core.INotice;
import cn.ucaner.wx.chat.bot.service.unreadmsg.core.INoticeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @ClassName：NoticeMsgFactory
 * @Description： <p> NoticeMsgFactory </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:13
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class NoticeMsgFactory implements INoticeMsg {

    /**
     * 手机退出
     */
    @Autowired
    private PhoneExitNotice phoneExitNotice;

    /**
     * 其他设备登陆
     */
    @Autowired
    private LoginOtherDeviceNotice loginOtherDeviceNotice;

    /**
     * sleep通知
     */
    @Autowired
    private SleepNotice sleepNotice;

    /**
     * 新消息通知
     */
    @Autowired
    private NewMessageNotice newMessageNotice;

    @Override
    public INotice createNoticeMsg(UnreadMessageModel unreadMessage) {
        //通知
        INotice notice = null;
        Integer retCode = unreadMessage.getRetCode();
        Integer selector = unreadMessage.getSelector();
        switch (retCode) {
            case 1100:
                //手机退出
                notice = phoneExitNotice;
                break;
            case 1101:
                //其他设备登陆
                notice = loginOtherDeviceNotice;
                break;
            case 0:
                switch (selector) {
                    case 0:
                        //没有消息
                        notice = sleepNotice;
                        break;
                    case 2:
                    case 6:
                    case 7:
                        //新消息通知
                        notice = newMessageNotice;
                        break;
                    default:
                        //没有消息
                        notice = sleepNotice;
                        break;
                }
                break;
            default:
                //没有消息
                notice = sleepNotice;
                break;
        }
        return notice;
    }

}
