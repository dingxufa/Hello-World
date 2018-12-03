package designPattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ding on 2018/3/7.
 */
public class Test {

    public static void test(){
        try{

            System.out.println(Singleton6.getInstance().hashCode());


            Class<?> clazz = Class.forName("designPattern.singleton.Singleton6");
            Constructor<?> constructor = clazz.getConstructor();
            Object obj = constructor.newInstance();
            Field f = clazz.getDeclaredField("instance");
            System.out.println(f);
            System.out.println(f.hashCode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void test2()throws  Exception{
        long start = System.currentTimeMillis();
        int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10000;j++){
                        Singleton6.getInstance();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        long end  = System.currentTimeMillis();
        System.out.println("总耗时："+(end-start));
    }

    public static void main(String[] args)throws  Exception{
       test2();

    }


}
