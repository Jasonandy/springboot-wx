package cn.ucaner.wx.chat.bot.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @ClassName：HttpAop
 * @Description： <p> HttpAop </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:32
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Aspect
@Component
public class HttpAop {

    private static final Logger logger = LoggerFactory.getLogger(HttpAop.class);

    /**
     * dao 方法切点
     */
    @Pointcut("execution(public * cn.ucaner.wx.chat.bot.dao.*.*(..))")
    public void daoMethod() {
    }

    @Around("daoMethod()")
    public Object daoMethod(ProceedingJoinPoint pjp) throws Throwable {
        // 获得参数
        Object[] args = pjp.getArgs();
        String signatureJson = pjp.getSignature().toString();
        String method = signatureJson.substring(signatureJson.lastIndexOf(".")+1,signatureJson.lastIndexOf("("));
        // 调用目标方法
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed(args);
        long end = System.currentTimeMillis();
        logger.info("请求结束：time = {}ms ,method = {}, result = {}", (end - start), method, JSON.toJSONString(proceed));
        return proceed;
    }

}
