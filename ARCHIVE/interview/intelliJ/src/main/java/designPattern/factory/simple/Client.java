package designPattern.factory.simple;

/**
 * Created by ding on 2018/3/7.
 */
public class Client {
    public static void main(String[] args){
//        Car car1 = new Audi();
//        Car car2 = new Byd();
//
//        car1.run();;
//        car2.run();

        Car car1 = SimpleFactory.createAudi();
        Car car2 = SimpleFactory.createByd();

        car1.run();;
        car2.run();
    }
}
