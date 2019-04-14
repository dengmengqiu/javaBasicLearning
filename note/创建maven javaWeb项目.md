# 创建一个简单maven java web项目

环境：IDEA社区版  Tomcat

<span style="font-size:13px;font-family:FangSong">说明：采用Eclipse开发时 其项目结构已经完好 有些配置是不需要的</span>

## 创建项目
    

1.在IDEA中已经整合了Maven所以无需重新单独下载  

  Create New Project -> New project -> maven -> next -> 输入GroupID(book.demo)、ArtifactId(chapter1)、Version(1.0.0) -> Project Name(chapter1) -> finiish

    项目目录
       src
        |
        |-main 主要程序代码
        |   |
        |   |-java
        |   |    
        |   |-resources  项目的配置文件
        |
        |-test 测试代码
        |   |
        |   |-java   
        |
        |-pom.XML JavaWeb的Meaven依赖
        Mmeaven依赖三坐标：groupID(组织项目名)、artifacId(模块名)、version(版本号)

   在Eclipse中新建项目后， 项目建立后，jsp文件有错“The superclass "javax.servlet.http.HttpServlet" was not found on the Java Build Path”
     
    右击项目->build path->config build path->add library->server runtime->添加一个 tomcat服务器


## 项目配置 
1. 使用Servlet3.0框架可以省略web.xml的配置，只需要通过注解的方来配置即可

2. 查看pom.XML文件
```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>book.demo</groupId>
    <artifactId>chapter1</artifactId>
    <version>1.0-SNAPSHOT</version>
</project>
```

编码格式(必需)
```java
  <!-- 编码格式 -->
 <properties>
    <rpoject.build.sourceEncodeing>UTF-8</rpoject.build.sourceEncodeing>
</properties>
```

JDX版本配置，此处采用JDK1.6版本(必需)
```java
<build>
    <!--使用的插件列表 。-->  
    <plugins>
        <!-- Complie -->
        <!-- 统一源代码与变异输出的JDK版本 -->
        <plugin>
            <groupId>org.apach.maven.plugins</groupId>
            <artifactId>maven-complier-plugin</artifactId>
            <version>3.3</version>
            <configuration>
                <source></source>
                <target></target>
            </configuration>
        </plugin>
        <!-- 打包时跳过单元测试 非必需-->
        <plugin>
            <groupId>org.apach.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.18.1</version>
            <configuration>
                <skipTesets>true</skipTesets>
            </configuration>
        </plugin>
    </plugins>	
  </build>
```


## 搭建web项目框架

### 转为java Web项目目录
将项目目录调整为：
    
    src
     |
     |-main 主要程序代码
     |    |
     |    |-java
     |    |    
     |    |-resources  
     |    |
     |    |-webapp
     |        |
     |        |-WEB=INF
     |            |
     |            |-WEB.XML
     |
     |
     |-test 测试代码
     |   |
     |   |-java   
     |
     |-pom.XML 


### 添加java web 的项目依赖
 说明：
 1)Maven 依赖的"三坐标"：groupId(组织项目名)、artifacId(模块名)、version(版本号)必须提供
 2)如果某些依赖只需要参与编译、而无需参与打包，可将其scope设置为provided
 3)运行时需要，无需参与编译，将scope设置为runtime

```java
 <!-- 添加一些java web 所需的servlet、sjp、jstl依赖 -->
    <!-- Servlet -->
  	<dependency>
  		<groupId>javax.servlet</groupId>
 		<artifactId>javax.servlet-api</artifactId>
 		<version>3.1.0</version>
 		<scope>provided</scope>
  	</dependency>
  	<!-- jsp -->
  	<dependency>
  	<groupId>javax.servlet.jsp</groupId>
 		<artifactId>jsp-api</artifactId>
 		<version>2.2</version>
 		<scope>provided</scope>
  	</dependency>
  	<!-- JSTL -->
  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>jstl</artifactId>
  		<version>1.2</version>
  		<scope>runtime</scope>
  	</dependency>

```

```java
<!-- 插件列表 -->
<plugins>
    <!-- Complie -->
    <!-- 统一源代码与变异输出的JDK版本 -->
    <plugin>
        <groupId>org.apach.maven.plugins</groupId>
        <artifactId>maven-complier-plugin</artifactId>
        <version>3.3</version>
        <configuration>
            <source></source>
            <target></target>
        </configuration>
    </plugin>
    <!-- 打包时跳过单元测试 非必需-->
    <plugin>
        <groupId>org.apach.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.18.1</version>
        <configuration>
            <skipTesets>true</skipTesets>
        </configuration>
    </plugin>
</plugins>	
```

### Tomcat 服务器配置

1、首先在官网下载好Tomcat并配置环境后 检验是否安装成功

2、项目配置 settings 
   1）按照网上的教程 在add Configration没有找到Tomcat Server  也没有Application Server这一项 
   2）自己安装一个插件Smart Tomcat 下载好配置好Tomcat安装路径即可
   3）Add Configration -> + -> 选择服务器  -> apply ->  点击add Configration旁边的小锤锤

# 创建一个简单的web应用

 添加包 鼠标单击java目录 -> Alt + Insert 选择Package 添加包名（book.demo.chapter1）

### 编写Servlet类
HelloServlet
```java
package book.demo.chapter1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloSerclet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dataFormat.format(new Date());
        req.setAttribute("currentTime", currentTime);
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
    }
}
```


## 编写jsp页面

