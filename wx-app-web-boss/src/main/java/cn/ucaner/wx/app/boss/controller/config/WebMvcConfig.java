/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.boss.controller.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.JSON;

/**
* @Package：cn.ucaner.wx.app.boss.controller.config   
* @ClassName：WebMvcConfig   
* @Description：   <p> WebMvcConfig springMvc静态资源配置 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:41:06   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
	
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/common/**").addResourceLocations("classpath:/common/");
		 registry.addResourceHandler("/dwz/**").addResourceLocations("classpath:/dwz/");
		 logger.debug("SpringMvc配置加载成功,详细信息为:{}",JSON.toJSONString(registry));
	}
    
}
