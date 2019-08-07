package cn.ucaner.wx.chat.bot.utils;

/**
 * @ClassName：TimeUtils
 * @Description： <p> TimeUtils </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:43
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class TimeUtils {

    public static int getTime(){
        long time = System.currentTimeMillis();
        return (int)(time/1000);
    }

}
