package cn.ucaner.wx.app.boss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* @Package：cn.ucaner.wx.app.boss   
* @ClassName：Application   
* @Description：   <p> Application  boss 后台管理系统 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:44:21   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@SpringBootApplication(scanBasePackages={"cn.ucaner.wx.app.core","cn.ucaner.wx.app.service" ,"cn.ucaner.wx.app.gateway","cn.ucaner.wx.app.boss"})
//@MapperScan({"com.wanguo.wx.service.*.dao"})
@EnableTransactionManagement
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}