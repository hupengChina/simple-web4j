simple-web4j
======= 
    'simple-web4j'一个基于java语言的简易Web框架。
## 起源
>    平时一直依赖spring相关的组件进行开发，慢慢的有了很多的疑问。查了很多资料，最后也学着别人打开了源码...，在看源码的过程中萌生了一个想法，自己是不是也跟着写个类似的简单点框架，加深理解，于是便有了'simple-web4j'。

## 实现
>进度
> * 不依赖Servlet采用netty封装http作为web服务器
> * 单例bean容器


## 使用
>依赖引用
```xml
<dependency>
    <groupId>org.hupeng</groupId>
    <artifactId>simple-web4j</artifactId>
    <version>${simpleweb4j.version}</version>
</dependency>
```
>启动
```Java
public class DemoApplication {

    public static void main(String[] args) {
        SimpleWebApplication.run(DemoApplication.class,args);
    }
}
```
>默认扫描启动类所在package及以下的包
```Java
@Controller
public class DemoController {
    
    @Autowired
    private DemoService demoService;

    @RequestMapping( value = "/hello")
    public Object hello(){
        return demoService.hello();
    }
}
```

> 实现参考了以下项目：
>  *  https://github.com/spring-projects/spring-framework
>  *  https://github.com/spring-projects/spring-boot
>  *  https://github.com/b3log/latke
>  *  https://gitee.com/huangyong/smart-framework