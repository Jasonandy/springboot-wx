package cn.ucaner.wx.chat.bot.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName：IdGenerate
 * @Description： <p> IdGenerate </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:42
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class IdGenerate {

    /**
     * 获取uuid
     */
    public static String getUUId(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 随机生成设备id
     */
    public static String getDeviceId(){
        double random = new Random().nextDouble();
        String deviceId = "e"+String.valueOf(random).substring(2, 17);
        return deviceId;
    }

}
