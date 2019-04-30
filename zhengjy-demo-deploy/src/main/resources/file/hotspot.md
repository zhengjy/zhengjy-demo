### Prims

![1546588536(1)](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\hotspot\1546588536(1).png)

##### JNI模块

​	JNI模块提供JAVA允许时接口，定义了许多以"jni_"为前缀命名的函数，允许JDK或外部程序调用由C/C++实现的库函数。

##### JVM模块

​	JVM模块中，虚拟机向外提供了一些函数，以"JVM_"为前缀名，作为标准接口的补充。这些函数可以分为3类

​		1)一些与JVM相关的函数，用来支撑一些需要访问本地库的java api。
​	![1546588620(1)](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\hotspot\1546588620(1).png)

​		2)一些函数和常量定义，用来支持字节码验证和Class文件格式校验。

![1546588630(1)](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\hotspot\1546588630(1).png)

​		3)各种I/O和网络操作，用来支持java I/O和网络API。

![1546588638(1)](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\hotspot\1546588638(1).png)

##### JVMTL模块

​	java虚拟机工具接口，提供了一种编程接口，允许程序员创建代理以监视和控制java应用程序。JVMTL代理常用于对应用程序进行
​	监控和调优。例如监控内存实际使用情况、CPU使用率和锁的信息。

##### Perf模块

​	jdk中sun.misc.Perf类的底层实现，定义了一些以"Perf_"为前缀命名的函数，由外部程序调用，以监控虚拟机内部的PerfData计数器。



