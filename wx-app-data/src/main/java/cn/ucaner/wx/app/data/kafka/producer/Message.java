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

    private String id;

    private String msg;

    private Date sendTime;

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
}
