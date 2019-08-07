package cn.ucaner.wx.chat.bot.windows;

import cn.ucaner.wx.chat.bot.biz.common.config.CommonConst;
import cn.ucaner.wx.chat.bot.utils.FileUtils;
import org.springframework.stereotype.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * @ClassName：QRCodeFrame
 * @Description： <p> QRCodeFrame </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:45
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Component
public class QrCodeFrame extends JFrame {

    private Integer time = CommonConst.WECHAT_QRCODE_TIME;

    /**
     * 处理异常
     */
    private static ScheduledExecutorService monitorExecutor;

    private static  final long INITIAL_DELAY = 3 * 1000L;
    private static  final long PERIOD = 3 * 1000L;

    private String qrcode;

    public void launchFrame(String qrcode) {
        this.qrcode = qrcode;
        updateFrameTitle();
        this.setVisible(true);
        this.setSize(420, 440);
        this.setLocation(100, 100);
        JPanel jPanel = new QrCodeFrameImage(this.qrcode);
        this.getContentPane().add(jPanel);
        this.setAlwaysOnTop(true);
    }

    public void closeWindows(){
        this.dispose();
        if(null != monitorExecutor){
            monitorExecutor=null;
        }
    }

    private void updateFrameTitle(){
        if(null == monitorExecutor){
            return;
        }
        monitorExecutor = newScheduledThreadPool(1);
        /**
         * 处理掉异常的连接
         */
        monitorExecutor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time--;
                if(time<1){
                    closeWindows();
                    FileUtils.deleteFile(qrcode);
                }
                setTitle(String.format("请使用微信扫描二维码,%s秒后自动关闭",time));
                updateFrameTitle();
            }
        }, INITIAL_DELAY, PERIOD, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        new QrCodeFrame().launchFrame("E:\\MyCoders\\weixin-bot\\target\\classes4eJwd3TgDg==");
    }

    public int getTime() {
        return time;
    }
}
