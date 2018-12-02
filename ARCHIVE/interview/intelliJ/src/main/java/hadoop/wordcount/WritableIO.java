package hadoop.wordcount;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
public class WritableIO {
    public static void main(String[] args) throws IOException {
        IntWritable writable = new IntWritable();
        writable.set(1331321);
        //将 writable 序列化为 byte 数组
        byte[] bytes = serialize(writable);
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
        IntWritable writable2 = new IntWritable();
        //将 byte 数组反序列化为 IntWritable 对象
        deserialize(writable2, bytes);
        System.out.println(writable2.get());
    }
    //序列化：将 Writable 对象序列化为 byte 数组
    public static byte[] serialize(Writable writable) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        writable.write(dataOut);
        dataOut.close();
        return out.toByteArray();
    }
    //反序列化：将 byte 数组反序列化为 Writable 对象
    public static byte[] deserialize(Writable writable,byte[] bytes) throws IOException{
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        DataInputStream dataIn = new DataInputStream(in);
        writable.readFields(dataIn);
        dataIn.close();
        return bytes;
    }
}
