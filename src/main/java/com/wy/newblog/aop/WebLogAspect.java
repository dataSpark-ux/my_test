package com.wy.newblog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @Description: WebLogAspect
 * author wy
 * @date 2018/9/16 11:18
 * version V1.0.0
 * 使用@Aspect注解将一个java类定义为切面类
 * 使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
 * @Order(i)i的值越小，优先值越高 在切入点前的操作，按order的值由小到大执行
 * 在切入点后的操作，按order的值由大到小执行
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    /**
     * 此处会有同步问题，引入ThreadLocal对象
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义一个切入点.
     * 解释下：
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     * <p>
     * <p>
     * 根据需要在切入点不同位置的切入内容
     * 使用@Before在切入点开始处切入内容
     * 使用@After在切入点结尾处切入内容
     * 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     * 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
     * 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
     */

    @Pointcut("execution(public * com.wy.newblog.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数方法一：
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            System.out.println(paraName + ": " + request.getParameter(paraName));
        }
    }


    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }


}
