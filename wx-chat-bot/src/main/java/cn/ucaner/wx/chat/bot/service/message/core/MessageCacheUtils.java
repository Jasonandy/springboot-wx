package cn.ucaner.wx.chat.bot.service.message.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName：MessageCacheUtils
 * @Description： <p> MessageCacheUtils </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:19
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class MessageCacheUtils {

    /**
     * 缓存最大消息数量
     */
    private volatile static Integer maxLength = 1000;

    private volatile static List<String> messageList = new ArrayList<>();

    private volatile static Map<String, MessageCache> messageMap = new HashMap<>();

    /**
     * 获取消息
     */
    public static MessageCache get(String msgId){
        return messageMap.get(msgId);
    }

    /**
     * 存储消息
     */
    public static Integer put(MessageCache message){
        String msgId = message.getMsgId();
        messageMap.put(msgId,message);
        messageList.add(msgId);
        int size = messageList.size();
        if(size>maxLength){
            messageMap.remove(messageList.get(0));
            messageList.remove(0);
        }
        return size;
    }

}
