package com.wy.newblog.annotation;

import java.lang.annotation.*;

/**
 * @author
 * @Date 2018/9/17 13:33
 * @Description 权限注解
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {
    String[] value() default {};

    String[] authorities() default {};

    String[] roles() default {};
}
