package cn.ucaner.wx.app.data.kafka.producer;

import java.util.Date;

/**
 * @ClassName：Message
 * @Description： <p> Message  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/8 - 17:42
 * @Modify By：
 * @ModifyTime： 2019/3/8
 * @Modify marker：
 * @version V1.0
*/
public class Message {

    /**
     * 消息id
     */
    private String id;

    /**
     * 信息
     */
    private String msg;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 发送者
     */
    private String sender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }
}
