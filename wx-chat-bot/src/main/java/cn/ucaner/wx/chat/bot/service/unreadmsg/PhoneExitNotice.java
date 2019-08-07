package cn.ucaner.wx.chat.bot.service.unreadmsg;

import cn.ucaner.wx.chat.bot.service.unreadmsg.core.INotice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName：PhoneExitNotice
 * @Description： <p> 手机退出通知 </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:14
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class PhoneExitNotice implements INotice {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneExitNotice.class);

    @Override
    public void process() {
        LOGGER.info("你在手机上退出了微信...");
        System.exit(0);
    }

}
