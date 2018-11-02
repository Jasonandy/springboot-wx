package cn.ucaner.wx.app.gateway.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.ucaner.wx.app.gateway.annotation.support.LoginUserHandlerMethodArgumentResolver;

/**
* @Package：cn.ucaner.wx.app.gateway.config   
* @ClassName：UseConfig   
* @Description：   <p> UseConfig </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:28:18   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Configuration
public class UseConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserHandlerMethodArgumentResolver());
    }
    
    
}
