package cn.ucaner.wx.chat.bot.utils;

import cn.ucaner.wx.chat.bot.biz.common.config.CommonConst;
import com.xiaoleilu.hutool.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName：DingMessageUtils
 * @Description： <p> DingMessageUtils </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:41
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Component
public class DingMessageUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DingMessageUtils.class);

    private static final String ADMIN = "https://oapi.dingtalk.com/robot/send?access_token=";

    /**
     * 钉钉机器人token
     */
    private String token = CommonConst.WECHAT_DINGDING_TOKEN;

    @Value("微信客户端")
    private String projectName;

    public void sendMessage(String content) {
        try {
            if(null == token){
                LOGGER.info("token is null...");
                return;
            }
            /** 钉钉推送消息 */
            com.xiaoleilu.hutool.json.JSONObject messageMsg = new com.xiaoleilu.hutool.json.JSONObject().put("msgtype", "text")
                    .put("text", new com.xiaoleilu.hutool.json.JSONObject().put("content", String.format("%s->%s", projectName, content)))
                    .put("at", new com.xiaoleilu.hutool.json.JSONObject()
                            .put("isAtAll", true)
                    );
            HttpRequest.post(ADMIN + token).body(messageMsg).execute().body();
        } catch (Exception e) {
            LOGGER.error("sendMessage error,",e);
        }
    }

}
