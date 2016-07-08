package com.howard.juc.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by howard on 16/6/1.
 *
 * Thread name:
 * 1. main 用户线程 程序入口
 * 2. Reference Handler JVM系统线程 清除Reference的线程
 * 3. Finalizer 系统线程 调用对象finalize方法的线程
 * 4. Signal Dispatcher 系统线程 分发处理发送给JVM信号的线程
 * 9. Monitor Ctrl-Break 系统线程
 */
public class ThreadMXBeamTest {

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.toString());
        }
    }
}
