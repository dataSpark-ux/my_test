package com.wy.newblog.common;

import com.wy.newblog.common.utils.SnowFlakeUtil;

/**
 * @author
 * @Date 2018/9/15 15:36
 * @Description 程序中的公用常量
 * @Version 1.0
 */
public class CommonConst {
    /**
     * 安全密码(UUID生成)，作为盐值用于用户密码的加密
     */
    public static final String SECURITY_KEY = "929123f8f17944e8b0a531045453e1f1";

    public static final Long ID = SnowFlakeUtil.getFlowIdInstance().nextId();

    /**
     * NX|XX, NX——只有在键不存在时才设置它。XX——只有在键已经存在时才设置它
     */
    public static final String NOT_EXIST = "NX";
    public static final String EXIST = "XX";

    /**
     * exx |PX，过期时间单位:EX =秒;PX =毫秒
     **/
    public static final String SECONDS = "EX";
    public static final String MILLISECONDS = "PX";

    public static final String LOCK = "lock";
}
