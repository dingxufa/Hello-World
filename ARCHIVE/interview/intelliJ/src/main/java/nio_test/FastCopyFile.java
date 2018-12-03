package nio_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by ding on 2018/3/9.
 */
public class FastCopyFile {
    public static void main(String[] args)throws Exception{
        FileInputStream fis = new FileInputStream(new File("d:/systemout.txt"));
        FileOutputStream fos = new FileOutputStream(new File("d:/scopy.txt"));

        FileChannel fcin = fis.getChannel();
        FileChannel fcout = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        long start =System.currentTimeMillis();
        while(true){
            buffer.clear();
            int r = fcin.read(buffer);
            if(r==-1){break;}
            buffer.flip();
            fcout.write(buffer);
        }
        long end  = System.currentTimeMillis();
        System.out.println("sum="+(end-start));

    }
}
