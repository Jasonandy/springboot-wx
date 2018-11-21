/**
 * <html>
 * <body>
 *  <P> Copyright JasonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason  https://github.com/Jasonandy/springboot-wx</p>
 *  </body>
 * </html>
 */
package cn.ucaner.wx.app.gateway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Package：cn.ucaner.wx.app.gateway.annotation   
* @ClassName：LoginUser   
* @Description：   <p> LoginUser </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:26:46   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
