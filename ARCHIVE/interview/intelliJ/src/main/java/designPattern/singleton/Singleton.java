package designPattern.singleton;

/**
 * 饿汉式   线程安全 效率高 不能延时加载
 */
public class Singleton {
    //类初始化时立即加载这个对象（没有延时加载的优势） 由于类加载时，天然的是线程安全的
    private static Singleton instance = new Singleton();

    private Singleton(){}

    //方法没有同步，调用效率高
    public static Singleton getInstance(){
        return instance;
    }
}
