package designPattern.factory.simple;

/**
 * 简单工厂(静态工厂)
 */
public class SimpleFactory {

    public static Car createAudi(){
        return new Audi();
    }

    public static Car createByd(){
        return new Byd();
    }


}
