package cn.ucaner.wx.chat.bot.biz.common.config;

import cn.ucaner.wx.chat.bot.biz.common.consts.ConfigPathConstant;
import cn.ucaner.wx.chat.bot.framework.common.util.properties.PropertiesHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName：springboot-wx
 * @Package：cn.ucaner.wx.chat.bot.biz.common.config
 * @Description： <p> CommonConst </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:58
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 */
public class CommonConst {

    private static Logger logger = LoggerFactory.getLogger(CommonConst.class);

    /**
     * 二维码等待时间
     */
    public static Integer WECHAT_QRCODE_TIME;

    /**
     * 特别关心
     */
    public static String WECHAT_SPECIAL_USERNAMES;

    /**
     * 微信域名
     */
    public static String WECHAT_HOSTS;

    /**
     * 微信缓存地址
     */
    public static String  WECHAT_CACHE_PATH;

    /**
     * 钉钉token
     */
    public static String  WECHAT_DINGDING_TOKEN;

    /**
     * 特别关心
     */
    public static String  WECHAT_CONCERNED_LIST;

    /**
     * 黑名单
     */
    public static String  WECHAT_BLACKLIST;

    /**
     * 初始化加载
     */
    static {
        load();
    }


    public static void load() {
        logger.info("=== load:初始化配置[CommonConst] ===");
        PropertiesHelper props = new PropertiesHelper(ConfigPathConstant.CONFIG_SYSTEM_APP_PATH);
        WECHAT_QRCODE_TIME = props.getInteger("wechat.qrcode.time",120);
        WECHAT_SPECIAL_USERNAMES = props.getProperty("wechat.special.usernames");
        WECHAT_HOSTS = props.getProperty("wechat.hosts");
        WECHAT_CACHE_PATH = props.getProperty("wechat.cache.path");
        WECHAT_DINGDING_TOKEN = props.getProperty("wechat.dingding.token");
        WECHAT_CONCERNED_LIST = props.getProperty("wechat.concerned.list");
        WECHAT_BLACKLIST = props.getProperty("wechat.blacklist");
    }
}
