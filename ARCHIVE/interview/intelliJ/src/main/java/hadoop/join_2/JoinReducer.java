package hadoop.join_2;


import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @ProjectName JoinDemo
 * @PackageName com.buaa
 * @ClassName JoinReducer
 * @Description TODO
 * @Author 刘吉超
 * @Date 2016-05-24 22:54:24
 */
public class JoinReducer extends Reducer< TextPair,Text,Text,Text>{
    protected void reduce(TextPair key, Iterable<Text> values,Context context) throws IOException,InterruptedException{
        Iterator<Text> iter = values.iterator();
        // 气象站名称
        Text stationName = new Text(iter.next());

        while(iter.hasNext()){
            // 天气记录的每条数据
            Text record = iter.next();

            Text outValue = new Text(stationName.toString() + "\t" + record.toString());

            context.write(key.getFirst(),outValue);
        }
    }
}