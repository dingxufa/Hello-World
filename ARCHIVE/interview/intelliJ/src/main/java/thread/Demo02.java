package thread;

import groovy.transform.ToString;

/**
 * Created by ding on 2018/10/11.
 */
public class Demo02 {
    public static void main(String[] args) {

        LoggingWidget loggingWidget = new LoggingWidget();
        loggingWidget.doSomething();
    }

}

class Widget{
    public Widget() {
        System.out.println("widget init: "+this);
    }

    public synchronized void doSomething(){
        System.out.println("widget doSomething : " + this);
    }
}

class LoggingWidget extends Widget{
    public LoggingWidget() {
        System.out.println("LoggingWidget init; " + this);
    }

    public synchronized void doSomething(){
        System.out.println(toString()+"：calling doSomething");
        System.out.println("LoggingWidget doSomething : " + this);
        super.doSomething();
    }
}