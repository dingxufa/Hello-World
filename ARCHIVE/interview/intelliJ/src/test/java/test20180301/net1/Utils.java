package test20180301.net1;

import java.io.Closeable;

/**
 * Created by ding on 2018/3/1.
 */
public class Utils {
    public static<T extends Closeable> void close(T...clos){
        for(Closeable t: clos){
            if (t != null) {
                try{
                    t.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
