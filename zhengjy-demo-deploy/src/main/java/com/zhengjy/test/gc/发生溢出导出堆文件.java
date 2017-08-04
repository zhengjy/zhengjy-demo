package com.zhengjy.test.gc;

import java.util.Vector;

/**-XX+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=/d.adump 发生溢出导出堆文件  导出OOM的文件路径
 * -Xmx20m -Xms5m -XX:HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=/d.adump
 * Created by zhengjy on 2017/7/30.
 */
public class 发生溢出导出堆文件 {
    public static void main(String[] args) {
        Vector v = new Vector();
        for(int i=0;i<25;i++){
            v.add(new byte[1*1024*1024]);
        }
    }
}
