package hadoop.wordcount;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class Driver {
    public static void main(String[] args) throws IOException, ClassNotFoundException,
            InterruptedException {

        Configuration conf = new Configuration();
        Job job = new Job(conf, "example_main");
        //指定了 main 函数所在的类
        job.setJarByClass(Driver.class);
        //指定 Mapper 类
        //job.setMapperClass(MyMapper.class);
        //指定 Reducer 类
        //job.setReducerClass(MyReducer.class);
        //设置 reduce()函数输出 key 的类
        job.setOutputKeyClass(Text.class);
        //设置 reduce()函数输出 value 的类
        job.setOutputValueClass(IntWritable.class);
        //指定输入路径
        FileInputFormat.addInputPath(job, new Path(args[0]));
        //指定输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //提交任务
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
