package offer.chapter1;

/**
 * 双重检测锁
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
            }
        }
        return instance;
    }
}
