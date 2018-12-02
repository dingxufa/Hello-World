package hadoop.join_2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @ProjectName JoinDemo
 * @PackageName com.buaa
 * @ClassName JoinRecordWithStationName
 * @Description TODO
 * @Date 2016-05-24 22:57:24
 */
public class App2 extends Configured implements Tool {
    public static class KeyPartitioner extends Partitioner<TextPair, Text> {
        public int getPartition(TextPair key, Text value, int numPartitions) {
            return (key.getFirst().hashCode() & Integer.MAX_VALUE) % numPartitions;
        }
    }

    public static class GroupComparator extends WritableComparator {
        protected GroupComparator() {
            super(TextPair.class, true);
        }

        @SuppressWarnings("rawtypes")
        @Override
        public int compare(WritableComparable wc1, WritableComparable wc2) {
            TextPair tp1 = (TextPair) wc1;
            TextPair tp2 = (TextPair) wc2;

            return tp1.getFirst().compareTo(tp2.getFirst());
        }
    }

    public int run(String[] args) throws Exception {
        // 读取配置文件
        Configuration conf = new Configuration();

        // 判断路径是否存在，如果存在，则删除
        Path mypath = new Path(args[2]);
        FileSystem hdfs = mypath.getFileSystem(conf);
        if (hdfs.isDirectory(mypath)) {
            hdfs.delete(mypath, true);
        }

        // 新建一个任务
        Job job = Job.getInstance(conf, "join");
        // 主类
        job.setJarByClass(App2.class);

        // 天气记录数据源
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, JoinRecordMapper.class);
        // 气象站数据源
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, JoinStationMapper.class);
        // 输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        // 自定义分区
        job.setPartitionerClass(KeyPartitioner.class);
        // 自定义分组
        job.setGroupingComparatorClass(GroupComparator.class);

        // 指定Reducer
        job.setReducerClass(JoinReducer.class);

        // map key输出类型
        job.setMapOutputKeyClass(TextPair.class);
        // reduce key输出类型
        job.setOutputKeyClass(Text.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        String[] args0 = {
                "hdfs://hadoop1:9000/buaa/join/record.txt",
                "hdfs://hadoop1:9000/buaa/join/station.txt",
                "hdfs://hadoop1:9000/buaa/join/out/"
        };
        int exitCode = ToolRunner.run(new App2(), args0);
        System.exit(exitCode);
    }
}