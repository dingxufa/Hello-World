package designPattern.prototype.shallowCopy;

import java.util.Date;

/**
 * Created by ding on 2018/3/7.
 */
public class Client {
    public static void main(String[] args)throws Exception{

        Date date = new Date(12312321331L);
        Sheep s1 = new Sheep("A",date);
        System.out.println(s1);
        System.out.println(s1.getName());
        System.out.println(s1.getBirth());

        date.setTime(23432432423L);

        System.out.println(s1.getBirth());
        System.out.println("======");
        Sheep s2 = (Sheep)s1.clone();
        System.out.println(s2.getName());
        s2.setName("B");
        System.out.println("A="+s1.getName()+" B="+s2.getName()+"");
        System.out.println(s2.getBirth());

    }
}
