package cn.ucaner.wx.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* @Package：cn.ucaner.wx.app.gateway   
* @ClassName：Application   
* @Description：   <p> Application </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午10:55:00   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@SpringBootApplication(scanBasePackages={"cn.ucaner.wx.app.core","cn.ucaner.wx.app.service" ,"cn.ucaner.wx.app.gateway"})
@EnableTransactionManagement
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}