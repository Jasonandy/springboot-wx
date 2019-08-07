package cn.ucaner.wx.chat.bot.service;

/**
 * @ClassName：QrCodeService
 * @Description： <p> QrCodeService </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:48
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public interface QrCodeService {

    void show(String uuid);

    void close();

}
