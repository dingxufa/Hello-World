package nio_test;

import java.nio.ByteBuffer;

/**
 * Created by ding on 2018/3/9.
 */
public class CreateBuffer {
    public static void main(String[] args)throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put((byte )'a');
        buffer.put( (byte)'b' );
        buffer.put( (byte)'c' );
        buffer.flip();
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
//        System.out.println((char)buffer.get());
//        System.out.println((char)buffer.get());
//        System.out.println((char)buffer.get());
    }
}
