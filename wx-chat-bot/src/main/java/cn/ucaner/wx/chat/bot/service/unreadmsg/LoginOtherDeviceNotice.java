package cn.ucaner.wx.chat.bot.service.unreadmsg;

import cn.ucaner.wx.chat.bot.service.unreadmsg.core.INotice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName：LoginOtherDeviceNotice
 * @Description： <p> 其他设备登陆通知 </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:12
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class LoginOtherDeviceNotice implements INotice {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginOtherDeviceNotice.class);

    @Override
    public void process() {
        LOGGER.info("你在其他地方登录了web微信...");
        System.exit(0);
    }

}
