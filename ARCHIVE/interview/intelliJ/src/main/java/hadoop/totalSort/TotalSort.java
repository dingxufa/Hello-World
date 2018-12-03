package hadoop.totalSort;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler.RandomSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;
public class TotalSort {
    public static void main(String[] args) throws Exception{
        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);
//分区文件路径
        Path partitionFile = new Path(args[2]);
        int reduceNumber = Integer.parseInt(args[3]);
//RandomSampler 第一个参数表示会被选中的概率，第二个参数是一个选取的样本数数，第三个参数是最大读取 InputSplit 数
        RandomSampler<Text, Text> sampler = new InputSampler.RandomSampler<Text,
                Text>(0.1, 10000, 10);
        Configuration conf = new Configuration();
//设置作业的分区文件路径
        TotalOrderPartitioner.setPartitionFile(conf, partitionFile);
        Job job = new Job(conf);
        job.setJobName("TotalSort");
        job.setJarByClass(TotalSort.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setNumReduceTasks(reduceNumber);
//设置 Partitioner 类
        job.setPartitionerClass(TotalOrderPartitioner.class);
        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);
        outputPath.getFileSystem(conf).delete(outputPath, true);
//写入分区文件
        InputSampler.writePartitionFile(job, sampler);
        System.out.println(job.waitForCompletion(true)? 0 : 1);
    }
}