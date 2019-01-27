

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



maven配置

```xml
 <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.10.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.6</version>
        </dependency>


        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
```







```java
import com.google.common.base.Function;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ding on 2019/1/27.
 */
public class SeleniumAPI {
    ChromeDriver driver = null;

    @Before
    public void init(){
 	 		System.setProperty("webdriver.chrome.driver","D:/software/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/jekins");
    }

    public void login(){
        driver.findElement(By.id("j_username")).sendKeys("");
        driver.findElement(By.name("j_password")).sendKeys("");;
        driver.findElement(By.name("j_password")).submit();//只要是表单中的任意一个元素都可以
//        driver.findElement(By.id("yui-gen1-button")).click();
    }

    @Test
    public void test(){

        //= driver.navigate().to("http://localhost:8080/jekins");

        //设置浏览器大小
        driver.manage().window().maximize();

        //设置页面超时时间
        driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.MILLISECONDS);

/*
        WebElement element = driver.findElement(By.className("task"));//获取第一个元素，找不到抛异常
        List<WebElement> elements = driver.findElements(By.className("task"));//返回一个数组，找不到返回null
        Iterator<WebElement> iterator = elements.iterator();
        while(iterator.hasNext()){
            iterator.next().click();
        }
*/
        driver.findElement(By.id("search-box")).clear();
        driver.findElement(By.xpath("//b[text()='登录']")).clear();
        String text = driver.findElement(By.cssSelector("#tasks div a:nth-child(2)")).getText();
        assertEquals("登录失败了","新Job",text);



        driver.navigate().forward();//前进

        driver.navigate().back();// 后退
        driver.close();
       // =  driver.quit();  关闭整个浏览器对象
    }


    public void jsEngins(){
        //login
        driver.findElement(By.cssSelector("#login-field span a:nth-child(1)")).click();

        JavascriptExecutor js = this.driver;
        WebElement username = (WebElement)js.executeScript("document.getElementById(\"j_username\")");

        //input username
        username.clear();
        username.sendKeys("admin");

        //截屏操作 screenshot
        TakesScreenshot take = this.driver;
        File file = take.getScreenshotAs(OutputType.FILE);
        try {
            FileOutputStream out = new FileOutputStream("d:/test.jpg");
            FileUtils.copyFile(file,out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //元素双击
        Actions actions = new Actions(driver);
        WebElement message = driver.findElement(By.id("message"));
        actions.doubleClick(message);

        //元素拖拽
        WebElement src = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(src,target).perform();

        //使用select类
        WebElement make = driver.findElement(By.id("make"));
        Select select = new Select(make);
        List<WebElement> options = select.getOptions();
        Iterator<WebElement> iterator = options.iterator();

        //<option value="text value">text vlaue</option>
        select.selectByIndex(0);
        select.selectByValue("text value");
        select.selectByVisibleText("text vlaue");

        select.deselectAll();// 去除所有选项

        //处理单选按钮 复选框
        WebElement radio = driver.findElement(By.id("radio"));
        radio.click();// 选择
        radio.isSelected();
    }

    @Test
    public void testWait(){
        // 隐式等待，每个动作都等待指定时间
        driver.manage().timeouts().implicitlyWait(1000,TimeUnit.MILLISECONDS);

        //显示等待,在指定时间内不断重试查找元素，超时未找到包timeout异常
        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("doLogin")));

        //可以自己实现方法
        WebElement el = wait.until(new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("doLogin"));
            }
        });

        
        
        
        FluentWait<WebDriver> wait1 = new FluentWait<WebDriver>(driver);
        // 10s超时 每100ms重试
        wait1.pollingEvery(100,TimeUnit.MILLISECONDS).withTimeout(10,TimeUnit.SECONDS);
        wait1.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("doLogin"));
            }
        });

        driver.findElement(By.id("doLogin")).click();
        driver.findElement(By.id("register"));
    }


   /* public boolean isPresence(WebDriver driver,long timeout){
        boolean flag = true;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.id("doLogin"));
                }
            });
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }*/

    public boolean isPresence(WebDriver driver,long timeout){
        boolean flag = true;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public  Boolean apply(WebDriver driver) {  // 泛型只能是包装类
                    return driver.findElement(By.id("doLogin")).isDisplayed();
                }
            });
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    //处理js弹出窗口
    public void testAlert(){
        WebElement alert = driver.findElement(By.id("alert"));
        alert.click();
        Alert temp = driver.switchTo().alert(); // alert promp

        String context = temp.getText();
        assertTrue(context.equals("xxx"));

        temp.sendKeys("input text ");
        temp.accept();
        temp.dismiss();
    }

    //如果处理frame ifame
    public void testFrame(){
        WebDriver wd = driver.switchTo().frame(1);

        WebElement second = driver.findElement(By.cssSelector("frameset frame:nth-child(2)"));
        driver.switchTo().frame(second);

        driver.switchTo().defaultContent();//返回到最开始最外层的frame

    }


    public void testWindow(){
        String paernt = driver.getWindowHandle();
        driver.findElement(By.id("button")).click();

        //点击弹出另一个window
        Set<String> all = driver.getWindowHandles();
        Iterator<String> iterator = all.iterator();
        String temp;
        while (iterator.hasNext()){
            temp = iterator.next();
            if (!temp.equals(paernt)){
                driver.switchTo().window(temp);// 切换到弹出的窗口
            }
        }
    }
}

```

