

D:\software\java\java1.8\jre1.8\bin\java.exe  -jar selenium-server-standalone-3.14.0.jar -role hub 



java -jar selenium-server-standalone-2.40.0.jar -role node -hub <http://localhost:4444/wd/hub> -browser “browserName=chrome,maxinstance=1,platform=WINDOWS” -Dwebdriver.chrome.driver=F://Anaconda3//envs//tensorflow//chromedriver.exe

# issue

```powershell
C:\Users\ding\Desktop>java -jar selenium-server-standalone-3.14.0.jar -role hub
Exception in thread "main" java.lang.UnsupportedClassVersionError: org/openqa/grid/selenium/GridLauncherV3 : Unsupported major.minor version 52.0
        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(ClassLoader.java:800)
        at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
        at java.net.URLClassLoader.defineClass(URLClassLoader.java:449)
        at java.net.URLClassLoader.access$100(URLClassLoader.java:71)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:361)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:355)
        at java.security.AccessController.doPrivileged(Native Method)
        at java.net.URLClassLoader.findClass(URLClassLoader.java:354)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:425)
        at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:308)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:358)
        at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:482)

C:\Users\ding\Desktop>java -version
java version "1.7.0_65"
Java(TM) SE Runtime Environment (build 1.7.0_65-b19)
Java HotSpot(TM) Client VM (build 24.65-b04, mixed mode, sharing)

C:\Users\ding\Desktop>D:\software\java\java1.8\jre1.8\bin\java.exe  -jar selenium-server-standalone-3.14.0.jar -role hub
16:43:44.952 INFO [GridLauncherV3.launch] - Selenium build info: version: '3.14.0', revision: 'aacccce0'
16:43:44.957 INFO [GridLauncherV3$2.launch] - Launching Selenium Grid hub on port 4444
2018-08-25 16:43:46.262:INFO::main: Logging initialized @8864ms to org.seleniumhq.jetty9.util.log.StdErrLog
16:43:48.179 INFO [Hub.start] - Selenium Grid hub is up and running
16:43:48.180 INFO [Hub.start] - Nodes should register to http://192.168.140.1:4444/grid/register/
16:43:48.180 INFO [Hub.start] - Clients should connect to http://192.168.140.1:4444/wd/hub
```

> That message (with version `52.0`) indicates that Selenium server requires java 6 to run, but you're trying to run it with some earlier version of java.
>
> f you have java 6 installed, but it isn't getting used, you may have to give the full path name to the java executable, so something like this if on linux:
>
> ```java
> /usr/local/jre6/bin/java -jar selenium.jar
> ```
>
> or like this if on windows:
>
> ```java
> "C:\Program Files (x86)\Java\jre6\bin\java.exe" -jar selenium.jar
> ```







```powershell
selenium.common.exceptions.WebDriverException: Message: unknown error: call function result missing 'value'
  (Session info: chrome=68.0.3440.106)
  (Driver info: chromedriver=2.28.455520 (cc17746adff54984afff480136733114c6b3704b),platform=Windows NT 10.0.16299 x86_64)
```







```

```

remote 模式运行 必须开启 hub和node