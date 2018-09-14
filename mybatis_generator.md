### 使用Mybatis Generator生成代码

- 下载Mybatis Generator core

  https://github.com/mybatis/generator/releases  [**mybatis-generator-core-1.3.7.zip**](https://github.com/mybatis/generator/releases/download/mybatis-generator-1.3.7/mybatis-generator-core-1.3.7.zip)

  注意使用的jdk版本

  或者maven

  ```xml
  The new artifacts are available in Maven central at
  <dependency>
      <groupId>org.mybatis.generator</groupId>
      <artifactId>mybatis-generator</artifactId>
      <version>1.3.7</version>
  </dependency>
  and
  <dependency>
      <groupId>org.mybatis.generator</groupId>
      <artifactId>mybatis-generator-maven-plugin</artifactId>
      <version>1.3.7</version>
  </dependency>
  ```

- 解压zip文件进入lib文件夹，改文件夹下有三个jar包

  >mybatis-generator-core-1.3.7.jar
  >
  >mybatis-generator-core-1.3.7-javadoc.jar
  >
  >mybatis-generator-core-1.3.7-sources.jar
  >
  >

- 创建generatorConfig.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE generatorConfiguration
    PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
    "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
  <generatorConfiguration>
  
      <!--数据库驱动  指定驱动的位置，可以直接放到lib下-->
      <classPathEntry    location="mysql-connector-java-5.1.13.jar"/>
  
      <context id="DB2Tables"    targetRuntime="MyBatis3">
          <commentGenerator>
              <property name="suppressDate" value="true"/>
              <property name="suppressAllComments" value="true"/>
          </commentGenerator>
  		
          <!--数据库链接地址账号密码-->
          <jdbcConnection driverClass="com.mysql.jdbc.Driver" 
  			connectionURL="jdbc:mysql://10.200.3.184:3306/lvmama_pay" 
  			userId="lvmama_pay" 
  			password="lvmama_pay">
          </jdbcConnection>
  		
          <javaTypeResolver>
              <property name="forceBigDecimals" value="false"/>
          </javaTypeResolver>
  
          <!--生成Model类存放位置-->
          <javaModelGenerator targetPackage="com.lvmama.util.model" targetProject="src">
              <property name="enableSubPackages" value="true"/>
              <property name="trimStrings" value="true"/>
          </javaModelGenerator>
  
          <!--生成映射文件存放位置-->
          <sqlMapGenerator targetPackage="com.lvmama.util.mapping" targetProject="src">
              <property name="enableSubPackages" value="true"/>
          </sqlMapGenerator>
  
          <!--生成Dao类存放位置-->
          <javaClientGenerator type="XMLMAPPER" targetPackage="com.lvmama.util.dao" targetProject="src">
              <property name="enableSubPackages" value="true"/>
          </javaClientGenerator>
  
  		
  		
  		
          <!--生成对应表及类名-->
          <table tableName="pay_payment" domainObjectName="payment" 
  		
  			enableCountByExample="false" 
  			enableUpdateByExample="false" 
  			enableDeleteByExample="false" 
  			enableSelectByExample="false" 
  			selectByExampleQueryId="false">
  		</table>
  		<!--
  		
  		
  		<table tableName="pay_payment" domainObjectName="payment" 
  		
  			enableCountByExample="true" 
  			enableUpdateByExample="true" 
  			enableDeleteByExample="true" 
  			enableSelectByExample="true" 
  			selectByExampleQueryId="true">
  		</table>
  		
  		This class is used to work with MBG's dynamic select capability. 
  		The class holds a set of criteria that are used to generate a dynamic WHERE clause at runtime for the following methods:
  		selectByExample
  		selectByExampleWithBLOBs
  		deleteByExample
  		countByExample
  		updateByExample
  		This class does not extend any of the other model classes.
  	
  		The name of the class will be «TableName»Example by default, or «domainObjectName»Example if the domainObjectName attribute 
  		is specified on the <table> configuration element.
  	
  		This class will be generated if any of the *ByExample methods are enabled. 
  		Note that this class can grow quite large if there are many fields in a table, 
  		but the DAO methods are small as is the generated XML fragment. 
  		If you do not plan to use the dynamic WHERE clause features, you may prefer to disable the generation of these methods.
  		
  		-->
  		
  
      </context>
  
  </generatorConfiguration>
  ```

- cmd运行命令生成代码文件

  ```powershell
  cmd 中直接执行命令  java必须是1.8的
  	java -jar mybatis-generator-core-1.3.5.jar -configfile generatorConfig.xml -overwrite
  
  eg:
  	D:\software\Java1.8\jdk1.8.0_172\bin\java -jar mybatis-generator-core-1.3.7.jar -configfile generatorConfig.xml -overwrite
  ```

- >官方参考配置地址
  >
  >http://www.mybatis.org/generator/configreference/generatedKey.html#
  >
  >