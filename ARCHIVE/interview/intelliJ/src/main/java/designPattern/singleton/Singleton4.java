package designPattern.singleton;

/**
 * 静态内部类  线程安全  效率高  能延时加载
 */
public class Singleton4 {
    static {
        System.out.println("222");
    }

    private static class SingletonClassInstance {
        static {
            System.out.println("111");
        }
        private static final Singleton4 instance = new Singleton4();
    }

    private Singleton4(){
    }

    //方法没有同步，调用效率高！
    public static Singleton4 getInstance(){
        return SingletonClassInstance.instance;
    }

    public static void main(String[] args){
        Singleton4 singleton4 = new Singleton4();
        System.out.println("==============");
        Singleton4.getInstance();
//        222
//        ==============
//        111
    }
}
