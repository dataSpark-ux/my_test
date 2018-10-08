package com.wy.newblog.Stream.knowJava;

import com.wy.newblog.Stream.knowJava.poly.*;
import org.junit.Test;

/**
 * @author
 * @Date 2018/10/8 13:52
 * @Description TODO
 * @Version 1.0
 */
public class test {

    @Test
    public void poly() {
        //向上转型
        Water w = new WarWater();
        w.showTem();

        w = new IceWater();
        w.showTem();

        w = new HotWater();
        w.showTem();

        w = new Water();
        w.showTem();
    }


}
