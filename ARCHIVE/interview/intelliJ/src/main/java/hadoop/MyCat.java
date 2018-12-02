package hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;
/**
 * Created by ding on 2018/4/22.
 */
public class MyCat {
    static{
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    public static void main(String[] args) throws MalformedURLException, IOException {
        InputStream in = null;
        try {
            in = new URL(args[0]).openStream();//hdfs://master:9000/user/hadoop/test
            IOUtils.copyBytes(in, System.out, 4096,false);
        } finally{
            IOUtils.closeStream(in);
        }
    }
}
