

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





## 2 maven的远程中央仓库无妨访问

```powershell
C:\Users\ding\Desktop\netty-in-action-2.0-SNAPSHOT>mvn compile
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
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ utils ---
[WARNING] Using platform encoding (GBK actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory C:\Users\ding\Desktop\netty-in-action-2.0-SNAPSHOT\utils\src\main\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.5.1:compile (default-compile) @ utils ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Sample Code for Netty in Action 2.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Chapter 1. Netty—asynchronous and event-driven 2.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
Downloading: https://repo.maven.apache.org/maven2/io/netty/netty-all/4.1.20.Final/netty-all-4.1.20.Final.pom
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] Common Utilities ................................... SUCCESS [  0.655 s]
[INFO] Sample Code for Netty in Action .................... SUCCESS [  0.002 s]
[INFO] Chapter 1. Netty—asynchronous and event-driven ..... FAILURE [  4.131 s]
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
[INFO] Total time: 4.991 s
[INFO] Finished at: 2018-08-27T00:51:49+08:00
[INFO] Final Memory: 10M/25M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal on project chapter1: Could not resolve dependencies for project nia:chapter1:jar:2.0-SNAPSHOT: Failed to collect dependencies at io.netty:netty-all:jar:4.1.20.Final: Failed to read artifact descriptor for io.netty:netty-all:jar:4.1.20.Final: Could not transfer artifact io.netty:netty-all:pom:4.1.20.Final from/to central (https://repo.maven.apache.org/maven2): Received fatal alert: protocol_version -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/DependencyResolutionException
[ERROR]
[ERROR] After correcting the problems, you can resume the build with the command
[ERROR]   mvn <goals> -rf :chapter1

```



Could not transfer artifact io.netty:netty-all:pom:4.1.20.Final from/to central (https://repo.maven.apache.org/maven2): 

说明maven无法访问https://repo.maven.apache.org/maven2

该配置在在maven的安装位置的lib目录下面有个maven-model-builder-3.5.4jar文件

解压此jar包。寻找maven-model-builder-3.5.4\org\apache\maven\model\pom-4.0.0.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!--
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
-->

<!-- START SNIPPET: superpom -->
<project>
  <modelVersion>4.0.0</modelVersion>

  <repositories>
    <repository>
      <id>central</id>
      <name>Central Repository</name>
      <url>https://repo.maven.apache.org/maven2</url>
      <layout>default</layout>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
    </repository>
  </repositories>

  <pluginRepositories>
    <pluginRepository>
      <id>central</id>
      <name>Central Repository</name>
      <url>https://repo.maven.apache.org/maven2</url>
      <layout>default</layout>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <releases>
        <updatePolicy>never</updatePolicy>
      </releases>
    </pluginRepository>
  </pluginRepositories>

  <build>
    <directory>${project.basedir}/target</directory>
    <outputDirectory>${project.build.directory}/classes</outputDirectory>
    <finalName>${project.artifactId}-${project.version}</finalName>
    <testOutputDirectory>${project.build.directory}/test-classes</testOutputDirectory>
    <sourceDirectory>${project.basedir}/src/main/java</sourceDirectory>
    <scriptSourceDirectory>${project.basedir}/src/main/scripts</scriptSourceDirectory>
    <testSourceDirectory>${project.basedir}/src/test/java</testSourceDirectory>
    <resources>
      <resource>
        <directory>${project.basedir}/src/main/resources</directory>
      </resource>
    </resources>
    <testResources>
      <testResource>
        <directory>${project.basedir}/src/test/resources</directory>
      </testResource>
    </testResources>
    <pluginManagement>
      <!-- NOTE: These plugins will be removed from future versions of the super POM -->
      <!-- They are kept for the moment as they are very unlikely to conflict with lifecycle mappings (MNG-4453) -->
      <plugins>
        <plugin>
          <artifactId>maven-antrun-plugin</artifactId>
          <version>1.3</version>
        </plugin>
        <plugin>
          <artifactId>maven-assembly-plugin</artifactId>
          <version>2.2-beta-5</version>
        </plugin>
        <plugin>
          <artifactId>maven-dependency-plugin</artifactId>
          <version>2.8</version>
        </plugin>
        <plugin>
          <artifactId>maven-release-plugin</artifactId>
          <version>2.5.3</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>

  <reporting>
    <outputDirectory>${project.build.directory}/site</outputDirectory>
  </reporting>

  <profiles>
    <!-- NOTE: The release profile will be removed from future versions of the super POM -->
    <profile>
      <id>release-profile</id>

      <activation>
        <property>
          <name>performRelease</name>
          <value>true</value>
        </property>
      </activation>

      <build>
        <plugins>
          <plugin>
            <inherited>true</inherited>
            <artifactId>maven-source-plugin</artifactId>
            <executions>
              <execution>
                <id>attach-sources</id>
                <goals>
                  <goal>jar-no-fork</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
          <plugin>
            <inherited>true</inherited>
            <artifactId>maven-javadoc-plugin</artifactId>
            <executions>
              <execution>
                <id>attach-javadocs</id>
                <goals>
                  <goal>jar</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
          <plugin>
            <inherited>true</inherited>
            <artifactId>maven-deploy-plugin</artifactId>
            <configuration>
              <updateReleaseInfo>true</updateReleaseInfo>
            </configuration>
          </plugin>
        </plugins>
      </build>
    </profile>
  </profiles>

</project>
<!-- END SNIPPET: superpom -->

```



不需要修改该xml文件，直接在maven 的配置文件中配置国内镜像



在"D:\software\apache-maven-3.5.0\conf\settings.xml"下添加镜像

```xml
<mirrors>
    <!-- mirror
     | Specifies a repository mirror site to use instead of a given repository. The repository that
     | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used
     | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.
     |
    <mirror>
      <id>mirrorId</id>
      <mirrorOf>repositoryId</mirrorOf>
      <name>Human Readable Name for this Mirror.</name>
      <url>http://my.repository.com/repo/path</url>
    </mirror>
     -->
     
     
    
    <mirror>  
    <id>alimaven</id>  
  <name>aliyun maven</name>  
  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>  
  <mirrorOf>central</mirrorOf>          
   </mirror>  
    <mirror>
      <!--This sends everything else to /public -->
      <id>nexus</id>
      <mirrorOf>*</mirrorOf> 
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    </mirror>
    <mirror>    
      <id>ibiblio.org</id>    
      <name>ibiblio Mirror of http://repo1.maven.org/maven2/</name>    
      <url>http://mirrors.ibiblio.org/pub/mirrors/maven2</url>    
      <mirrorOf>central</mirrorOf>    
   </mirror>  
```

