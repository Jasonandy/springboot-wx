package cn.ucaner.wx.chat.bot.framework.common.util.ip;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;


/**
 * @ClassName：IpUtil
 * @Description： <p> IpUtil </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:56
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class IpUtil {

    /**
     * 获取主机ip地址
     * @return
     */
	public static String getHostIp() {
		Properties prop = System.getProperties();
		InetAddress addr;
		String ip = null;
		Enumeration<NetworkInterface> nis;
		try {
			nis = NetworkInterface.getNetworkInterfaces();
			for (; nis.hasMoreElements();) {
				NetworkInterface ni = nis.nextElement();
				Enumeration<InetAddress> ias = ni.getInetAddresses();
				for (; ias.hasMoreElements();) {
					InetAddress ia = ias.nextElement();
                    /**
                     * ia instanceof Inet6Address && !ia.equals("")
                     */
					if (ia instanceof Inet4Address && !"127.0.0.1".equals(ia.getHostAddress())) {
						ip = ia.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		String os = prop.getProperty("os.name");
		if (os != null && os.toLowerCase().startsWith("win")) {
			try {
				addr = InetAddress.getLocalHost();
				ip = addr.getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return ip;
	}

}
