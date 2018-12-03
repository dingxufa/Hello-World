package offer.chapter1;

/**
 * 懒汉式
 */
public class Singleton2 {
    /*public static Singleton2 instance=null;

    private Singleton2() {}

    public static Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return  instance;
    }*/

    //类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static Singleton2 instance;

    private Singleton2() {}

    //方法同步  调用效率低
    public static synchronized Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return  instance;
    }
}
