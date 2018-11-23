/**
 *<html>
 *<body>
 *	<P> Copyright(c)  ● JasonInternational ◆ Jason <jasonandy@hotail.com></p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.utils.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

/**
* @Package：cn.ucaner.wx.app.core.utils.captcha   
* @ClassName：CaptchaUtil   
* @Description：   <p> 验证码工具类</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:45:57   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class CaptchaUtil {
	
	/**
	 * 图片的宽度-默认-defalut
	 */
	private int width = 160;
	
	/**
	 *  图片的高度 -默认  -defalut 
	 */
	private int height = 40;
	
	/**
	 * 验证码字符个数- 默认
	 */
	private int codeCount = 4;
	
	/**
	 * 验证码干扰线数
	 */
	private int lineCount = 20;
	
	/**
	 * 验证码
	 */
	private String code = null;
	
	/**
	 * 验证码图片buffer 
	 */
	private BufferedImage buffImg = null;
	
	
	Random random = new Random();

	/**
	* CaptchaUtil.创建默认的图片.
	 */
	public CaptchaUtil() {
		creatImage();
	}
	
	/**
	* CaptchaUtil. 
	* @param width   宽
	* @param height  高
	 */
	public CaptchaUtil(int width, int height) {
		this.width = width;
		this.height = height;
		creatImage();
	}

	/**
	* CaptchaUtil.  
	* @param width 宽
	* @param height 高
	* @param codeCount 字符串个数
	 */
	public CaptchaUtil(int width, int height, int codeCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		creatImage();
	}

	/**
	* CaptchaUtil. 
	* @param width 
	* @param height
	* @param codeCount
	* @param lineCount   -  加干扰线
	 */
	public CaptchaUtil(int width, int height, int codeCount, int lineCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		this.lineCount = lineCount;
		creatImage();
	}

	/**
	 * @Description: 生成图片
	 * @Autor: Jason
	 */
	private void creatImage() {
		
		// 字体的宽度
		int fontWidth = width / codeCount;
		
		// 字体的高度
		int fontHeight = height - 5;
		int codeY = height - 8;

		// 图像buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();
		//Graphics2D g = buffImg.createGraphics();
		
		/**
		 *获取指定范围的随机颜色-随机码的背景色 
		 */
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);


		// 设置字体
		Font font = getFont(fontHeight);
		//Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		g.setFont(font);

		// 设置干扰线
		for (int i = 0; i < lineCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width);
			int ye = ys + random.nextInt(height);
			g.setColor(getRandColor(1, 255));
			g.drawLine(xs, ys, xe, ye);
		}

		// 添加噪点
		float yawpRate = 0.01f;// 噪声率
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			buffImg.setRGB(x, y, random.nextInt(255));
		}

		String str1 = randomStr(codeCount);// 得到随机字符
		this.code = str1;
		for (int i = 0; i < codeCount; i++) {
			String strRand = str1.substring(i, i + 1);
			g.setColor(getRandColor(1, 255));
			// g.drawString(a,x,y);
			// a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处

			g.drawString(strRand, i * fontWidth + 3, codeY);
		}
	}

	/**
	 * @Description: 获取n长度的随机字符
	 * @param n      字符串的长度
	 * @return String
	 * @Autor: Jason
	 */
	private String randomStr(int n) {
		String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		String str2 = "";
		int len = str1.length() - 1;
		double r;
		for (int i = 0; i < n; i++) {
			r = (Math.random()) * len;
			str2 = str2 + str1.charAt((int) r);
		}
		return str2;
	}

	/**
	 * @Description: 获取随机的颜色
	 * @param fc
	 * @param bc
	 * @return Color
	 * @Autor: Jason
	 */
	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * @Description:产生随机字体
	 * @param size
	 * @return Font
	 * @Autor: Jason
	 */
	private Font getFont(int size) {
		Random random = new Random();
		Font[] font = new Font[5];
		font[0] = new Font("Ravie", Font.PLAIN, size);
		font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
		font[2] = new Font("Fixedsys", Font.PLAIN, size);
		font[3] = new Font("Wide Latin", Font.PLAIN, size);
		font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
		return font[random.nextInt(5)];
	}

	/**
	 * @Description: 扭曲方法
	 * @param g
	 * @param w1
	 * @param h1
	 * @param color void
	 * @Autor: Jason
	 */
	@SuppressWarnings("unused")
	private void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	
	/**
	 * @Description: shearX 线条
	 * @param g
	 * @param w1
	 * @param h1
	 * @param color void
	 * @Autor: Jason
	 */
	private void shearX(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
					+ (6.2831853071795862D * (double) phase)
					/ (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}
	}

	/**
	 * @Description: 线条y
	 * @param g
	 * @param w1
	 * @param h1
	 * @param color void
	 * @Autor: Jason
	 */
	private void shearY(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
					+ (6.2831853071795862D * (double) phase)
					/ (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}

	
	/**
	 * @Description: 输入流数据
	 * @param sos
	 * @throws IOException void
	 * @Autor: Jason
	 */
	public void write(OutputStream sos) throws IOException {
		ImageIO.write(buffImg, "png", sos);
		sos.close();
	}

	/**
	 * @Description: 获取图片流
	 * @return BufferedImage
	 * @Autor: Jason
	 */
	public BufferedImage getBuffImg() {
		return buffImg;
	}

	/**
	 * @Description:获取小写code
	 * @return String
	 * @Autor: Jason
	 */
	public String getCode() {
		return code.toLowerCase();
	}
	
	/**
	 * @Description:    将生成的图片流写到指定的磁盘位置
	 * @param file      file文件对象
	 * @param path       输出的文件的路径
	 * @param ext        拓展ext名   - “.png”
	 * @Autor: Jason
	 */
	public static void writeRandomCode(CaptchaUtil cu,File file,String path,String ext) {
		OutputStream out = null ;
		if (file == null) {
			file = new File(path+ext);
	     }
		 try {
			 out = new FileOutputStream(file);
			 cu.write(out);
			 out.close();
		 } catch (FileNotFoundException e) {
			//文件未找到异常
			e.printStackTrace();
		 } catch (IOException e) {
			//io异常
			e.printStackTrace();
		 }   
	}
	
	/**
	 * @Description:获取日期格式
	 * @return String
	 * @Autor: Jason
	 */
	public static String getTimeNoFlag() {
		Calendar calendar = Calendar.getInstance();
		return formatDate(calendar.getTime(), "yyyy-MMdd-hhmm-ss");
	}
	
	/**
	 * @Description: 格式化时间
	 * @param date
	 * @param format
	 * @return String
	 * @Autor: Jason
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	 * test 测试生产指定的随机的验证码图片 保存到指定的位置   - need fix  优化生成的文件的随机名称问题 
	 * fix 优化并发问题
	 * @Description: Just for test
	 * @Autor: jasonandy@hotmail.com
	 */
	public static void main(String[] args) throws IOException {
		CaptchaUtil cu = new CaptchaUtil(800,400,6,60);
		 writeRandomCode(cu,null, "D:" + File.separator + getTimeNoFlag(),".png");
	}

}
