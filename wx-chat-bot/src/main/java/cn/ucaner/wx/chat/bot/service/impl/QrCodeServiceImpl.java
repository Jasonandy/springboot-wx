package cn.ucaner.wx.chat.bot.service.impl;

import cn.ucaner.wx.chat.bot.biz.common.config.CommonConst;
import cn.ucaner.wx.chat.bot.service.QrCodeService;
import cn.ucaner.wx.chat.bot.utils.QrcodeUtils;
import cn.ucaner.wx.chat.bot.utils.WechatApiPool;
import cn.ucaner.wx.chat.bot.windows.QrCodeFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @ClassName：QrCodeServiceImpl
 * @Description： <p> QrCodeServiceImpl </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:00
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Service
public class QrCodeServiceImpl implements QrCodeService {

    private static final Logger logger = LoggerFactory.getLogger(QrCodeServiceImpl.class);

    @Autowired
    private QrCodeFrame qrCodeFrame;

    private  ExecutorService loadQrCodeExec = newFixedThreadPool(3);

    @Override
    public void show(String uuid) {
        loadQrCodeExec.execute(() -> show(uuid));
    }

    @Override
    public void close() {
        qrCodeFrame.closeWindows();
    }

    /**
     * 显示二维码
     * @param uuid
     */
    public void showQRCode(String uuid){
        try {
            logger.info("正在渲染登陆二维码...");
            Path path = new File(String.format("%s/%s.png", CommonConst.WECHAT_CACHE_PATH,uuid)).toPath();
            logger.info("正在生成二维码,{}",path.toString());
            QrcodeUtils.createQrcode(String.format(WechatApiPool.QRCODE_FORMAT,uuid),path);
            qrCodeFrame.launchFrame(path.toString());
            logger.info("请使用微信扫描二维码,二维码将在[{}]秒后失效",qrCodeFrame.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
