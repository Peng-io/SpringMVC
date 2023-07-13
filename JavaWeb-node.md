# java web 学习 配置地狱

## web xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
</web-app>
```

## 路由配置

```xml

<servlet>
    <servlet-name>hello</servlet-name>
    <servlet-class>com.demo.helloServlet</servlet-class>
</servlet>
<servlet-mapping>
<servlet-name>hello</servlet-name>
<url-pattern>/hello</url-pattern>
</servlet-mapping>
```

## 过滤器

```java

public class helloFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("CharacterEncodingFilter执行前....");
        chain.doFilter(request, response); //让我们的请求继续走，如果不写，程序到这里就被拦截停止！
        System.out.println("CharacterEncodingFilter执行后....");

    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
```

### 在web xml里配置

```xml

<filter>
    <filter-name>helloFilter</filter-name>
    <filter-class>com.demo.helloFilter</filter-class>
</filter>
<filter-mapping>
<filter-name>helloFilter</filter-name>
<!--        走hello/下的所有请求都会被过滤-->
<url-pattern>/hello/*</url-pattern>
</filter-mapping>
```

## spring MVC

### springmvc-servlet.xml 配置项

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 自动扫描包，让指定包下的注解生效,由IOC容器统一管理 -->
    <context:component-scan base-package=""/>
    <!-- 让Spring MVC不处理静态资源 -->
    <mvc:default-servlet-handler/>
    <!--
    支持mvc注解驱动
        在spring中一般采用@RequestMapping注解来完成映射关系
        要想使@RequestMapping注解生效
        必须向上下文中注册DefaultAnnotationHandlerMapping
        和一个AnnotationMethodHandlerAdapter实例
        这两个实例分别在类级别和方法级别处理。
        而annotation-driven配置帮助我们自动完成上述两个实例的注入。
     -->
    <mvc:annotation-driven/>

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"/>
    </bean>

</beans>
```

### web.xml配置

```xml
    <!--1.注册DispatcherServlet-->
<servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!--关联一个springmvc的配置文件:【servlet-name】-servlet.xml-->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc-servlet.xml</param-value>
    </init-param>
    <!--启动级别-1-->
    <load-on-startup>1</load-on-startup>
</servlet>

        <!--所有请求都会被springmvc拦截 -->
<servlet-mapping>
<servlet-name>springmvc</servlet-name>
<url-pattern>/</url-pattern>
</servlet-mapping>
```

### 编写controller类

```java

@Controller
public class HelloController {
    //真实访问地址 : 项目名/hello
    @RequestMapping("/hello")
    public String hello(Model model) {
        //向模型中添加属性msg与值，可以在JSP页面中取出并渲染
        model.addAttribute("msg", "注解实现");
        //web-inf/jsp/hello.jsp
        return "hello";
    }
}
```

```text
@Controller注解是为了让Spring IOC容器初始化时自动扫描到该Controller类；
@RequestMapping是为了映射请求路径，这里因为类与方法上都有映射所以访问时应该是/；
方法返回的结果是视图的名称hello，该名称不是完整页面路径，最终会经过视图解析器解析为完整页面路径并跳转。
映射到这个路径下的jsp文件 /WEB-INF/jsp/hello.jsp
```

```text
Spring MVC默认是通过转发的形式响应JSP，可以手动进行修改
设置重定向的时候不能写逻辑视图，必须写明资源的物理路径，比如"rediect:/index.jsp"
return "redirect:/index.jsp";
```

## springMVC乱码过滤

在web xml 中配置

```xml

<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </init-param>
</filter>
<filter-mapping>
<filter-name>encoding</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping>
```

```text
WebMvcConfigurer是一个接口，用于配置全局的SpringMVC的相关属性，采用JAVABEAN的方式来代替传统的XML配置文件，
提供了跨域设置、静态资源处理器、类型转化器、自定义拦截器、页面跳转等能力。
WebMvcConfigurationSupport是webmvc的配置类，如果在springboot项目中，有配置类继承了WebMvcConfigurationSupport，
那么webmvc的自动配置类WebMvcAutoConfiguration就会失效。
官方推荐直接实现WebMvcConfigurer或者直接继承WebMvcConfigurationSupport
方式一实现WebMvcConfigurer接口（推荐），方式二继承WebMvcConfigurationSupport类。 
```
