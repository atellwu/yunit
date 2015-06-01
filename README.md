# Yunit 是什么

1. Yeahmobi 自己的单元测试框架  
2. 做的事情比较少，主要是搭建框架，集成其他开源单元测试工具，目的是统一公司的 UT 技术选型，以及提供常见场景的sample
2. 集成了一些常用的工具，比如 spring，junit，dbunit，jmock 等
3. 集成了 spring-test-dbunit （将 spring-test 和 dbunit 集成一起的框架），在 spring-test 基础上，对 dbunit 的 setup 和 expected 增加了对默认的路径的支持
4. 将 case-by-case 地逐步提供各种场景的 sample，如 Dao，restful接口，远程service，等

# Maven依赖
一  需要把公司的私有仓库添加到pom设置中

    <repositories>
        <repository>
            <id>yeahmobi-releases</id>
            <name>Repository for yeahmobi releases</name>
	    <url>http://172.20.0.77:8081/nexus/content/repositories/releases/</url>
        </repository>
	</repositories>


也可以修改setting.xml文件，把私服的releases库加上
 (1) 在profiles标签里面加上

      <profile>
         <id>yeahmobi-releases</id>
         <repositories>
	         <repository>
		      <id>yeahmobi-releases</id>
		      <name>Repository for yeahmobi releases</name>     
				      <url>http://172.20.0.77:8081/nexus/content/repositories/releases/</url>
		    </repository> 
	     </repositories>
    </profile>

(2) 在activeProfiles标签里面加上
	`<activeProfile>yeahmobi-releases</activeProfile>`
 
二  添加 Yunit (yeahmobi's unittest framework) 依赖，该框架的目的是集成常用的 ut 工具( junit, jmock, dbunit 等)，为公司提供统一的 ut 方案。

    <!-- 单元测试 -->
	<dependency>
	    <groupId>com.yeahmobi.yunit</groupId>
	    <artifactId>yunit</artifactId>
	    <version>0.0.1-SNAPSHOT</version>
	    <scope>test</scope>
	</dependency>
	<!-- yunit 已经依赖了 junit, jmock, dbunit, spring-test 等工具，所以不需要再显式依赖 -->

 
 
大家可以把 Yunit 下载下来 ( svn://svn.dy/platform/yunit/trunk 如果svn无法访问，也可以：https://github.com/ndpmedia/yunit  )。基于该框架，目前有一些sample，供大家参考。
 
