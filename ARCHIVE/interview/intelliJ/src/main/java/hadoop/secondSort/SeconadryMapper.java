package hadoop.secondSort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class SeconadryMapper extends Mapper<LongWritable, Text, Text ,NullWritable>{
    protected void map(LongWritable key, Text value, Context context) throws
            java.io.IOException ,InterruptedException {
        //仅仅是将 value 作为 key 输出
        context.write(value,NullWritable.get());
    };
}