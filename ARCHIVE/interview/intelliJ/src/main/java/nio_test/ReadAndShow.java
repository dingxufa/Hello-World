package nio_test;// $Id$

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadAndShow
{
  static public void main( String args[] ) throws Exception {
    FileInputStream fin = new FileInputStream( "d:/scopy1.txt" );
    FileChannel fc = fin.getChannel();

    //注意这里并没有读取整个文件，只是读取了前1024个字节
    ByteBuffer buffer = ByteBuffer.allocate( 1024 );

    fc.read( buffer );

    buffer.flip();

    int i=0;
    while (buffer.remaining()>0) {
      byte b = buffer.get();
      //System.out.print( "Character "+i+": "+((char)b)+" " );
        System.out.print((char)b);
      i++;
    }

    fin.close();
  }
}
