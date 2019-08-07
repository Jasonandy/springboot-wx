package cn.ucaner.wx.chat.bot.windows;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * @ClassName：QrCodeFrameImage
 * @Description： <p> QrCodeFrameImage </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:45
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class QrCodeFrameImage extends JPanel {

    private String imagePath;

    public QrCodeFrameImage(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon icon = new ImageIcon(imagePath);
        g.drawImage(icon.getImage(), 0, 0, 400, 400, this);
    }

}
