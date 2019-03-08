<p align=center>
  <a href="https://github.com/Jasonandy/springboot-wx.git">
    <img src="http://upload-images.jianshu.io/upload_images/7802425-9eb1bcd006e34aa6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240" alt="wx-apps" >
  </a>
</p>

<p align="center">
	<a href="#"><img src="https://img.shields.io/badge/Bulid-Pass-blue.svg?style=for-the-badge" alt="wx-apps"></a>
	<a href="#"><img src="https://img.shields.io/badge/Author-Jason-orange.svg?style=for-the-badge" alt="wx-apps"></a>
</p>


# Ucaner
> You are what you want to be. - w.b

## 1.wx-apps 大概需要的实现的功能模块

微信小程序Java和后台

> 1.整合小程序
>
> 2.接口服务
>
> 3.对外提供api网关服务

## 2.代码模块的具体划分

> 1.wx-apps-core

一些基本的Core信息,登录校验,全局的常量,枚举类,base基类的抽取,通用的工具类的抽取,全局的响应状态码的定义,监听器容器初始化数据的加载配置和初始化
拦截器登录信息的校验,系统性能的日志统计[日志后期选型为logback]

> 2.wx-apps-service

业务模块的抽取，分为基本用户的CURD操作，线下营销人员的业务等，代码里面做细分

> 3.wx-apps-gateway

对内和对外的网关服务，对内提供小程序的 api接口，对外提供通信接口 
[后期加入 短信推送  小程序站内消息推送  公司配置化的邮箱推送提醒等接口]

异步和同步回调通知等相关api[后期优化]

> 4.wx-apps-web-boss

商户管理后台，加入Shiro控制权限细粒度，配置多个商户，进行增删改查等操作[可能考虑不做前后分离]
做的话优先Vue-Admin等开源的后台管理模块整合

> 5.wx-app-data

数据模块 后面需要对数据进行分析 集成elasticSearch  kafka  zookeeper

## 3.Jenkins 环境集成 SonarQube 质量检测

> 1.CentOS7 

> 2.Jenkins SonarQube 搭建


## 4.Eolinker Swagger api相关迭代开发

> 1.Eolinker 搭建


## 5.测试Bug提测进度管理

> 1.project排期

> 2.禅道


## 6.SpirngBoot maven 编译打包

> 1.插件实现编译打包

```xml
<!-- BUILD PROJECT -->
    <build>
		<!-- PACKAGE SOURCE LOCATION -->
        <resources>
            <resource>
            	<directory>src/main/resources</directory>
            </resource>
            <resource>
                <directory>src/main/java</directory>
	                <includes>
	                    <include>**/*.properties</include>
	                    <include>**/*.xml</include>
	                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
        
        <!-- SpringBoot 打包插件  web[war]应用打包为exec.jar 加入application主线程的jar包 -->
        <!-- Linux 启动 nohup java -jar xxx.exec.jar &  -->
        <!-- 后期计划结合Jenkins SonarQube 实现持续化集成环境 + 代码质量检测 by WanGuo -->
        <plugins>
        
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <executable>true</executable>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                        <configuration>
                            <classifier>exec</classifier>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
			
			<!-- docker-maven-plugin  -->
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>0.4.13</version>
                <configuration>
                    <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
                    <dockerDirectory>src/main/docker</dockerDirectory>
                    <resources>
                        <resource>
                            <targetPath>/</targetPath>
                            <directory>${project.build.directory}</directory>
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>
        </plugins>
    </build>

```

```properties
mvn clean

mvn install

//部署用内置 嵌入式tomcat  or 外置tomcat容器 后期根据业务优化

// 后期可以考虑 docker 部署
```


### 6.1方案一 基于Jenkins持续化集成方案

> 1.Jenkins 

搭建Jenkins 添加git相关插件 结合maven插件打包 用shell脚本实现 部署启停

```jshelllanguage
sh nohup java -jar xxx.exec.jar & //启动

ps -ef | grep java sed awk kill -9 ${} //停止
```

> 2.

### 6.2方案二 直接gitee 拉取打包部署
```jshelllanguage

git clone  https://gitee.com/isd_info/Demo_wubin

cd /**/

mvn clean 
mvn install
...
```


## 7.运维部署

> 1.Jdk 配置(1.8+)
```jshelllanguage
//不推荐 openJdk

//安装oracle JDK8+
wget ...
tar -vxf jdk1.8.14X.tar.gz

//配置环境变量
vim /etc/profile 

$JAVA_HOME=...
$JAVA_CLASSPATH=...

```

> 2.Nginx
```jshelllanguage
//编译源码安装 （推荐）


// rpm 编译包安装

//yum 
yum install nginx

```

```properties
//配置nginx
vim nginx.confg

//配置ssl 证书 为网页加https 

//openssl  生成证书 SpringBoot需要做处理  已经实现
```


> 3.Mysql
```properties
//yum 不推荐 已经不开源了mysql

//推荐安装5.6左右的开源版本

// 字符集utf-8 有坑  推荐utf-8umf

//数据库引擎采用innodb 支持事物和锁
```

> 4.Redis 等中间件 - 后期
```properties
//后期提示性能引入缓存


```

## 8.Task 
+ 测试
    - 测试环境
+ 部署
    - 部署环境

## 9.技术清单
+ Spring //提升到5
+ SpringBoot //后期对版本做提升 
+ SpringMVC
+ Mybatis //后期优化 MybatisPlus + 插件
+ Shiro //实现权限控制管理
+ Ehcahe //优化性能 + Shiro 登录管理

+ 后期可能会考虑到的
+ ElasticSearch //提供搜索 or 日志分析
+ Kafaka // 消息队列 做后台的消息通知等 // 回调
+ Dubbo  //服务拆分 先预留逻辑
+ Oauth2.0 Jwt 等  //鉴权相关

+ Vue 前后分离  or Node.js 做服务端 
   

## Contact
- Company : JasonInternational co.,LTD
- Website : https://wx.apps.ucaner.cn
