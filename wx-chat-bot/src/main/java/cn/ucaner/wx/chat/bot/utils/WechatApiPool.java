package cn.ucaner.wx.chat.bot.utils;

/**
 * @ClassName：WechatApiPool
 * @Description： <p> WechatApiPool </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:43
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class WechatApiPool {

    /**
     * appId
     */
    public static final String APPID = "wx782c26e4c19acffb";

    public static final String FUN = "new";

    public static final String LANG = "zh_CN";

    /**
     * 获取UUID
     * POST
     */
    public static final String POST_JSLOGIN = "https://login.weixin.qq.com/jslogin";

    /**
     * 生成二维码
     */
    public static final String QRCODE_FORMAT = "https://login.weixin.qq.com/l/%s";

    /**
     * 判断是否扫码
     * @tip
     * @uuid
     * @_
     */
    public static final String GET_WAITFORLOGIN = "https://login.weixin.qq.com/cgi-bin/mmwebwx-bin/login?tip=%s&uuid=%s&_=%s";

    /**
     * 初始化
     */
    public static final String POST_INIT = "/webwxinit?pass_ticket=%s&skey=%s&r=%s";

    /**
     * 开启状态通知
     */
    public static final String POST_STATUS_NOTIFY = "/webwxstatusnotify?lang=zh_CN&pass_ticket=%s";

    /**
     * 获取联系人列表
     */
    public static final String POST_GET_CONTACT_LIST ="/webwxgetcontact?pass_ticket=%s&skey=%s&r=%s";

    /**
     * 获取群信息
     */
    public static final String POST_GROUP_INFO = "/webwxbatchgetcontact?type=ex&r=%s&pass_ticket=%s";

    /**
     * 轮询check消息
     * @info 轮询check是否有未读消息
     */
    public static final String GET_POLLING_CHECK_UNREAD_MESSAGE = "https://%s/cgi-bin/mmwebwx-bin/synccheck?%s";

    /**
     * 获取消息
     */
    public static final String POST_GET_MESSAGE = "/webwxsync?sid=%s&skey=%s&pass_ticket=%s";

    /**
     * 获取消息-图片
     */
    public static final String GET_MESSAGE_IMAGE = "/webwxgetmsgimg?MsgID=%s&skey=%s";

    /**
     * 获取消息-视频
     */
    public static final String GET_MESSAGE_VIDEO = "/webwxgetvideo?msgid=%s&skey=%s";

    /**
     * 获取消息-语音
     */
    public static final String GET_MESSAGE_VOICE = "/webwxgetvoice?msgid=%s&skey=%s";

}
