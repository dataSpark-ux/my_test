package com.wy.newblog.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wy
 * @Description 堆益出
 * 限制堆的大小为 20MB(将堆的最小值-Xms和最大值-Xmx参数设置为一样即可避免堆自动扩展)
 * 通过-XX:+HeapDumpOnOutOfMemoryError 可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析
 * VM :   -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @createTime 2019/04/29
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }


    }

    static class OOMObject{

    }
}
