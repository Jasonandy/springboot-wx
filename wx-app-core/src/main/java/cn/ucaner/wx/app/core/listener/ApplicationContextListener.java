package cn.ucaner.wx.app.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
* @Package：cn.ucaner.wx.app.core.listener   
* @ClassName：ApplicationContextListener   
* @Description：   <p> ApplicationContextListener 监听容器 可以用来做一些数据的初始化等</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:48:25   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
@Configuration
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // root application context 没有parent 说明它就是老大
        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
        	
        	init();//初始化方法  可以用来初始化redis缓存等 .. 
        	
        	logger.info(">>>> 喜大普奔 恭喜您 您的容器Context初始化成功.<<<");
        }
    }
    
    
    /**
     * @Description: init() 初始化方法
     * @Autor: Jason
     */
    public void init() {
    	logger.info("ApplicationContextListener - {}","Init方法初始化成功!");
    }

}
