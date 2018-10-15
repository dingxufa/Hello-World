ServletContext   ActionContext   ServletActionContext



**ServletContext**

 从`ServletContext`的包（ `javax.servlet.ServletContext` ）我们可以知道它是标准的JavaEE  WebApplication类库。

 ServletContext提供了标准的Servlet运行时环境。 实际上是servlet与web容器进行通信的一些方法。

 

```java
public interface ServletContext { 
// Returns the URL prefix for the ServletContext. 
public String getServletContextName(); 
//Returns the context-path for the web-application. 
public String getContextPath(); 
//Returns the ServletContext for the uri. public 
ServletContext getContext(String uri);
//Returns the real file path for the given uri. 
public String getRealPath(String uri); 
public RequestDispatcher getNamedDispatcher(String servletName); 
public RequestDispatcher getRequestDispatcher(String uri);

```

ServletContext包含在ServletConfig中，ServletConfig通常从servlet或filter的`init()`方法读取：

```java
servletConfig.getServletContext() filterConfig.getServletContext()
```

**ActionContext**

 来自Struts，但是起初来自Struts1和Struts2，它们是不同的。

 从Struts1： 
一个`servlet` （servlet  org.apache.struts.action.ActionServlet）处理所有的`*.do`动作。

 从Struts2： 
`filter` （org.apache.struts2.dispatcher.FilterDispatcher）处理所有请求。

 因为struts1属于servlet范围。 struts1动作本质上是servlet。 
struts2动作是普通的Java bean，出了servlet限制。 
在strtus2动作出来之后，ActionContext构成了丢失的WEB环境。

 ActionContext主要功能：

- 提供WEB上下文。
- 解决线程安全问题。
- 解决与其他Framework不兼容的问题（如：webLogic））

 **ServletActionContext**

 正如你所说，ServletActionContext是ActionContext的子类。 它的功能是从ActionContext开始，封装方法，使其更加简单直观。

```java
public class ServletActionContext extends ActionContext implements StrutsStatics { //HTTP servlet request 
    public static void setRequest(HttpServletRequest request) { 		      	       
        ActionContext.getContext().put(HTTP_REQUEST, request); } 
    public static HttpServletRequest getRequest() { 
        return (HttpServletRequest) ActionContext.getContext().get(HTTP_REQUEST); }
//HTTP servlet response 
    public static void setResponse(HttpServletResponse response) { 
        ActionContext.getContext().put(HTTP_RESPONSE, response); } 
    public static HttpServletResponse getResponse() { 
        return (HttpServletResponse) ActionContext.getContext().get(HTTP_RESPONSE); } //servlet context. 
    public static ServletContext getServletContext() { 
        return (ServletContext) ActionContext.getContext().get(SERVLET_CONTEXT); } 
    public static void setServletContext(ServletContext servletContext) { 
        ActionContext.getContext().put(SERVLET_CONTEXT, servletContext); }

```

从上面我们可以知道ServletActionContext扩展了ActionContext。





# 从JVM角度看为什么子类不能重写父类静态方法



https://blog.csdn.net/TyroneRenekton/article/details/68923475



我们要解释的是什么问题呢？

public class A extends B{
    public static void f() {
        System.out.println("com.sdkd.A.f()");
    }

    public static void main(String[] args) {
        A.f();
    }
}

class B {
    public static void f() {
        System.out.println("com.sdkd.B.f()");
    }
} 
/**
com.sdkd.A.f()
*/
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
我在网上搜这个问题的时候答案多数是这样的

子类不能重写实例方法
父类的静态方法被绑定了[写死了orz…],不能重写
好了，开始我们的分析

调用A.f()发生了什么呢？ 
注意这里是“调用”而不是“执行”，方法调用就是确定被调用方法的版本[即调用哪一个方法]。 
在JAVA虚拟机中提供了5中方法调用字节码指令，如下

invokestatic， 调用静态方法
invokespecial， 调用实例构造器init方法、私有方法和父类方法
invokevirtual， 调用所有的虚方法[public]
invokeinterface , 调用接口方法，会在运行时再确定一个实现此接口的对象
invokedynamic， 先在运行时动态解析出调用点限定符所引用的方法，然后再执行该方法，在此之前的4条调用指令，分派逻辑是固化在Java虚拟机内部的，而invokedynamic指令是由引导方法决定的.
先普及一点知识点： 
符号引用：符号引用是一组符号来描述所引用的目标，符号可以是任何形式的字面量，只要使用时能无歧义地定位到目标即可。符号引用与虚拟机内部的内存布局无关，引用的目标并不一定加载到内存中。各种虚拟机实现的内存布局可以各不相同，但是他们能接受的符号引用是相同的，因为符号引用的字面量形式明确定义在虚拟机规范的Class文件规范中 
比如

public class C {
    public void f(){

    }
}
1
2
3
4
5
使用javap -v C.class，我们看该class文件中的常量池中f（）的符号引用——#11 

直接引用：直接引用可以是直接指向目标的指针、相对偏移量或是一个能间接定位到目标的句柄。直接引用是和虚拟机内存布局相关的，同一个符号引用在不同虚拟机实例上翻译出来的直接引用一般不会相同。如果有了直接引用，那引用的目标必定已经在内存中存在 
比如

public class C {
    public void f(){

    }
}
1
2
3
4
5
直接引用是我们是看不到的，但是如果我们用偏移量来表示f()，它可以是0x00000045[偏移量].

首先我们知道普通的public方法是能够被重写的，它在class方法中的字节码指令是invokevirtual，有了指令也要有参数——方法的入口地址，类似于这种invokevirtual address。该入口地址是要动态解析的，也就是将方法引用解析为直接引用，类似于这种invokevirtual 0xffffff 
举例

public class C {
    public void f(){

    }
}
class D extends C {
    public void f() {

    }
    public static void main(String[] args) {
        C c = new D();
        c.f();
    }
}
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
运行时执行这里的invokevirtual指令时，将符号引用f解析成一个具体的直接引用0xfffffff

解析过程：

类方法和接口方法引用的常量类型定义是分开的，如果在类的方法表中发现class_index 索引的C是个接口，那就直接抛出java.lang.IncompatibleClassChangeError异常
如果通过了第一步，在类中查找是否有简单名称和描述符都与目标匹配的方法，如果有则返回这个方法的直接引用，查找结束
否则，在类的父类中递归查找是否有简单名称和描述符都与目标匹配的方法，如果有则返回这个方法的直接引用，查找结束
否则，在类实现的接口列表及父接口中递归查找是否有简单名称和描述符都与目标匹配的方法，如果有则返回这个方法的直接引用，查找结束
否则，宣告方法查找失败，抛出java.lang.NoSuchMethodError
注意：这里的解析过程是在运行时

这个时候说“为什么子类不能重写父类静态方法”——调用A.f()这个问题就十分简单了，因为静态方法对应的invokestatic，它所需要的符号引用在类加载阶段符号引用解析成为直接引用了。也就是说它在运行的时候是这样的invokestatic 0xfffff，根本就不会有上面提到的解析过程——随便你怎么重写，跟我一点关系都没没有。

真相虽然简单，但过程却是非常丰富的，阅读愉快~
--------------------- 
作者：陷于才华 
来源：CSDN 
原文：https://blog.csdn.net/TyroneRenekton/article/details/68923475?utm_source=copy 
版权声明：本文为博主原创文章，转载请附上博文链接！