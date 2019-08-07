package cn.ucaner.wx.chat.bot.service.unreadmsg;

import cn.ucaner.wx.chat.bot.service.unreadmsg.core.INotice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName：SleepNotice
 * @Description： <p> SleepNotice </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:14
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class SleepNotice implements INotice {

    private static final Logger LOGGER = LoggerFactory.getLogger(SleepNotice.class);

    @Override
    public void process() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("sleep error,",e);
        }
    }

}
