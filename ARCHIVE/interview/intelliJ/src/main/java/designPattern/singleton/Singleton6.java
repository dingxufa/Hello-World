package designPattern.singleton;

import java.io.Serializable;

/**
 * 懒汉式单例模式(如何防止反射和反序列化漏洞)
 */
public class Singleton6 implements Serializable{

    private static Singleton6 instance;
    private Singleton6(){
        if(instance != null) {
            throw new RuntimeException();  //防止通过反射创建实例
        }
    }
    public static synchronized Singleton6 getInstance(){
        if(null == instance){
            instance = new Singleton6();
        }
        return instance;
    }

    //反序列化时，如果定义了readResolve()则直接返回此方法指定的对象。而不需要单独再创建新对象！
    private Object readResolve(){
        return  instance;
    }


}
