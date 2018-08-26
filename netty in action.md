

# issue



```powershell

C:\Users\ding\Desktop\netty-in-action-2.0-SNAPSHOT>mvn install
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO]
[INFO] Common Utilities
[INFO] Sample Code for Netty in Action
[INFO] Chapter 1. Netty—asynchronous and event-driven
[INFO] Chapter 2. Your First Netty Application - Echo App
[INFO] Chapter 2. Echo Client
[INFO] Chapter 2. Echo Server
[INFO] Chapter 4. Transports
[INFO] Chapter 5. ByteBuf
[INFO] Chapter 6. ChannelHandler and ChannelPipeline
[INFO] Chapter 7. EEventLoop and threading model
[INFO] Chapter 8. Bootstrapping
[INFO] Chapter 9. Unit testing
[INFO] Chapter 10: The codec framework
[INFO] Chapter 11. Provided ChannelHandlers and codecs
[INFO] Chapter 12. WebSocket
[INFO] Chapter 13. Broadcasting Events with UDP
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Common Utilities 2.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
Downloading: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/3.3/maven-compiler-plugin-3.3.pom
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] Common Utilities ................................... FAILURE [  1.398 s]
[INFO] Sample Code for Netty in Action .................... SKIPPED
[INFO] Chapter 1. Netty—asynchronous and event-driven ..... SKIPPED
[INFO] Chapter 2. Your First Netty Application - Echo App . SKIPPED
[INFO] Chapter 2. Echo Client ............................. SKIPPED
[INFO] Chapter 2. Echo Server ............................. SKIPPED
[INFO] Chapter 4. Transports .............................. SKIPPED
[INFO] Chapter 5. ByteBuf ................................. SKIPPED
[INFO] Chapter 6. ChannelHandler and ChannelPipeline ...... SKIPPED
[INFO] Chapter 7. EEventLoop and threading model .......... SKIPPED
[INFO] Chapter 8. Bootstrapping ........................... SKIPPED
[INFO] Chapter 9. Unit testing ............................ SKIPPED
[INFO] Chapter 10: The codec framework .................... SKIPPED
[INFO] Chapter 11. Provided ChannelHandlers and codecs .... SKIPPED
[INFO] Chapter 12. WebSocket .............................. SKIPPED
[INFO] Chapter 13. Broadcasting Events with UDP ........... SKIPPED
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.576 s
[INFO] Finished at: 2018-08-27T00:00:49+08:00
[INFO] Final Memory: 7M/18M
[INFO] ------------------------------------------------------------------------
[ERROR] Plugin org.apache.maven.plugins:maven-compiler-plugin:3.3 or one of its dependencies could not be resolved: Failed to read artifact descriptor for org.apache.maven.plugins:maven-compiler-plugin:jar:3.3: Could not transfer artifact org.apache.maven.plugins:maven-compiler-plugin:pom:3.3 from/to central (https://repo.maven.apache.org/maven2): Received fatal alert: protocol_version -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginResolutionException

```

https://github.com/normanmaurer/netty-in-action 下载源码后 执行 mvn  install报错



修改最顶层pom.xml和utis包下的pom.xml文件，修改 maven-compiler-plugin的版本