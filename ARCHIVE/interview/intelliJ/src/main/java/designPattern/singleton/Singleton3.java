package designPattern.singleton;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 双重检测锁
 * 有序性：是因为 instance = new Singleton(); 不是原子操作。编译器存在指令重排，从而存在线程1 创建实例后（初始化未完成），线程2 判断对象不为空，但实际对象扔为空，造成错误。

 主要是禁止重排序，初始化一个实例（SomeType st = new SomeType()）在java字节码中会有4个步骤，
 1. 申请内存空间，
 2. 初始化默认值（区别于构造器方法的初始化），
 3. 执行构造器方法
 4. 连接引用和实例。

 这4个步骤后两个有可能会重排序，1234  1243都有可能，造成未初始化完全的对象发布。
 volatile可以禁止指令重排序，从而避免这个问题
 */
public class Singleton3 {
    public static volatile Singleton3 instance = null;

    private Singleton3() {}

    public static Singleton3 getInstance(){
        if(instance == null){
            synchronized(Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
             //  return instance;
            }
        }
        return instance;
    }

    public static void main(String[] args)throws  Exception{
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("d:/systemout.txt")))));
        for(int i=0;i<100000;i++){
         //   Thread.sleep(1);
            System.out.println(Singleton3.getInstance());
        }
        System.setOut(oldOut);
    }
}
