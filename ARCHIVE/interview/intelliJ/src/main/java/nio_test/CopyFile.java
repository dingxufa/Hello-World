package nio_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by ding on 2018/3/9.
 */
public class CopyFile {

    public static void test()throws Exception{
        FileInputStream fis = new FileInputStream(new File("d:/systemout.txt"));
        FileOutputStream fos = new FileOutputStream(new File("d:/scopy.txt"));

        FileChannel fcin = fis.getChannel();
        FileChannel fcout = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long start =System.currentTimeMillis();
        while(true){
            buffer.clear();
            int r = fcin.read(buffer);
            if(r == -1){
                break;
            }
            buffer.flip();
            fcout.write(buffer);
        }
        long end  = System.currentTimeMillis();
        System.out.println("sum="+(end-start));
    }

    public static void test2()throws Exception{
        FileInputStream fis = new FileInputStream(new File("d:/systemout.txt"));
        FileOutputStream fos = new FileOutputStream(new File("d:/scopy1.txt"));

        byte[] buffer = new byte[1024];
        int len = 0;
        long start =System.currentTimeMillis();
        while(-1!=(len=fis.read(buffer))){
            fos.write(buffer,0,len);
        }
        fos.flush();
        fos.close();
        fis.close();
        long end  = System.currentTimeMillis();
        System.out.println("sum="+(end-start));

    }

    public static void main(String[] args)throws Exception{
        test2();//71 68
        //test();//90



    }


}
