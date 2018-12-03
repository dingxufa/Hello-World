package offer.chapter1;

/**
 * 枚举实现单列模式
 */
public class Singleton5 {
    /*private Singleton5(){}
    public static Singleton5 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private static enum Singleton{
        INSTANCE;
        private Singleton5 instance;

        private Singleton(){
            instance = new Singleton5();
        }
        public Singleton5 getInstance(){
            return instance;
        }
    }*/

    private Singleton5 (){}
    public static Singleton5 getInstance(){ return SingletonEnum.INSTANCE.getInstance();}
    private static enum SingletonEnum{
        INSTANCE;
        private Singleton5 instance;
        private SingletonEnum(){
            instance = new Singleton5();
        }
        public Singleton5 getInstance(){
            return instance;
        }
    }


}
