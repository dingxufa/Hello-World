package hadoop.join_2;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @ProjectName JoinDemo
 * @PackageName com.buaa
 * @ClassName JoinRecordMapper
 * @Description TODO
 * @Author 刘吉超
 * @Date 2016-05-24 22:56:55
 */
public class JoinRecordMapper extends Mapper<LongWritable,Text,TextPair,Text>{
    protected void map(LongWritable key,Text value,Context context) throws IOException,InterruptedException{
        String line = value.toString();
        // 解析天气记录数据
        String[] arr = line.split("\\s+",2);

        if(arr.length == 2){
            //key=气象站id  value=天气记录数据
            context.write(new TextPair(arr[0],"1"),new Text(arr[1]));
        }
    }
}