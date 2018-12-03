package designPattern.prototype.serial;

import designPattern.prototype.shallowCopy.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 以序列化的方式实现深复制
 */
public class Client {
    public static void main(String[] args)throws Exception{
        Date date = new Date(12312321331L);
        Sheep s1 = new Sheep("A",date);
        System.out.println(s1);
        System.out.println(s1.getName());
        System.out.println(s1.getBirth());

        //使用序列化和反序列化实现深复制  反序列化时会把属性等也反序列化
        //通过ObjectOutputStream输出流，将对象序列化后输出到ByteArrayOutputStream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(s1);
        byte[] bytes = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Sheep s2 = (Sheep) ois.readObject();

        System.out.println("==================");
        System.out.println("修改原型对象的属性值");
        date.setTime(23432432423L);
        System.out.println(s1.getBirth());
        s2.setName("B");
        System.out.println("A="+s1.getName()+" B="+s2.getName()+"");
        System.out.println(s2.getBirth());

    }
}
