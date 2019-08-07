package cn.ucaner.wx.chat.bot.config;

import cn.ucaner.wx.chat.bot.biz.common.consts.ConfigPathConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.List;

/**
 * @ClassName：SysConfig
 * @Description： <p> SysConfig </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:33
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Configuration
@PropertySources(value = {@PropertySource(ConfigPathConstant.CONFIG_SYSTEM_APP_PATH)})
public class SysConfig {

    /**
     * 微信缓存地址
     */
    @Value("${wechat.cache.path}")
    public String wechatCachePath;

    /**
     * 特别关心列表
     */
    @Value("#{'${wechat.concerned.list}'.split(',')}")
    private List<String> concernedList;

    /**
     * 黑名单
     */
    @Value("#{'${wechat.blacklist}'.split(',')}")
    private List<String> blackList;

    /**
     * 特殊账号昵称
     */
    @Value("#{'${wechat.special.usernames}'.split(',')}")
    private List<String>  specialUserName;


    public String getWechatCachePath() {
        return wechatCachePath;
    }

    public void setWechatCachePath(String wechatCachePath) {
        this.wechatCachePath = wechatCachePath;
    }

    public List<String> getConcernedList() {
        return concernedList;
    }

    public void setConcernedList(List<String> concernedList) {
        this.concernedList = concernedList;
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }

    public List<String> getSpecialUserName() {
        return specialUserName;
    }

    public void setSpecialUserName(List<String> specialUserName) {
        this.specialUserName = specialUserName;
    }
}
