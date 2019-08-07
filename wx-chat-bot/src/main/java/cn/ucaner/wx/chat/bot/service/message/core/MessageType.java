package cn.ucaner.wx.chat.bot.service.message.core;

/**
 * @ClassName：MessageType
 * @Description： <p> MessageType </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:11
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public enum  MessageType {

    TEXT("文本"),
    IMAGES("图片"),
    VOICE("语音"),
    VIDEO("视频"),
    EMOJI("表情包"),
    RECALL("撤回一条消息"),
    LINK("分享链接"),
    MINIAPP("小程序"),
    OPEN("打开"),
    CLOSE("关闭")
    ;

    MessageType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
