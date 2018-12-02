package hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
public class MyPartitioner extends Partitioner<Text, IntWritable>{
    @Override
    public int getPartition(Text key, IntWritable value, int numReduceTasks) {
        return ( new Boolean(value.get()>10000).hashCode()  & Integer.MAX_VALUE  ) % numReduceTasks;
    }
}