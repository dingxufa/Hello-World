package hadoop.join_2;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @ProjectName JoinDemo
 * @PackageName com.buaa
 * @ClassName JoinStationMapper
 * @Description TODO
 * @Author 刘吉超
 * @Date 2016-05-24 22:55:42
 */
public class JoinStationMapper extends Mapper<LongWritable, Text, TextPair, Text> {
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        // 解析气象站数据
        String[] arr = line.split("\\s+");

        if (arr.length == 2) {// 满足这种数据格式
            // key=气象站id value=气象站名称
            context.write(new TextPair(arr[0], "0"), new Text(arr[1]));
        }
    }
}