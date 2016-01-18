#Thread.State
---
> State是Thread类的内部类，是个枚举类型。它主要是用来标识线程的当前状态。

##State状态种类 ##

 - NEW
 - RUNNABLE
 - BLOCKED
 - WAITING
 - TIMED_WAITING
 - TERMINATED
 
##State详解 ##
> 注意：在给定时间点上，一个线程只能处于一种状态。这些状态是虚拟机状态，它们并没有反映所有操作系统线程状态。

### NEW ###
> 是指尚未启动的线程。通常是指在new Thread()后，start()方法之前。

### RUNNABLE ###
> 是指该线程正在运行的状态。通常是指执行start()方法后。

### BLOCKED ###
> 是指该线程正在处于阻塞状态。处于阻塞状态的线程通常是指正在等待监视器进入synchronized块、方法或重新进入一个synchronized块、方法。

### WAITING ###
> 是指该为等待另一个线程来执行特定操作。通常在执行以下几个方法会显示这个状态：
 - Object.wait()
 - Thread.join()
 - LockSupport.park()

### TIMED_WAITING ###
> 是指该线程为等待指定的时间。通常在执行以下几个方法会显示这个状态：
 - Thread.sleep(long)或者TimeUnit.SECONDS.sleep(long)
 - Object.wait(long)
 - Thread.join(long)
 - LockSupport.parkNanos(long)
 - LockSupport.parkUntil(long)
 - Condition.awaitNanos(long)
 - Condition.awaitUntil(Date)

### TERMINATED ###
> 是指该线程已终止。通常run()方法里执行完成。

