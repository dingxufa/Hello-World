package nio_test;

import java.nio.ByteBuffer;

/**
 * Created by ding on 2018/3/9.
 */
public class CreateArrayBuffer {
    public static void main(String[] args)throws Exception{
        byte[] array = new byte[1024];
        ByteBuffer buffer = ByteBuffer.wrap(array);

        buffer.put((byte )'a');
        buffer.put( (byte)'b' );
        buffer.put( (byte)'c' );
        buffer.flip();
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
    }
}
